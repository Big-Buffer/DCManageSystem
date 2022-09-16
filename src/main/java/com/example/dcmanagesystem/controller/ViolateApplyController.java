package com.example.dcmanagesystem.controller;

import com.example.dcmanagesystem.bean.*;
import com.example.dcmanagesystem.service.CustomerService;
import com.example.dcmanagesystem.service.ViolateApplyService;
import com.example.dcmanagesystem.uitls.TextUtils;
import com.example.dcmanagesystem.vo.InsertViolateApplyVO;
import com.example.dcmanagesystem.vo.ViolateApplyVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/violateApply")
public class ViolateApplyController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ViolateApplyService violateApplyService;

    @RequestMapping("/getAllApply")
    public Response getAllViolateApply() {
        try {
            PageHelper.startPage(1, 2);
            PageInfo<ViolateApply> violateApplies = violateApplyService.queryAllViolateApply();
            if (!violateApplies.getList().isEmpty()) {
                return new Response(200, "success", violateApplies);
            } else {
                return new Response(200, "success");
            }
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }

    @RequestMapping("/getViolateInfo")
    public Response getViolateInfo(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                   @RequestParam(required = false) String customerName, @RequestParam(required = false) String violateStatus,
                                   @RequestParam(required = false) String violateReason, @RequestParam(required = false) String violateLevel,
                                   @RequestParam(required = false) String applyPerson, @RequestParam(required = false) String outsideLevel) {
        try {
            if (customerName != null && !customerName.equals("null") && !customerName.equals("")) {
                List<Customer> customers = customerService.queryCustomerByName(customerName);
                if (customers.size() == 0) {
                    return new Response(414, "没有相关信息");
                }
                List<Integer> customer_ids = new ArrayList<>();
                for (Customer customer : customers) {
                    customer_ids.add(customer.getId_());
                }
                PageHelper.startPage(pageNum, pageSize);
                MyPageInfo<ViolateInfo> violateInfos = violateApplyService.queryViolateApplyByIds(customer_ids);
                return new Response(200, "success", violateInfos);
            } else if (violateStatus != null && !violateStatus.equals("null") && !violateStatus.equals("")) {
                PageHelper.startPage(pageNum, pageSize);
                MyPageInfo<ViolateInfo> violateInfos = violateApplyService.queryViolateApplyByViolateStatus(Integer.valueOf(violateStatus));
                return new Response(200, "success", violateInfos);
            } else if (violateReason != null && !violateReason.equals("null") && !violateReason.equals("")) {
                List<Integer> reasons = new ArrayList<>();
                String[] vio_reasons = violateReason.split(",");
                for (String s : vio_reasons) {
                    reasons.add(Integer.valueOf(s));
                }
                PageHelper.startPage(pageNum, pageSize);
                MyPageInfo<ViolateInfo> violateInfos = violateApplyService.queryViolateApplyByViolateReason(reasons);
                return new Response(200, "success", violateInfos);
            } else if (violateLevel != null && !violateLevel.equals("null") && !violateLevel.equals("")) {
                PageHelper.startPage(pageNum, pageSize);
                MyPageInfo<ViolateInfo> violateInfos = violateApplyService.queryViolateApplyByViolateLevel(Integer.valueOf(violateLevel));
                return new Response(200, "success", violateInfos);
            } else if (applyPerson != null && !applyPerson.equals("null") && !applyPerson.equals("")) {
                PageHelper.startPage(pageNum, pageSize);
                MyPageInfo<ViolateInfo> violateInfos = violateApplyService.queryViolateApplyByApplyPerson(applyPerson);
                return new Response(200, "success", violateInfos);
            } else if (outsideLevel != null && !outsideLevel.equals("null") && !outsideLevel.equals("")) {
                List<Customer> customers = customerService.queryCustomerByLevel(Integer.valueOf(outsideLevel));
                if (customers.size() == 0) {
                    return new Response(414, "没有相关信息");
                }
                List<Integer> customer_ids = new ArrayList<>();
                for (Customer customer : customers) {
                    customer_ids.add(customer.getId_());
                }
                PageHelper.startPage(pageNum, pageSize);
                MyPageInfo<ViolateInfo> violateInfos = violateApplyService.queryViolateApplyByIds(customer_ids);
                return new Response(200, "success", violateInfos);
            } else {
                PageHelper.startPage(pageNum, pageSize);
                MyPageInfo<ViolateInfo> violateInfos = violateApplyService.queryViolateInfo();
                return new Response(200, "success", violateInfos);
            }
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }

    @PostMapping("/insertApply")
    public Response insertViolateApply(@RequestBody InsertViolateApplyVO insertViolateApplyVO) {
        try {
            Customer customer = customerService.queryCustomerById(insertViolateApplyVO.getCustomerId());
            ViolateApply violateApply1 = violateApplyService.queryLastOneViolateApplyByCustomerId(insertViolateApplyVO.getCustomerId());
            if (violateApply1 != null && violateApply1.getStatus() == 1 && violateApply1.getStatus() == 2 && violateApply1.getStatus() == 3) {
                return new Response(413, "有待审核记录，无法新增");
            }
            int violate_level = 0;
            String[] buff = insertViolateApplyVO.getViolateReason().split(",");
            if (buff.length < 2) {
                violate_level = 1; // 低
            } else if (buff.length < 4) {
                violate_level = 2; // 中
            } else {
                violate_level = 3; // 高
            }
            if (customer == null) {
                return Response.invalidParamResp("customer_id:" + insertViolateApplyVO.getCustomerId());
            }
            ViolateApply violateApply = new ViolateApply();
            violateApply.setCustomer_id(insertViolateApplyVO.getCustomerId());
            violateApply.setViolate_reason(insertViolateApplyVO.getViolateReason());
            violateApply.setViolate_level(violate_level);
            violateApply.setRemark(insertViolateApplyVO.getRemark());
            violateApply.setStatus(1);
            violateApply.setApply_date(TextUtils.timeFormat(new Date()));
            violateApply.setApply_person(insertViolateApplyVO.getUsername());
            violateApplyService.insertViolateApply(violateApply);
            return new Response(200, "success");
        } catch (Exception e) {
            return new Response(502, "错误：" + e);
        }
    }

    @PostMapping("/pass")
    public Response passViolateApply(@RequestBody ViolateApplyVO violateApplyVO) {
        try {

            ViolateApply violateApply = violateApplyService.queryViolateApplyById(violateApplyVO.getId());
            if (violateApply != null) {
                if (violateApply.getStatus() == 2) {
                    return new Response(415, "通过失败，该记录已通过");
                }
                violateApply.setStatus(2);
                Integer customer_id = violateApply.getCustomer_id();
                Customer customer = customerService.queryCustomerById(customer_id);
                customer.setStatus(1);
                int violate_num = customer.getViolate_num();
                violate_num += 1;
                customer.setViolate_num(violate_num);
                customerService.updateCustomer(customer);
                violateApply.setCertificate_date(TextUtils.timeFormat(new Date()));
                violateApply.setCertificate_person(violateApplyVO.getUsername());
                violateApplyService.updateViolateApply(violateApply);
                return new Response(200, "success");

            }
            return new Response(512, "未知错误");
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }

    @PostMapping("/refuse")
    public Response refuseViolateApply(@RequestBody ViolateApplyVO violateApplyVO) {
        try {

            ViolateApply violateApply = violateApplyService.queryViolateApplyById(violateApplyVO.getId());
            if (violateApply != null) {
                if (violateApply.getStatus() == 2) {
                    return new Response(416, "拒绝失败，该记录已通过");
                }
                violateApply.setStatus(4);
                violateApply.setCertificate_date(TextUtils.timeFormat(new Date()));
                violateApply.setCertificate_person(violateApplyVO.getUsername());
                violateApplyService.updateViolateApply(violateApply);
                return new Response(200, "success");

            }
            return new Response(512, "未知错误");
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }
}
