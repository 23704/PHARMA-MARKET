package com.pharmmarket.springbootpharmmarket.service;


import com.pharmmarket.springbootpharmmarket.dao.DrugRepository;
import com.pharmmarket.springbootpharmmarket.model.Drug;
import com.pharmmarket.springbootpharmmarket.model.PharmacyDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class DrugServiceImpl implements DrugService {
    private DrugRepository drugRepository;
    private PharmacyDirectorService pharmacyDirectorService;

    private EntityManager entityManager;

    @Autowired
    public DrugServiceImpl(DrugRepository drugRepository,
                           PharmacyDirectorService pharmacyDirectorService,
                           EntityManager entityManager) {
        this.drugRepository = drugRepository;
        this.pharmacyDirectorService = pharmacyDirectorService;
        this.entityManager = entityManager;
    }

    @Override
    public Page<Drug> findAll(String key, int pageNumber) {
        Sort sort = Sort.by("drugName").ascending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 3, sort);
        if (key != null) {
            return drugRepository.findAll(key, pageable);
        }
        return drugRepository.findAll(pageable);
    }

    @Override
    public Drug findById(Long theId) {
        Optional<Drug> result = drugRepository.findById(theId);

        Drug drug = null;

        if (result.isPresent()) {
            drug = result.get();
        }
        else {
            // we didn't find the project
            throw new RuntimeException("Did not find Drug id - " + theId);
        }

        return drug;
    }

    @Override
    @Transactional
    public void save(Drug theDrug, Long pharmaId) {
        PharmacyDirector existingPharma = pharmacyDirectorService.findById(pharmaId);
        existingPharma.add(theDrug);
        drugRepository.save(theDrug);
    }

    @Override
    @Transactional
    public void save(Drug theDrug) {
        drugRepository.save(theDrug);

    }

    @Override
    public void deleteById(Long theId) {

        drugRepository.deleteById(theId);
    }

    @Override
    public List<Drug> findAll() {
        TypedQuery<Drug> theQuery = entityManager.createQuery("SELECT d FROM Drug d ORDER BY d.drugName ASC", Drug.class);

        // return query results
        return theQuery.getResultList();
    }
}
