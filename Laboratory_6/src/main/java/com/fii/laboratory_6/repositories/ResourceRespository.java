package com.fii.laboratory_6.repositories;

import com.fii.laboratory_6.entities.Resource;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class ResourceRespository {
    @PersistenceContext(unitName = "com.jpaPU")
    private EntityManager em;

    public List<Resource> get() {
        return em.createNamedQuery("Resources.all", Resource.class).getResultList();
    }

    public Resource getByName(String name) {
        return em.createNamedQuery("Resources.byName", Resource.class)
                .setParameter("name", name)
                .getResultList()
                .get(0);
    }

    public List<Resource> getWrittenByDate(Date date) {
        return em.createNamedQuery("Resources.getFromWritten", Resource.class)
                .setParameter("data", date)
                .getResultList();
    }

    public List<Resource> getProjectByDate(Date date) {
        return em.createNamedQuery("Resources.getFromProject", Resource.class)
                .setParameter("data", date)
                .getResultList();
    }
}
