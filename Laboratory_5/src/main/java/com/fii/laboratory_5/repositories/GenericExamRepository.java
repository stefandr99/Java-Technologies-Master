package com.fii.laboratory_5.repositories;

import com.fii.laboratory_5.entities.Exam;
import com.fii.laboratory_5.entities.ProjectExam;
import com.fii.laboratory_5.entities.WrittenExam;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Dependent
public class GenericExamRepository<T> implements Serializable {
    @PersistenceContext(unitName = "com.jpaPU")
    private EntityManager em;

    /*private Class<T> persistentClass;

    public GenericExamRepository(Class<T> c) {
        this.persistentClass = c;
    }*/

    public GenericExamRepository() {
    }

    @Transactional
    public T create(T entity) {
        em.persist(entity);
        em.flush();

        return entity;
    }

    /*public List<T> get() {
        return em.createNamedQuery(persistentClass.getName() + ".all", persistentClass).getResultList();
    }*/

    public List<WrittenExam> getWritten() {
        return em.createNamedQuery("WrittenExam.all", WrittenExam.class).getResultList();
    }

    public List<ProjectExam> getProject() {
        return em.createNamedQuery("ProjectExam.all", ProjectExam.class).getResultList();
    }

    public WrittenExam getWrittenByName(String name) {
        return em.createNamedQuery("WrittenExam.byName", WrittenExam.class)
                .setParameter("name", name)
                .getResultList()
                .get(0);
    }

    public ProjectExam getProjectByName(String name) {
        return em.createNamedQuery("ProjectExam.byName", ProjectExam.class)
                .setParameter("name", name)
                .getResultList()
                .get(0);
    }
}
