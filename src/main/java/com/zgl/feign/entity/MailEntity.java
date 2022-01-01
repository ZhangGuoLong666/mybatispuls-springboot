package com.zgl.feign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailEntity {
    private String to;
    private String subject;
    private String text;
}
