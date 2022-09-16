package com.example.dcmanagesystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecoverApplyVO {
    private Integer violateId;
    private String username;
}
