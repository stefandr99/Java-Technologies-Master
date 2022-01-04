package com.fii.laboratory_8.laboratory_8_v2.repositories;

import com.fii.laboratory_8.laboratory_8_v2.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;

public class UserRepository {
    @PersistenceContext(unitName = "com.PU")
    private EntityManager em;

    //@Transactional
    public void create(User user) {
        em.persist(user);
        em.flush();
    }

    //@Transactional
    public User getByUsername(String username) {
        return em.createNamedQuery("User.byUsername", User.class)
                .setParameter("username", username)
                .getResultList()
                .get(0);
    }
}
