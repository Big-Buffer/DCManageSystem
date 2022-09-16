package com.example.dcmanagesystem.controller;

import com.example.dcmanagesystem.bean.*;
import com.example.dcmanagesystem.service.AdminService;
import com.example.dcmanagesystem.service.CustomerService;
import com.example.dcmanagesystem.service.RecoverCheckService;
import com.example.dcmanagesystem.service.ViolateApplyService;
import com.example.dcmanagesystem.uitls.JWTUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @RequestMapping("/getAllCustomers")
    public Response getAllCustomer() {
        try {

            List<Customer> customers = customerService.queryAllCustomers();
            if (customers != null) {
                return new Response(200, "success", customers);
            }
            return new Response(512, "未知错误");
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }

    @RequestMapping("getViolateCustomerInfo")
    public Response getCustomerInfo(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            MyPageInfo<ViolateCustomerInfo> customers = customerService.queryViolateCustomerInfo();
            if (customers != null) {
                return new Response(200, "success", customers);
            }
            return new Response(512, "未知错误");
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }

    @RequestMapping("getCustomerInfo")
    public Response getViolateCustomerInfo(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            MyPageInfo<CustomerInfo> customers = customerService.queryCustomerInfo();
            if (customers != null) {
                return new Response(200, "success", customers);
            }
            return new Response(512, "未知错误");
        } catch (Exception e) {
            return new Response(502, "错误:" + e);
        }
    }

    @RequestMapping("/insertCustomer")
    public Response insertCustomer(@RequestParam String customerName, @RequestParam String area,
                                   @RequestParam String industry, @RequestParam int outsideLevel) {
        Customer customer = new Customer(customerName, area, industry, outsideLevel);
        try {
            customerService.insertCustomer(customer);
            return new Response(200, "success");
        } catch (Exception e) {
            return new Response(502, "错误：" + e);
        }
    }

}
