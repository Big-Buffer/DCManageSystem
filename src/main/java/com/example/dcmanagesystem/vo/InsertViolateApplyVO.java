package com.example.dcmanagesystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertViolateApplyVO {
    private Integer customerId;
    private String violateReason;
    private String remark;
    private String username;
}
