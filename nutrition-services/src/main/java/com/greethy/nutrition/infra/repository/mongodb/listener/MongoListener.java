package com.greethy.nutrition.infra.repository.mongodb.listener;

import com.greethy.core.domain.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MongoListener extends AbstractMongoEventListener<BaseEntity> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseEntity> event) {
        super.onBeforeConvert(event);
        Date dateNow = new Date();
        event.getSource().setCreatedAt(dateNow);
        event.getSource().setUpdatedAt(dateNow);
    }

}
