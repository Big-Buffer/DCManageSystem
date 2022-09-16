package com.example.dcmanagesystem.mapper;

import com.example.dcmanagesystem.bean.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@Component
public interface CustomerMapper {
    void insertCustomer(Customer customer);
    List<Customer> queryAllCustomers();
    Customer queryCustomerById(Integer id_);
    List<Customer> queryCustomerByName(String customer_name);
    List<Customer> queryCustomerByLevel(Integer outside_level);
    void updateCustomer(Customer customer);
    void deleteCustomerById(Integer id_);
}
