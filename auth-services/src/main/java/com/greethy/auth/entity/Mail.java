package com.greethy.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    @Id
    private String id;

    private String from;

    private String to;

    private String cc;

    private String bcc;

    private String subject;

    private String body;

    private List<Attachment> attachments;
}
