package com.example.dcmanagesystem.mapper;

import com.example.dcmanagesystem.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface AdminMapper {
    void insertAdmin(Admin admin);
    Admin queryAdminByUsername(String username);
    void updateAdminByUsername(Admin admin);
    void deleteAdminByUsername(String username);
}
