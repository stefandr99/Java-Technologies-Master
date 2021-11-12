package com.fii.laboratory_6.repositories;

import com.fii.laboratory_6.entities.Resource;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class InMemoryRepository {
    private Map<String, List<Resource>> assignments;

    @PostConstruct
    public void init() {
        assignments = new HashMap<>();
    }

    public void addAssignment(String examName, List<Resource> resources) {
        assignments.put(examName, resources);
    }
}
