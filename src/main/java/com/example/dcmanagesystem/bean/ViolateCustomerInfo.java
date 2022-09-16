package com.example.dcmanagesystem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolateCustomerInfo {
    private Integer id_;
    private Integer violate_id;
    private String customer_name;
    private Integer violate_status;
    private Integer status;
    private String violate_reason;
    private Integer outside_level;
}
