package com.fii.laboratory_8.laboratory_8_v2.repositories;

import com.fii.laboratory_8.laboratory_8_v2.entities.Bibliography;
import com.fii.laboratory_8.laboratory_8_v2.entities.Document;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class DocumentRepository {
    @PersistenceContext(unitName = "com.PU")
    private EntityManager em;


    public void create2(Document document) {
        em.persist(document);
        em.flush();
    }

    public List<Document> get() {
        return em.createNamedQuery("Document2.all", Document.class).getResultList();
    }

    public List<Document> getByUserId(int userId) {
        return em.createNamedQuery("Document2.byUserId", Document.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Transactional
    public boolean delete(int id) {
        Long longId = (long) id;

        Document document = em.find(Document.class, longId);

        if(document != null) {
            em.remove(document);

            return true;
        }

        return false;
    }

    @Transactional
    public boolean update(int id, String name, String registrationNumber) {
        Long longId = (long) id;
        Document doc = em.find(Document.class, longId);

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
