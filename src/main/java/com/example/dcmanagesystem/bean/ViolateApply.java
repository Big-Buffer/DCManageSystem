package com.example.dcmanagesystem.bean;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViolateApply {
    private Integer id_;
    private Integer customer_id;
    private String violate_reason;
    private Integer violate_level;
    private String remark;
    private Integer status;
    private String apply_date;
    private String apply_person;
    private String certificate_date;
    private String certificate_person;
}
