package com.fii.laboratory_4.example;

import javax.faces.bean.ManagedBean;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "example")
public class Example {
    private List<String> examples = new ArrayList<>(Arrays.asList("Stefan", "for", "Geeks"));
    private String selectedExample;

    public String getSelectedExample() {
        return selectedExample;
    }

    public void setSelectedExample(String selectedExample) {
        this.selectedExample = selectedExample;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }
}

