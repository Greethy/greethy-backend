package com.greethy.feedbackquery;

import com.greethy.common.infra.util.RandomUtil;
import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.feedbackcommon.entity.GorseFeedback;
import com.greethy.feedbackcommon.entity.enums.FeedbackType;
import com.greethy.feedbackquery.domain.port.GorseClientPort;
import com.greethy.feedbackquery.domain.port.NutritionClientPort;
import com.greethy.feedbackquery.domain.port.UserClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication(
        scanBasePackages = {
                "com.greethy.common",
                "com.greethy.feedbackcommon",
                "com.greethy.feedbackquery"
        })
@RequiredArgsConstructor
public class FeedbackQueryApplication implements CommandLineRunner {

    private final UserClientPort userClientPort;

    private final GorseClientPort gorseClientPort;

    private final NutritionClientPort nutritionClientPort;

    public static void main(String[] args) {
        SpringApplication.run(FeedbackQueryApplication.class);
    }

    @Override
    public void run(String... args) {
        List<String> userIds = userClientPort.getAllUserIds()
                .map(ObjectIdResponse::id)
                .collectList().block();
        List<String> foodIds = nutritionClientPort.getAllFoodIds()
                .map(ObjectIdResponse::id)
                .collectList().block();
        var feedbacks = IntStream.range(0, 60000)
                .mapToObj(i -> {
                    assert userIds != null;
                    assert foodIds != null;
                    var userId = RandomUtil.getSingleRandomFromStrings(userIds);
                    var foodId = RandomUtil.getSingleRandomFromStrings(foodIds);
                    var timestamp = LocalDateTime.now().minusDays(RandomUtil.getSingleRandomInteger(0, 15));
                    var feedbackType = RandomUtil.getSingleRandomFromStrings(Arrays.stream(FeedbackType.values()).map(FeedbackType::getType).toList());
                    return GorseFeedback.builder()
                            .userId(userId)
                            .itemId(foodId)
                            .timestamp(timestamp)
                            .feedbackType(feedbackType)
                            .build();
                }).collect(Collectors.toList());
        gorseClientPort.saveFeedbacks(feedbacks)
                .subscribe();
    }
}