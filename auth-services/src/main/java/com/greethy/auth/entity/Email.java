package com.greethy.auth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.avro.Schema;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AvroGenerated
public class Email extends SpecificRecordBase implements SpecificRecord {

    private String from;

    private String to;

    private String cc;

    private String bcc;

    private String subject;

    private String body;

    private List<Attachment> attachments;

    @Override
    public Schema getSchema() {
        return null;
    }

    @Override
    public Object get(int field) {
        return null;
    }

    @Override
    public void put(int field, Object value) {

    }
}
