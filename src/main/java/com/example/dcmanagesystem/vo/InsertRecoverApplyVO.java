package com.example.dcmanagesystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertRecoverApplyVO {
    private Integer violateId;
    private Integer customerId;
    private String recoverReason;
    private String username;
}
