package com.pharmmarket.springbootpharmmarket.controller;


import com.pharmmarket.springbootpharmmarket.model.PharmacyDirector;
import com.pharmmarket.springbootpharmmarket.service.PharmacyDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pharmas")
public class PharmacyDirectorController {

    private PharmacyDirectorService pharmacyDirectorService;
    @Autowired
    public PharmacyDirectorController(PharmacyDirectorService pharmacyDirectorService) {
        this.pharmacyDirectorService = pharmacyDirectorService;
    }

    @GetMapping("/registerDirector")
    public String registerDirector(Model theModel){
        // create Model attribute to bind form data
        PharmacyDirector pharma = new PharmacyDirector();
        theModel.addAttribute("pharma", pharma);
        return "signup-director";
    }
    @GetMapping("/loginDirector")
    public String login(Model theModel){
        // create Model attribute to bind form data
        PharmacyDirector pharma = new PharmacyDirector();
        theModel.addAttribute("pharma", pharma);
        return "login-director";
    }
    @PostMapping("/save")
    public String saveDirector(@ModelAttribute("pharma") PharmacyDirector pharma){
        // save the director
        pharmacyDirectorService.save(pharma);
        return "redirect:/pharmas/loginDirector";


    }

    @PostMapping("/authenticate")
    public String AuthenticateAdmin(@ModelAttribute("pharma") PharmacyDirector pharma){
        // Find the Admin
        PharmacyDirector authPharma = pharmacyDirectorService.findByEmail(pharma.getEmail());
        if (authPharma != null) {

            return "redirect:/drugs/getAddForm";
        }
        return "redirect:/pharmas/registerDirector";
    }
}
