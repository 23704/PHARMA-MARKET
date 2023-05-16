package com.pharmmarket.springbootpharmmarket.service;


import com.pharmmarket.springbootpharmmarket.model.PharmacyDirector;



public interface PharmacyDirectorService {
    public PharmacyDirector findById(Long id);

    public PharmacyDirector findByEmail(String email);

    public void save(PharmacyDirector pharmacyDirector);
}
