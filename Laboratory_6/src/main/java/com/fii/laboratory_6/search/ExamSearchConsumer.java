package com.fii.laboratory_6.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

public class ExamSearchConsumer implements Consumer<SearchFields> {
    private Predicate predicate;
    private final CriteriaBuilder builder;
    private Root r;

    public ExamSearchConsumer(Predicate predicate, CriteriaBuilder builder, Root r) {
        this.predicate = predicate;
        this.builder = builder;
        this.r = r;
    }

    @Override
    public void accept(SearchFields param) {
        if (r.get(param.getKey()).getJavaType() == String.class) {
            predicate = builder.and(predicate, builder.like(
                    r.get(param.getKey()), "%" + param.getValue() + "%"));
        }
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public CriteriaBuilder getBuilder() {
        return builder;
    }

    public Root getR() {
        return r;
    }

    public void setR(Root r) {
        this.r = r;
    }
}
