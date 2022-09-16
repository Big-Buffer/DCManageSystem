package com.example.dcmanagesystem.bean;

import com.example.dcmanagesystem.service.CustomerService;
import com.example.dcmanagesystem.service.ViolateApplyService;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPageInfo<T> {
    /**
     * 当前页
     */
    private int currentPageNum;

    /**
     * 总页数
     */
    private int totalPageNum;

    /**
     * 每页记录数量
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 当前页记录集合
     */
    List<T> list;

    /**
     * 传入Page结果对象构造Pageing对象
     */

    public MyPageInfo(PageInfo<Customer> customerPageInfo, ViolateApplyService violateApplyService) {
        List<Customer> customers = customerPageInfo.getList();
        List<CustomerInfo> customerInfos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerInfo customerInfo = new CustomerInfo();
            customerInfo.setCustomer(customer);
            customerInfo.setViolate_reason(violateApplyService.queryLastViolateApplyReasonByCustomerId(customer.getId_()));
            customerInfos.add(customerInfo);
        }
        this.currentPageNum = customerPageInfo.getPageNum();
        this.totalPageNum = customerPageInfo.getPages();
        this.pageSize = customerPageInfo.getPageSize();
        this.totalCount = customerPageInfo.getTotal();
        this.list = (List<T>) customerInfos;
    }

    public MyPageInfo(PageInfo<Customer> customerPageInfo, ViolateApplyService violateApplyService, String s) {
        List<Customer> customers = customerPageInfo.getList();
        List<ViolateCustomerInfo> violateCustomerInfos = new ArrayList<>();
        for (Customer customer : customers) {
            ViolateCustomerInfo violateCustomerInfo = new ViolateCustomerInfo();
            ViolateApply violateApply = violateApplyService.queryLastViolateApplyByCustomerId(customer.getId_());
            violateCustomerInfo.setId_(customer.getId_());
            violateCustomerInfo.setCustomer_name(customer.getCustomer_name());
            violateCustomerInfo.setStatus(customer.getStatus());
            if(violateApply!=null){
                violateCustomerInfo.setViolate_id(violateApply.getId_());
                violateCustomerInfo.setViolate_status(violateApply.getStatus());
                violateCustomerInfo.setViolate_reason(violateApply.getViolate_reason());
            }else {
                violateCustomerInfo.setViolate_id(-1);
                violateCustomerInfo.setViolate_status(0);
                violateCustomerInfo.setViolate_reason("");
            }
            violateCustomerInfo.setOutside_level(customer.getOutside_level());
            violateCustomerInfos.add(violateCustomerInfo);
        }
        this.currentPageNum = customerPageInfo.getPageNum();
        this.totalPageNum = customerPageInfo.getPages();
        this.pageSize = customerPageInfo.getPageSize();
        this.totalCount = customerPageInfo.getTotal();
        this.list = (List<T>) violateCustomerInfos;
    }

    public MyPageInfo(PageInfo<ViolateApply> violateApplyList, CustomerService customerService) {
        List<ViolateInfo> violateInfos = new ArrayList<>();
        for (ViolateApply apply: violateApplyList.getList()) {
            ViolateInfo violateInfo = new ViolateInfo();
            Customer customer = customerService.queryCustomerById(apply.getCustomer_id());
            violateInfo.setViolate_id(apply.getId_());
            violateInfo.setCustomer_id(apply.getCustomer_id());
            violateInfo.setCustomer_name(customer.getCustomer_name());
            violateInfo.setViolate_status(apply.getStatus());
            violateInfo.setViolate_reason(apply.getViolate_reason());
            violateInfo.setViolate_level(apply.getViolate_level());
            violateInfo.setApply_person(apply.getApply_person());
            violateInfo.setApply_date(apply.getApply_date());
            violateInfo.setCertificate_date(apply.getCertificate_date());
            violateInfo.setOutside_level(customer.getOutside_level());
            violateInfo.setStatus(customer.getStatus());
            violateInfos.add(violateInfo);
        }
        this.currentPageNum = violateApplyList.getPageNum();
        this.totalPageNum = violateApplyList.getPages();
        this.pageSize = violateApplyList.getPageSize();
        this.totalCount = violateApplyList.getTotal();
        this.list = (List<T>) violateInfos;
    }


    public MyPageInfo(PageInfo<RecoverCheck> recoverCheckPageInfo, CustomerService customerService, ViolateApplyService violateApplyService) {
        List<RecoverInfo> recoverInfos = new ArrayList<>();
        for (RecoverCheck check: recoverCheckPageInfo.getList()) {
            RecoverInfo recoverInfo = new RecoverInfo();
            Customer customer = customerService.queryCustomerById(check.getCustomer_id());
            ViolateApply apply = violateApplyService.queryViolateApplyById(check.getViolate_id());
            recoverInfo.setViolate_id(check.getViolate_id());
            recoverInfo.setCustomer_id(check.getCustomer_id());
            recoverInfo.setCustomer_name(customer.getCustomer_name());
            recoverInfo.setViolate_reason(apply.getViolate_reason());
            recoverInfo.setViolate_level(apply.getViolate_level());
            recoverInfo.setApply_person(check.getApply_person());
            recoverInfo.setApply_date(check.getApply_date());
            recoverInfo.setOutside_level(customer.getOutside_level());
            recoverInfos.add(recoverInfo);
        }
        this.currentPageNum = recoverCheckPageInfo.getPageNum();
        this.totalPageNum = recoverCheckPageInfo.getPages();
        this.pageSize = recoverCheckPageInfo.getPageSize();
        this.totalCount = recoverCheckPageInfo.getTotal();
        this.list = (List<T>) recoverInfos;
    }
}
