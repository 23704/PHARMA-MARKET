package com.pharmmarket.springbootpharmmarket.controller;


import com.pharmmarket.springbootpharmmarket.model.Customer;
import com.pharmmarket.springbootpharmmarket.model.PharmacyDirector;
import com.pharmmarket.springbootpharmmarket.service.CustomerService;
import com.pharmmarket.springbootpharmmarket.service.PharmacyDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pharmass")
public class CustomerController {


    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/registerCustomer")
    public String registerCustomer(Model theModel){
        // create Model attribute to bind form data
        Customer cus = new Customer();
        theModel.addAttribute("cus", cus);
        return "signup-customer";
    }
    @GetMapping("/loginCustomer")
    public String login(Model theModel){
        // create Model attribute to bind form data
        Customer cus = new Customer();
        theModel.addAttribute("cus", cus);
        return "login-customer";
    }
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("cus") Customer cus){

        customerService.saveCustomer(cus);
        return "redirect:/pharmass/loginCustomer";


    }
@PostMapping("/authenticate")
    public String AuthenticateCus(@ModelAttribute("cus") Customer cus){
        // Find the Admin
        Customer authCus =  customerService.findByEmail(cus.getEmail());
        if (authCus != null) {

            return "redirect:/drugs/list";
        }
        return "redirect:/pharmass/registerCustomer";
    }

}
