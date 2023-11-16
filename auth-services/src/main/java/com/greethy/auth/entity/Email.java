package com.greethy.auth.entity;

import lombok.*;
import org.apache.avro.Schema;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AvroGenerated
public class Email extends SpecificRecordBase implements SpecificRecord {

    @Id
    private String id;

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
