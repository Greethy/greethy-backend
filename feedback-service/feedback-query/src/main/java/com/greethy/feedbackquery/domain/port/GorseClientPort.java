package com.greethy.feedbackquery.domain.port;

import com.greethy.feedbackcommon.dto.response.GorseResponse;
import com.greethy.feedbackcommon.entity.GorseFeedback;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GorseClientPort {

    Mono<GorseResponse> saveFeedbacks(List<GorseFeedback> feedback);

}
