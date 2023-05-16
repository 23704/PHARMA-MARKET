package com.pharmmarket.springbootpharmmarket.dao;

import com.pharmmarket.springbootpharmmarket.model.PharmacyDirector;

public interface PharmacyDirectorRepository {



    public void save(PharmacyDirector pharmacyDirector);

    public PharmacyDirector findByEmail(String email);

    public PharmacyDirector findById(Long id);
}
