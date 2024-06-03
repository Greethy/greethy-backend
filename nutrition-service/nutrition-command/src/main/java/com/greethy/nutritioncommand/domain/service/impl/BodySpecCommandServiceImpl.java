package com.greethy.nutritioncommand.domain.service.impl;

import com.greethy.core.infra.util.DataUtil;
import com.greethy.nutritioncommand.domain.port.BmiEvaluatePort;
import com.greethy.nutritioncommand.domain.port.BmrByAgePort;
import com.greethy.nutritioncommand.domain.port.BodySpecPort;
import com.greethy.nutritioncommand.domain.port.PalEvaluatePort;
import com.greethy.nutritioncommand.domain.service.BodySpecCommandService;
import com.greethy.nutritioncommon.dto.request.CreateBodySpecCommand;
import com.greethy.nutritioncommon.dto.response.BodySpecResponse;
import com.greethy.nutritioncommon.entity.BmiEvaluate;
import com.greethy.nutritioncommon.entity.BmrByAge;
import com.greethy.nutritioncommon.entity.BodySpec;
import com.greethy.nutritioncommon.entity.value.Bmi;
import com.greethy.nutritioncommon.entity.value.Bmr;
import com.greethy.nutritioncommon.entity.value.Pal;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BodySpecCommandServiceImpl implements BodySpecCommandService {

    private final ModelMapper mapper;

    private final BmrByAgePort bmrByAgePort;

    private final BmiEvaluatePort bmiEvaluatePort;

    private final PalEvaluatePort palEvaluatePort;

    private final BodySpecPort bodySpecPort;

    public BodySpecCommandServiceImpl(ModelMapper mapper,
                                      BmrByAgePort bmrByAgePort,
                                      BmiEvaluatePort bmiEvaluatePort,
                                      PalEvaluatePort palEvaluatePort,
                                      BodySpecPort bodySpecPort) {
        this.mapper = mapper;
        this.bmrByAgePort = bmrByAgePort;
        this.bmiEvaluatePort = bmiEvaluatePort;
        this.palEvaluatePort = palEvaluatePort;
        this.bodySpecPort = bodySpecPort;
    }

    @Override
    public Mono<BodySpecResponse> createBodySpec(CreateBodySpecCommand command) {
        Double bmiIndex = DataUtil.toDoubleSafely(
                (command.getWeight() / (command.getHeight() * command.getHeight()))
        );
        return Mono.zip(
                        bmiEvaluatePort.findByIndexInRange(bmiIndex)
                                .map(BmiEvaluate::getCategory)
                                .map(category -> new Bmi(bmiIndex, category)),
                        bmrByAgePort.findByAgeGroup(command.getAge())
                                .map(BmrByAge::getBmrPerKg)
                                .map(bmrPerKg -> new Bmr(bmrPerKg, bmrPerKg * command.getWeight())),
                        palEvaluatePort.findByAgeGroup(command.getAge())
                                .map(palEvaluate -> {
                                            switch (command.getActivityLevel()) {
                                                case SEDENTARY -> {
                                                    return palEvaluate.getSedentary();
                                                }
                                                case MODERATELY -> {
                                                    return palEvaluate.getModerately();
                                                }
                                                case VIGOROUS -> {
                                                    return palEvaluate.getVigorous();
                                                }
                                                default -> {
                                                    return 1.6d;
                                                }
                                            }
                                        }
                                ).map(palValue -> new Pal(command.getActivityLevel().getName(), palValue))
                ).map(tuple3 -> {
                    var bodySpec = mapper.map(command, BodySpec.class);
                    bodySpec.setBmi(tuple3.getT1());
                    bodySpec.setBmr(tuple3.getT2());
                    bodySpec.setPal(tuple3.getT3());
                    var tdee = tuple3.getT2().getBmrPerDay() * tuple3.getT3().getValue();
                    bodySpec.setTdee(tdee);
                    bodySpec.setGoal(command.getGoal().getName());
                    return bodySpec;
                }).flatMap(bodySpecPort::save)
                .doOnSuccess(bodySpec -> log.info("User"))
                .map(bodySpec -> mapper.map(bodySpec, BodySpecResponse.class));

    }
}
