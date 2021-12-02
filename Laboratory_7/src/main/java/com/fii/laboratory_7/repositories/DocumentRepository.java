package com.fii.laboratory_7.repositories;

import com.fii.laboratory_7.entities.Document;
import com.fii.laboratory_7.entities.Document2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class DocumentRepository {
    @PersistenceContext(unitName = "com.CDIPU")
    private EntityManager em;

    @Transactional
    public void create(Document document) {
        em.persist(document);
        em.flush();
    }

    @Transactional
    public void create2(Document2 document) {
        em.persist(document);
        em.flush();
    }

    public List<Document2> get() {
        return em.createNamedQuery("Document2.all", Document2.class).getResultList();
    }
}
