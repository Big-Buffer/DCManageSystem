package com.example.dcmanagesystem.controller;

import com.example.dcmanagesystem.bean.*;
import com.example.dcmanagesystem.service.CustomerService;
import com.example.dcmanagesystem.service.RecoverCheckService;
import com.example.dcmanagesystem.service.ViolateApplyService;
import com.example.dcmanagesystem.uitls.TextUtils;
import com.example.dcmanagesystem.vo.InsertRecoverApplyVO;
import com.example.dcmanagesystem.vo.RecoverApplyVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/recoverApply")
public class RecoverCheckController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ViolateApplyService violateApplyService;
    @Autowired
    RecoverCheckService recoverCheckService;

    @RequestMapping("/getAllApply")
    public Response getAllRecoverApply() {
        try {
            PageInfo<RecoverCheck> recoverChecks = recoverCheckService.queryAllRecoverCheck();
            if (!recoverChecks.getList().isEmpty()) {
                return new Response(200, "success", recoverChecks.getList());
            } else {
                return new Response(200, "success");
            }
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }

    @RequestMapping("/getRecoverApplyInfo")
    public Response getRecoverApplyInfo(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            MyPageInfo<RecoverInfo> recoverApplyInfo = recoverCheckService.queryRecoverApplyInfo();
            return new Response(200, "success", recoverApplyInfo);
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }

    @PostMapping("/insertApply")
    public Response insertRecoverApply(@RequestBody InsertRecoverApplyVO insertRecoverApplyVO) {
        try {
            Customer customer = customerService.queryCustomerById(insertRecoverApplyVO.getCustomerId());
            if (customer == null) {
                return Response.invalidParamResp("customer_id:" + insertRecoverApplyVO.getCustomerId());
            }
            ViolateApply violateApply = violateApplyService.queryViolateApplyById(insertRecoverApplyVO.getViolateId());
            if (violateApply == null) {
                return Response.invalidParamResp("violate_id:" + insertRecoverApplyVO.getViolateId());
            }
            if (violateApply.getStatus() == 3){
                return new Response(413, "有待审核记录，无法新增");
            }
            violateApply.setStatus(3);  // 待重生
            RecoverCheck recoverCheck = new RecoverCheck();
            recoverCheck.setViolate_id(insertRecoverApplyVO.getViolateId());
            recoverCheck.setCustomer_id(insertRecoverApplyVO.getCustomerId());
            recoverCheck.setRecover_reason(insertRecoverApplyVO.getRecoverReason());
            recoverCheck.setApply_date(TextUtils.timeFormat(new Date()));
            recoverCheck.setApply_person(insertRecoverApplyVO.getUsername());
            violateApplyService.updateViolateApply(violateApply);
            try {
                recoverCheckService.insertRecoverCheck(recoverCheck);
            } catch (Exception e){
                recoverCheckService.updateRecoverCheck(recoverCheck);
            }

            return new Response(200, "success");
        } catch (Exception e) {
            return new Response(502, "错误：" + e);
        }
    }

    @RequestMapping("/pass")
    public Response passRecoverApply(@RequestBody RecoverApplyVO recoverApplyVO) {
        try {
            ViolateApply violateApply = violateApplyService.queryViolateApplyById(recoverApplyVO.getViolateId());
            RecoverCheck recoverCheck = recoverCheckService.queryRecoverCheckById(recoverApplyVO.getViolateId());
            if (violateApply != null && recoverCheck != null) {
                if (violateApply.getStatus() != 3) {
                    return new Response(415, "通过失败，该记录已通过");
                }
                violateApply.setStatus(0);
                Integer customer_id = violateApply.getCustomer_id();
                Customer customer = customerService.queryCustomerById(customer_id);
                customer.setStatus(0);
                int recover_num = customer.getRecover_num();
                recover_num += 1;
                customer.setRecover_num(recover_num);
                customerService.updateCustomer(customer);
                violateApplyService.updateViolateApply(violateApply);
                recoverCheck.setCertificate_date(TextUtils.timeFormat(new Date()));
                recoverCheck.setCertificate_person(recoverApplyVO.getUsername());
                recoverCheckService.updateRecoverCheck(recoverCheck);
                return new Response(200, "success");
            }
            return new Response(512, "未知错误");
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }

    @PostMapping("/refuse")
    public Response refuseRecoverApply(@RequestBody RecoverApplyVO recoverApplyVO) {
        try {

            ViolateApply violateApply = violateApplyService.queryViolateApplyById(recoverApplyVO.getViolateId());
            RecoverCheck recoverCheck = recoverCheckService.queryRecoverCheckById(recoverApplyVO.getViolateId());
            if (violateApply != null) {
                if (violateApply.getStatus() == 2) {
                    return new Response(416, "拒绝失败，该记录已通过");
                }
                violateApply.setStatus(0);
                violateApplyService.updateViolateApply(violateApply);
                recoverCheck.setCertificate_date(TextUtils.timeFormat(new Date()));
                recoverCheck.setCertificate_person(recoverApplyVO.getUsername());
                recoverCheckService.updateRecoverCheck(recoverCheck);
                return new Response(200, "success");
            }
            return new Response(512, "未知错误");
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }
}
