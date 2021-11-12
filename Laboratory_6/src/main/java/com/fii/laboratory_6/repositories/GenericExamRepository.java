package com.fii.laboratory_6.repositories;

import com.fii.laboratory_6.entities.ProjectExam;
import com.fii.laboratory_6.entities.Resource;
import com.fii.laboratory_6.entities.WrittenExam;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Stateful
public class GenericExamRepository<T> {
    @PersistenceContext(unitName = "com.jpaPU")
    private EntityManager em;

    @EJB
    ResourceRespository resourceRespository;

    @EJB
    InMemoryRepository inMemoryRepository;

    /*private Class<T> persistentClass;

    public GenericExamRepository(Class<T> c) {
        this.persistentClass = c;
    }*/

    public GenericExamRepository() {
    }

    @Transactional
    public T create(T entity, String[] chosenResources) {
        em.persist(entity);
        em.flush();

        return entity;
    }

    @Transactional
    public WrittenExam createWritten(WrittenExam entity, String[] chosenResources) {
        List<Resource> writtenResources = resourceRespository.getWrittenByDate(entity.getData());
        List<Resource> projectResources = resourceRespository.getProjectByDate(entity.getData());

        int roomsCount = 0, projectorsCount = 0;
        for(Resource res : writtenResources) {
            if(res.getName().equals("room"))
                roomsCount++;
            else
                projectorsCount++;
        }
        for(Resource res : projectResources) {
            if(res.getName().equals("room"))
                roomsCount++;
            else
                projectorsCount++;
        }

        for (String resourceName : chosenResources) {
            Resource resource = resourceRespository.getByName(resourceName);

            if(resource.getName().equals("room")) {
                if(roomsCount >= resource.getNumber())
                    return null;
            }
            else {
                if(projectorsCount >= resource.getNumber())
                    return null;
            }

            entity.getResources().add(resource);
        }

        em.persist(entity);
        em.flush();

        inMemoryRepository.addAssignment(entity.getName(), entity.getResources());

        return entity;
    }

    @Transactional
    public ProjectExam createProject(ProjectExam entity, String[] chosenResources) {
        List<Resource> writtenResources = resourceRespository.getWrittenByDate(entity.getData());
        List<Resource> projectResources = resourceRespository.getProjectByDate(entity.getData());

        int roomsCount = 0, projectorsCount = 0;
        for(Resource res : writtenResources) {
            if(res.getName().equals("room"))
                roomsCount++;
            else
                projectorsCount++;
        }
        for(Resource res : projectResources) {
            if(res.getName().equals("room"))
                roomsCount++;
            else
                projectorsCount++;
        }

        for (String resourceName : chosenResources) {
            Resource resource = resourceRespository.getByName(resourceName);

            if(resource.getName().equals("room")) {
                if(roomsCount >= resource.getNumber())
                    return null;
            }
            else {
                if(projectorsCount >= resource.getNumber())
                    return null;
            }

            entity.getResources().add(resource);
        }

        em.persist(entity);
        em.flush();

        inMemoryRepository.addAssignment(entity.getName(), entity.getResources());

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
