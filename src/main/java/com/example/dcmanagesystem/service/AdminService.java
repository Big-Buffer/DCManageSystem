package com.example.dcmanagesystem.service;

import com.example.dcmanagesystem.bean.Admin;
import com.example.dcmanagesystem.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    public void insertAdmin(Admin admin){
        adminMapper.insertAdmin(admin);
    }
    public Admin queryAdminByUsername(String username){
        return adminMapper.queryAdminByUsername(username);
    }

    public void updateAdminByUsername(Admin admin){
        adminMapper.updateAdminByUsername(admin);
    }

    public void deleteAdminByUsername(String username){
        adminMapper.deleteAdminByUsername(username);
    }

}
