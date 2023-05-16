package com.pharmmarket.springbootpharmmarket.service;


import com.pharmmarket.springbootpharmmarket.dao.PharmacyDirectorRepository;
import com.pharmmarket.springbootpharmmarket.model.PharmacyDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PharmacyDirectorServiceImpl implements PharmacyDirectorService {


    @Autowired
    private PharmacyDirectorRepository pharmacyDirectorRepository;
    @Override
    @Transactional
    public PharmacyDirector findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public PharmacyDirector findByEmail(String email) {
        return pharmacyDirectorRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void save(PharmacyDirector pharmacyDirector) {

        pharmacyDirectorRepository.save(pharmacyDirector);

    }
}
