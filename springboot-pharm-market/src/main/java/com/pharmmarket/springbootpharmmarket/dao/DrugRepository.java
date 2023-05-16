package com.pharmmarket.springbootpharmmarket.dao;

import com.pharmmarket.springbootpharmmarket.model.Drug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DrugRepository extends PagingAndSortingRepository<Drug, Long> {
    @Query("SELECT d FROM Drug d WHERE "
            + "CONCAT(d.drugName, d.description, d.quantity, d.price, d.fabDate, d.expDate, d.drugImage) "
            + "LIKE %?1%")
    public Page<Drug> findAll(String key, Pageable pageable);


}
