package com.greethy.nutritioncommand.domain.service.impl;

import com.greethy.common.infra.util.DataUtil;
import com.greethy.common.domain.event.AddBodySpecToUserEvent;
import com.greethy.nutritioncommand.domain.port.BmiEvaluatePort;
import com.greethy.nutritioncommand.domain.port.BmrByAgePort;
import com.greethy.nutritioncommand.domain.port.BodySpecPort;
import com.greethy.nutritioncommand.domain.port.PalEvaluatePort;
import com.greethy.nutritioncommand.domain.port.producer.BodySpecEventProducer;
import com.greethy.nutritioncommand.domain.service.BodySpecCommandService;
import com.greethy.nutritioncommon.dto.request.command.CreateBodySpecCommand;
import com.greethy.nutritioncommon.dto.response.BodySpecResponse;
import com.greethy.nutritioncommon.entity.BmiEvaluate;
import com.greethy.nutritioncommon.entity.BmrByAge;
import com.greethy.nutritioncommon.entity.BodySpec;
import com.greethy.nutritioncommon.entity.value.Bmi;
import com.greethy.nutritioncommon.entity.value.Bmr;
import com.greethy.nutritioncommon.entity.value.Pal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
    public Mono<BodySpecResponse> createBodySpec(CreateBodySpecCommand command) {
        Double bmiIndex = DataUtil.toDoubleSafely((command.getWeight() / (command.getHeight() * command.getHeight())));
        return Mono.zip(bmiEvaluatePort.findByIndexInRange(bmiIndex)
                                .map(BmiEvaluate::getCategory)
                                .map(category -> new Bmi(bmiIndex, category)),
                        bmrByAgePort.findByAgeGroup(command.getAge())
                                .map(BmrByAge::getBmrPerKg)
                                .map(bmrPerKg -> new Bmr(bmrPerKg, bmrPerKg * command.getWeight())),
                        palEvaluatePort.findByAgeGroup(command.getAge())
                                .map(palEvaluate -> {
                                    Double palValue;
                                    switch (command.getActivityLevel()) {
                                        case SEDENTARY -> palValue = palEvaluate.getSedentary();
                                        case MODERATELY -> palValue = palEvaluate.getModerately();
                                        case VIGOROUS -> palValue = palEvaluate.getVigorous();
                                        default -> palValue = 1.6d;
                                    }
                                    return palValue;
                                }).map(palValue -> new Pal(command.getActivityLevel().getName(), palValue))
                ).map(tuple3 -> {
                    var bodySpec = mapper.map(command, BodySpec.class);
                    var tdee = tuple3.getT2().getBmrPerDay() * tuple3.getT3().getValue();
                    bodySpec.setTdee(tdee);
                    bodySpec.setBmi(tuple3.getT1());
                    bodySpec.setBmr(tuple3.getT2());
                    bodySpec.setPal(tuple3.getT3());
                    bodySpec.setGoal(command.getGoal().getName());
                    return bodySpec;
                }).flatMap(mongoBodySpecPort::save)
                .doOnSuccess(bodySpec -> {
                    var event = AddBodySpecToUserEvent.builder()
                            .id(UUID.randomUUID().toString())
                            .name(AddBodySpecToUserEvent.class.getSimpleName())
                            .createdAt(LocalDateTime.now())
                            .source(serviceName)
                            .payload(new AddBodySpecToUserEvent.Payload(bodySpec.getId(), command.getUsername()))
                            .build();
                    bodySpecEventProducer.produce(event);
                    log.info("BodySpec {} created ", bodySpec);
                }).map(bodySpec -> mapper.map(bodySpec, BodySpecResponse.class));
    }
}
