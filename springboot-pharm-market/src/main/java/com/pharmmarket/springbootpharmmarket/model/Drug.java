package com.pharmmarket.springbootpharmmarket.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name="drug")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String drugName;

    private String description;

    private Long quantity;
    private Long price;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private String fabDate;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private String expDate;

    private String category;

    private String drugImage;

    @ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name="pharma_id")
    private PharmacyDirector pharmacyDirector;

    public Drug(){

    }

    public Drug(String drugName, String description, Long price, String fabDate,
                String expDate, Long quantity, String category) {
        this.drugName = drugName;
        this.description = description;
        this.price = price;
        this.fabDate = fabDate;
        this.expDate = expDate;
        this.quantity = quantity;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getFabDate() {
        return fabDate;
    }

    public void setFabDate(String fabDate) {
        this.fabDate = fabDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getDrugImage() {
        return drugImage;
    }

    public void setDrugImage(String drugImage) {
        this.drugImage = drugImage;
    }

    public PharmacyDirector getPharmacyDirector() {
        return pharmacyDirector;
    }

    public void setPharmacyDirector(PharmacyDirector pharmacyDirector) {
        this.pharmacyDirector = pharmacyDirector;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
