package com.example.dcmanagesystem.bean;

import com.example.dcmanagesystem.service.CustomerService;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolateInfo {
    private Integer violate_id;
    private Integer customer_id;
    private String customer_name;
    private Integer violate_status;
    private String violate_reason;
    private Integer violate_level;
    private String apply_person;
    private String apply_date;
    private String certificate_date;
    private Integer outside_level;
    private Integer status;
}
