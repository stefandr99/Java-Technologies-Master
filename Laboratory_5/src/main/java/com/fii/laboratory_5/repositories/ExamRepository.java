package com.fii.laboratory_5.repositories;

import com.fii.laboratory_5.entities.Exam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class ExamRepository {
    @PersistenceContext(unitName = "com.jpaPU")
    private EntityManager em;

    @Transactional
    public Exam create(Exam exam) {
        em.persist(exam);
        em.flush();

        return exam;
    }

    public List<Exam> get() {
        return em.createNamedQuery("Exam.all", Exam.class).getResultList();
    }

    public Exam getByName(String name) {
        return em.createNamedQuery("Exam.byName", Exam.class)
                .setParameter("name", name)
                .getResultList()
                .get(0);
    }
}
