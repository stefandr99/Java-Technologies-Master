package com.fii.laboratory_8.laboratory_8_v2.repositories;

import com.fii.laboratory_8.laboratory_8_v2.entities.Bibliography;
import com.fii.laboratory_8.laboratory_8_v2.entities.Document2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class DocumentRepository {
    @PersistenceContext(unitName = "com.PU")
    private EntityManager em;


    public void create2(Document2 document) {
        em.persist(document);
        em.flush();
    }

    public List<Document2> get() {
        return em.createNamedQuery("Document2.all", Document2.class).getResultList();
    }

    public List<Document2> getByUserId(int userId) {
        return em.createNamedQuery("Document2.byUserId", Document2.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Transactional
    public boolean delete(int id) {
        Long longId = (long) id;

        Document2 document2 = em.find(Document2.class, longId);

        if(document2 != null) {
            em.remove(document2);

            return true;
        }

        return false;
    }

    @Transactional
    public boolean update(int id, String name, String registrationNumber) {
        Long longId = (long) id;
        Document2 doc = em.find(Document2.class, longId);

        if(doc != null) {
            doc.setName(name);
            doc.setRegistrationNumber(registrationNumber);

            return true;
        }

        return false;
    }

    public List<Bibliography> getBibliography() {
        return em.createNamedQuery("Bibliography.all", Bibliography.class)
                .getResultList();
    }
}
