package com.pharmmarket.springbootpharmmarket.dao;


import com.pharmmarket.springbootpharmmarket.model.PharmacyDirector;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PharmacyDirectorRepositoryImpl implements PharmacyDirectorRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(PharmacyDirector pharmacyDirector) {
        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create the director ...
        currentSession.saveOrUpdate(pharmacyDirector);

    }

    @Override
    public PharmacyDirector findByEmail(String email) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using username
        Query<PharmacyDirector> theQuery = currentSession.createQuery("from PharmacyDirector where email=:uemail", PharmacyDirector.class);
        theQuery.setParameter("uemail", email);
        PharmacyDirector theEmail = null;
        try{
            theEmail = theQuery.getSingleResult();
        }catch(Exception e){
            theEmail = null;
        }
        return theEmail;
    }

    @Override
    public PharmacyDirector findById(Long id) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using id
        Query<PharmacyDirector> theQuery = currentSession.createQuery("from PharmacyDirector where id=:uid", PharmacyDirector.class);
        theQuery.setParameter("uid", id);
        PharmacyDirector theDir = null;
        try{
            theDir = theQuery.getSingleResult();
        }catch(Exception e){
            theDir = null;
        }
        return theDir;
    }
}
