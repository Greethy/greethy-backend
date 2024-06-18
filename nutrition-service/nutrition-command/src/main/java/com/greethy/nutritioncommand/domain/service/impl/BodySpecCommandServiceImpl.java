package com.greethy.nutritioncommand.domain.service.impl;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.common.infra.util.DataUtil;
import com.greethy.common.domain.event.AddBodySpecToUserEvent;
import com.greethy.nutritioncommand.domain.port.BmiEvaluatePort;
import com.greethy.nutritioncommand.domain.port.BmrByAgePort;
import com.greethy.nutritioncommand.domain.port.BodySpecPort;
import com.greethy.nutritioncommand.domain.port.PalEvaluatePort;
import com.greethy.nutritioncommand.domain.port.producer.BodySpecEventProducer;
import com.greethy.nutritioncommand.domain.service.BodySpecCommandService;
import com.greethy.nutritioncommon.constant.Constants;
import com.greethy.nutritioncommon.dto.request.command.CreateBodySpecCommand;
import com.greethy.nutritioncommon.dto.request.command.UpdateBodySpecCommand;
import com.greethy.nutritioncommon.dto.response.BodySpecResponse;
import com.greethy.nutritioncommon.entity.BmiEvaluate;
import com.greethy.nutritioncommon.entity.BmrByAge;
import com.greethy.nutritioncommon.entity.BodySpec;
import com.greethy.nutritioncommon.entity.enums.ActivityLevel;
import com.greethy.nutritioncommon.entity.value.Bmi;
import com.greethy.nutritioncommon.entity.value.Bmr;
import com.greethy.nutritioncommon.entity.value.Pal;
import com.greethy.nutritioncommon.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Service implementation for body specification change BodySpec resources (create, update, delete).
 * This service handles the creation of {@link BodySpec} instances which include health indices
 * like BMI (Body Measure Index), BMR (Basal Metabolic Rate), and PAL (Physical Activity Level).
 *
 * @author KienThanh
 * @see BodySpecCommandService
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BodySpecCommandServiceImpl implements BodySpecCommandService {

    @Value("${spring.application.name}")
    private String serviceName;

    private final ModelMapper mapper;

    private final Translator translator;

    private final BmrByAgePort bmrByAgePort;

    private final BmiEvaluatePort bmiEvaluatePort;

    private final PalEvaluatePort palEvaluatePort;

    private final BodySpecPort mongoBodySpecPort;

    private final BodySpecEventProducer bodySpecEventProducer;

    /**
     * Creates and persists a BodySpec entity based on the given command.
     * The method computes BMI, BMR, and PAL based on the input parameters from {@code CreateBodySpecCommand},
     * maps them to a new BodySpec entity, and saves it using a reactive repository.
     *
     * @param command A command object containing the necessary data to create a BodySpec,
     *                including weight, height, age, and activity level.
     * @return A {@link Mono} that emits {@link BodySpecResponse} upon successful creation
     * and persistence of the BodySpec entity.
     */
    @Override
    public Mono<ObjectIdResponse> createBodySpec(String username, CreateBodySpecCommand command) {
        return calculateIndexes(command.getAge(), command.getHeight(), command.getWeight(), command.getActivityLevel())
                .map(indexes -> {
                    var bodySpec = mapper.map(command, BodySpec.class);
                    var tdee = indexes.getT2().getBmrPerDay() * indexes.getT3().getValue();
                    bodySpec.setTdee(tdee);
                    bodySpec.setUsername(username);
                    bodySpec.setBmi(indexes.getT1());
                    bodySpec.setBmr(indexes.getT2());
                    bodySpec.setPal(indexes.getT3());
                    bodySpec.setGoal(command.getGoal().getName());
                    bodySpec.setActivityLevel(command.getActivityLevel().getName());
                    return bodySpec;
                })
                .flatMap(mongoBodySpecPort::save)
                .doOnSuccess(bodySpec -> {
                    var event = AddBodySpecToUserEvent.builder()
                            .id(UUID.randomUUID().toString())
                            .name(AddBodySpecToUserEvent.class.getSimpleName())
                            .createdAt(LocalDateTime.now())
                            .source(serviceName)
                            .payload(new AddBodySpecToUserEvent.Payload(bodySpec.getId(), username))
                            .build();
                    bodySpecEventProducer.produce(event);
                }).doOnNext(bodySpec -> log.info("BodySpec {} created ", bodySpec))
                .map(bodySpec -> new ObjectIdResponse(bodySpec.getId()));
    }

    @Override
    public Mono<BodySpecResponse> updateBodySpec(String bodySpecId, UpdateBodySpecCommand command) {
        return mongoBodySpecPort.findById(bodySpecId)
                .switchIfEmpty(Mono.error(this::bodySpecNotFoundException))
                .zipWith(calculateIndexes(command.getAge(), command.getHeight(), command.getWeight(), command.getActivityLevel()))
                .doOnNext(tuple2 -> {
                    var bodySpec = tuple2.getT1();
                    var indexes = tuple2.getT2();
                    var tdee = indexes.getT2().getBmrPerDay() * indexes.getT3().getValue();
                    bodySpec.setTdee(tdee);
                    bodySpec.setAge(command.getAge());
                    bodySpec.setHeight(command.getHeight());
                    bodySpec.setWeight(command.getWeight());
                    bodySpec.setBmi(indexes.getT1());
                    bodySpec.setBmr(indexes.getT2());
                    bodySpec.setPal(indexes.getT3());
                    bodySpec.setGoal(command.getGoal().getName());
                    bodySpec.setActivityLevel(command.getActivityLevel().getName());
                }).map(Tuple2::getT1)
                .flatMap(mongoBodySpecPort::save)
                .map(bodySpec -> mapper.map(bodySpec, BodySpecResponse.class));

    }

    @Override
    public Mono<Void> deleteBodySpec(String bodySpecId) {
        return mongoBodySpecPort.deleteById(bodySpecId)
                ;
    }

    private Mono<Tuple3<Bmi, Bmr, Pal>> calculateIndexes(Integer age, Double height,
                                                         Double weight, ActivityLevel activityLevel) {
        Double bmiIndex = DataUtil.toDoubleSafely((weight / (height * height)));
        return Mono.zip(
                bmiEvaluatePort.findByIndexInRange(bmiIndex)
                        .map(BmiEvaluate::getCategory)
                        .map(category -> new Bmi(bmiIndex, category)),
                bmrByAgePort.findByAgeGroup(age)
                        .map(BmrByAge::getBmrPerKg)
                        .map(bmrPerKg -> new Bmr(bmrPerKg, bmrPerKg * weight)),
                palEvaluatePort.findByAgeGroup(age)
                        .map(palEvaluate -> {
                            Double palValue;
                            switch (activityLevel) {
                                case SEDENTARY -> palValue = palEvaluate.getSedentary();
                                case MODERATELY -> palValue = palEvaluate.getModerately();
                                case VIGOROUS -> palValue = palEvaluate.getVigorous();
                                default -> palValue = 1.6d;
                            }
                            return palValue;
                        }).map(palValue -> new Pal(activityLevel.getName(), palValue))
        );
    }

    private NotFoundException bodySpecNotFoundException() {
        var message = translator.getLocalizedMessage(Constants.MessageKeys.BODY_SPEC_NOT_FOUND);
        return new NotFoundException(message);
    }

}
