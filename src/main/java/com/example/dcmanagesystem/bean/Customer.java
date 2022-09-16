package com.example.dcmanagesystem.bean;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer id_;
    private String customer_name;
    private String area;
    private String industry;
    private Integer status;
    private Integer violate_num;
    private Integer recover_num;
    private Integer outside_level;

    public Customer(String customer_name, String area, String industry, Integer outside_level){
        this.id_ = 0;
        this.customer_name = customer_name;
        this.area = area;
        this.industry = industry;
        this.status = 0;
        this.violate_num = 0;
        this.recover_num = 0;
        this.outside_level = outside_level;
    }
}
