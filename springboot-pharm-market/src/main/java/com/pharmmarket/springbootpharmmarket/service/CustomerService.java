package com.pharmmarket.springbootpharmmarket.service;


import com.pharmmarket.springbootpharmmarket.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer cust);

    Customer findByEmail(String email);

    Customer findById(int theId);
}
