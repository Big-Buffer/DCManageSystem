package com.example.dcmanagesystem.service;

import com.example.dcmanagesystem.bean.MyPageInfo;
import com.example.dcmanagesystem.bean.ViolateApply;
import com.example.dcmanagesystem.bean.ViolateInfo;
import com.example.dcmanagesystem.mapper.ViolateApplyMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ViolateApplyService {
    @Autowired
    ViolateApplyMapper violateApplyMapper;
    @Autowired
    CustomerService customerService;

    public void insertViolateApply(ViolateApply violateApply){
        violateApplyMapper.insertViolateApply(violateApply);
    }

    public PageInfo<ViolateApply> queryAllViolateApply(){
        return new PageInfo<ViolateApply>(violateApplyMapper.queryAllViolateApply());
    }

    public MyPageInfo<ViolateInfo> queryViolateInfo(){
        PageInfo<ViolateApply> violateApplyList = new PageInfo<>(violateApplyMapper.queryAllViolateApply());
        return new MyPageInfo<>(violateApplyList, customerService);
    }

    public ViolateApply queryViolateApplyById(Integer id_){
        return violateApplyMapper.queryViolateApplyById(id_);
    }

    public void updateViolateApply(ViolateApply violateApply){
        violateApplyMapper.updateViolateApply(violateApply);
    }

    public void deleteViolateApplyById(Integer id_){
        violateApplyMapper.deleteViolateApplyById(id_);
    }

    public MyPageInfo<ViolateInfo> queryViolateApplyByIds(List<Integer> customer_ids) {
        PageInfo<ViolateApply> violateApplyList = new PageInfo<>(violateApplyMapper.queryViolateApplyByCustomerIds(customer_ids));
        return new MyPageInfo<>(violateApplyList, customerService);
    }

    public MyPageInfo<ViolateInfo> queryViolateApplyByViolateStatus(Integer violate_status) {
        PageInfo<ViolateApply> violateApplyList = new PageInfo<>(violateApplyMapper.queryViolateApplyByViolateStatus(violate_status));
        return new MyPageInfo<>(violateApplyList, customerService);
    }

    public MyPageInfo<ViolateInfo> queryViolateApplyByViolateReason(List<Integer> reasons) {
        PageInfo<ViolateApply> violateApplyList = new PageInfo<>(violateApplyMapper.queryViolateApplyByViolateReason(reasons));
        return new MyPageInfo<>(violateApplyList, customerService);
    }

    public MyPageInfo<ViolateInfo> queryViolateApplyByViolateLevel(Integer violate_level) {
        PageInfo<ViolateApply> violateApplyList = new PageInfo<>(violateApplyMapper.queryViolateApplyByViolateLevel(violate_level));
        return new MyPageInfo<>(violateApplyList, customerService);
    }

    public MyPageInfo<ViolateInfo> queryViolateApplyByApplyPerson(String apply_person) {
        PageInfo<ViolateApply> violateApplyList = new PageInfo<>(violateApplyMapper.queryViolateApplyByApplyPerson(apply_person));
        return new MyPageInfo<>(violateApplyList, customerService);
    }

    public String queryLastViolateApplyReasonByCustomerId(Integer customer_id) {
        ViolateApply violateApply = violateApplyMapper.queryLastViolateApplyByCustomerId(customer_id);
        if(violateApply!=null && violateApply.getStatus()!=0){
            return violateApply.getViolate_reason();
        } else {
            return "";
        }
    }

    public ViolateApply queryLastViolateApplyByCustomerId(Integer customer_id) {
        return violateApplyMapper.queryLastViolateApplyByCustomerId(customer_id);
    }

    public ViolateApply queryLastOneViolateApplyByCustomerId(Integer customer_id) {
        return violateApplyMapper.queryLastViolateApplyByCustomerId(customer_id);
    }
}
