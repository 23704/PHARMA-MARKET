package com.pharmmarket.springbootpharmmarket.service;

import com.pharmmarket.springbootpharmmarket.model.Drug;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DrugService {
    Page<Drug> findAll(String key, int pageNumber); // Adjusted method signature

    Drug findById(Long theId);

    void save(Drug theDrug, Long pharmaId);

    void save(Drug theDrug);

    void deleteById(Long theId);

    List<Drug> findAll();
}
