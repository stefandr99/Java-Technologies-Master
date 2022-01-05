package com.fii.laboratory_8.laboratory_8_v2.services;

import com.fii.laboratory_8.laboratory_8_v2.entities.Bibliography;
import com.fii.laboratory_8.laboratory_8_v2.entities.Document2;
import com.fii.laboratory_8.laboratory_8_v2.repositories.DocumentRepository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphService {
    @Inject
    DocumentRepository documentRepository;

    Map<Long, List<Long>> adj = new HashMap<>();

    public boolean check(List<Document2> document2List) {
        List<Bibliography> bibliographies = documentRepository.getBibliography();

        for(Document2 document2 : document2List) {
            adj.put(document2.getId(), bibliographies.stream().filter(b -> b.getDocumentId().equals(document2.getId()))
                    .map(Bibliography::getReferenceId)
                    .collect(Collectors.toList()));
        }

        boolean[] visited = new boolean[100];
        boolean[] recursion = new boolean[100];

        for (Long id : adj.keySet())
            if (isCyclic(id, visited, recursion))
                return true;

        return false;
    }

    private boolean isCyclic(Long id, boolean[] visited, boolean[] recursion)
    {
        if (recursion[id.intValue()])
            return true;

        if (visited[id.intValue()])
            return false;

        visited[id.intValue()] = true;

        recursion[id.intValue()] = true;
        List<Long> children = adj.get(id);

        for (Long c: children)
            if (isCyclic(c, visited, recursion))
                return true;

        recursion[id.intValue()] = false;

        return false;
    }
}
