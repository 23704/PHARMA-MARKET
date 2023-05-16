package com.pharmmarket.springbootpharmmarket.service;


import com.pharmmarket.springbootpharmmarket.dao.CustomerRepository;
import com.pharmmarket.springbootpharmmarket.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer cust) {
        return customerRepository.save(cust);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Email not Found"));
    }

    @Override
    public Customer findById(int theId) {
        return customerRepository.findById(theId).orElseThrow(()-> new RuntimeException("Customer ID not Found"));
    }
}
