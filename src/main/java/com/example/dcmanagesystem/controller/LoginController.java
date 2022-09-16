package com.example.dcmanagesystem.controller;

import com.example.dcmanagesystem.bean.Admin;
import com.example.dcmanagesystem.bean.Customer;
import com.example.dcmanagesystem.bean.RecoverCheck;
import com.example.dcmanagesystem.bean.Response;
import com.example.dcmanagesystem.service.AdminService;
import com.example.dcmanagesystem.service.CustomerService;
import com.example.dcmanagesystem.service.RecoverCheckService;
import com.example.dcmanagesystem.service.ViolateApplyService;
import com.example.dcmanagesystem.uitls.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    AdminService adminService;
    @Autowired
    CustomerService customerService;
    @Autowired
    RecoverCheckService recoverCheckService;
    @Autowired
    ViolateApplyService violateApplyService;
    @Autowired
    JWTUtils jwtUtils;

    @RequestMapping("/test")
    public Response test() {
        Admin admin = new Admin("张三", "1234567", 0);
        return new Response(200, "success", admin);
    }

    @RequestMapping("/login")
    public Response login(@RequestParam String username, @RequestParam String password, @RequestParam Integer authority) {
        Admin query_admin = adminService.queryAdminByUsername(username);
        System.out.println(query_admin);
        Admin admin = new Admin(username, password, authority);
        System.out.println(admin);
        if (query_admin != null) {
            if (query_admin.equals(admin)) {
//                String token = jwtUtils.createToken(admin.getUsername());
//                return new Response(200, "success", token);
                return new Response(200, "success");
            } else if (!query_admin.getPassword().equals(admin.getPassword())) {
                return new Response(402, "密码错误");
            }
        } else {
            return new Response(401, "用户名错误");
        }
        return new Response(501, "未知错误");
    }
}
