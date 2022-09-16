package com.example.dcmanagesystem.service;

import com.example.dcmanagesystem.bean.*;
import com.example.dcmanagesystem.mapper.CustomerMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    ViolateApplyService violateApplyService;

    public void insertCustomer(Customer customer) {
        customerMapper.insertCustomer(customer);
    }

    public List<Customer> queryAllCustomers() {
        return customerMapper.queryAllCustomers();
    }

    public MyPageInfo<CustomerInfo> queryCustomerInfo() {
        PageInfo<Customer> customerPageInfo = new PageInfo<>(customerMapper.queryAllCustomers());
        return new MyPageInfo<>(customerPageInfo, violateApplyService);
    }

    public Customer queryCustomerById(Integer id_) {
        return customerMapper.queryCustomerById(id_);
    }

    public List<Customer> queryCustomerByName(String customer_name) {
        return customerMapper.queryCustomerByName(customer_name);
    }

    public void updateCustomer(Customer customer) {
        customerMapper.updateCustomer(customer);
    }

    public void deleteCustomerById(Integer id_) {
        customerMapper.deleteCustomerById(id_);
    }

    public List<Customer> queryCustomerByLevel(Integer outside_level) {
        return customerMapper.queryCustomerByLevel(outside_level);
    }

    public MyPageInfo<ViolateCustomerInfo> queryViolateCustomerInfo() {
        PageInfo<Customer> customerPageInfo = new PageInfo<>(customerMapper.queryAllCustomers());
        String s = "violateCustomerInfo";
        return new MyPageInfo<>(customerPageInfo, violateApplyService, s);
    }
}
