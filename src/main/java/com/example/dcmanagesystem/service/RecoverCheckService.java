package com.example.dcmanagesystem.service;

import com.example.dcmanagesystem.bean.MyPageInfo;
import com.example.dcmanagesystem.bean.RecoverCheck;
import com.example.dcmanagesystem.bean.RecoverInfo;
import com.example.dcmanagesystem.bean.ViolateApply;
import com.example.dcmanagesystem.mapper.RecoverCheckMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoverCheckService {
    @Autowired
    RecoverCheckMapper recoverCheckMapper;
    @Autowired
    CustomerService customerService;
    @Autowired
    ViolateApplyService violateApplyService;

    public void insertRecoverCheck(RecoverCheck recoverCheck){
        recoverCheckMapper.insertRecoverCheck(recoverCheck);
    }

//    public List<RecoverCheck> queryAllRecoverCheck(){
//        return recoverCheckMapper.queryAllRecoverCheck();
//    }

    public PageInfo<RecoverCheck> queryAllRecoverCheck(){
        return new PageInfo<RecoverCheck>(recoverCheckMapper.queryAllRecoverCheck());
    }

    public MyPageInfo<RecoverInfo> queryRecoverApplyInfo(){
        PageInfo<RecoverCheck> recoverCheckPageInfo = new PageInfo<>(recoverCheckMapper.queryAllRecoverCheck());
        return new MyPageInfo<>(recoverCheckPageInfo, customerService, violateApplyService);
    }

    public RecoverCheck queryRecoverCheckById(Integer violate_id){
        return recoverCheckMapper.queryRecoverCheckById(violate_id);
    }

    public void updateRecoverCheck(RecoverCheck recoverCheck){
        recoverCheckMapper.updateRecoverCheck(recoverCheck);
    }

    public void deleteRecoverCheckById(Integer violate_id){
        recoverCheckMapper.deleteRecoverCheckById(violate_id);
    }
}
