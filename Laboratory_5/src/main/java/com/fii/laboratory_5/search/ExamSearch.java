package com.fii.laboratory_5.search;

import com.fii.laboratory_5.entities.WrittenExam;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Dependent
public class ExamSearch implements Serializable {
    @PersistenceContext(unitName = "com.jpaPU")
    private EntityManager em;

    public List<WrittenExam> searchUser(List<SearchFields> params) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<WrittenExam> query = builder.createQuery(WrittenExam.class);
        Root r = query.from(WrittenExam.class);

        Predicate predicate = builder.conjunction();

        ExamSearchConsumer searchConsumer =
                new ExamSearchConsumer(predicate, builder, r);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        List<WrittenExam> result = em.createQuery(query).getResultList();
        return result;
    }
}
