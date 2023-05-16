package com.pharmmarket.springbootpharmmarket.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pharmacyDirector")
public class PharmacyDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String first_name;

    private String last_name;


    private String password;

    private String email;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="pharmacyDirector",
            cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private List<Drug> drugs;

    public PharmacyDirector(){

    }

    public PharmacyDirector(String first_name, String last_name, String password, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public void add(Drug tempDrug)
    {
        if(drugs == null)
        {
            drugs = new ArrayList<>();
        }

        drugs.add(tempDrug);
        tempDrug.setPharmacyDirector(this);
    }

}

