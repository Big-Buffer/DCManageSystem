package com.example.dcmanagesystem.bean;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoverCheck {
    private Integer violate_id;
    private Integer customer_id;
    private String recover_reason;
    private String apply_date;
    private String apply_person;
    private String certificate_date;
    private String certificate_person;
    private Integer status;
}
