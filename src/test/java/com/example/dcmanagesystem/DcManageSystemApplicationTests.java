package com.example.dcmanagesystem;

import com.example.dcmanagesystem.bean.Admin;
import com.example.dcmanagesystem.bean.Customer;
import com.example.dcmanagesystem.bean.Response;
import com.example.dcmanagesystem.mapper.AdminMapper;
import com.example.dcmanagesystem.service.AdminService;
import com.example.dcmanagesystem.service.CustomerService;
import com.example.dcmanagesystem.uitls.JWTUtils;
import org.json.JSONObject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DcManageSystemApplicationTests {

    @Autowired
    AdminService adminService;
    @Autowired
    CustomerService customerService;
    @Autowired
    JWTUtils jwtUtils;
    @Test
    void contextLoads() {
        String str = "1,2";
        String[] reason = str.split(",");
        System.out.println(Arrays.toString(reason));
        List<Integer> list = new ArrayList<>();
        for (String s: reason) {
            list.add(Integer.valueOf(s));
        }
        System.out.println(list);
//        System.out.println(jwtUtils.createToken("张三"));

//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        Customer customer = new Customer(0,"王五","金华","金融",0,0,0,0);
//        customerService.insertCustomer(customer);
//        Customer customer1 = new Customer(0,"赵六","宁波","软件",0,0,0,0);
//        customerService.insertCustomer(customer1);
//        Customer customer2 = new Customer(0,"陈七","杭州","广告",0,0,0,0);
//        customerService.insertCustomer(customer2);
//        Admin admin = new Admin("张三","1234567",0);
//        System.out.println(new Response(200,"success",admin));
//        String token = jwtUtils.createToken(admin.getUsername());
//        System.out.println(token);
//        int code = jwtUtils.verify(token);
//        System.out.println(code);
    }

}
