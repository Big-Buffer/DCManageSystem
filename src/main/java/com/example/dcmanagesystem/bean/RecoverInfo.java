package com.example.dcmanagesystem.bean;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecoverInfo {
    private Integer violate_id;
    private Integer customer_id;
    private String customer_name;
    private String violate_reason;
    private Integer violate_level;
    private String apply_person;
    private String apply_date;
    private String recover_reason;
    private Integer outside_level;
}
