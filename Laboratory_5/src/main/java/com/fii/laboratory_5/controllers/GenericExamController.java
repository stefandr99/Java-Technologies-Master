package com.fii.laboratory_5.controllers;

import com.fii.laboratory_5.entities.Exam;
import com.fii.laboratory_5.entities.ProjectExam;
import com.fii.laboratory_5.entities.WrittenExam;
import com.fii.laboratory_5.repositories.GenericExamRepository;
import com.fii.laboratory_5.search.ExamSearch;
import com.fii.laboratory_5.search.SearchFields;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
public class GenericExamController implements Serializable {
    @Inject
    GenericExamRepository<WrittenExam> writtenExamGenericExamRepository;

    @Inject
    GenericExamRepository<ProjectExam> projectExamGenericExamRepository;

    @Inject
    ExamSearch examSearch;

    private Exam exam;

    private List<WrittenExam> writtenExams;

    private WrittenExam writtenExam;

    private List<ProjectExam> projectExams;

    private ProjectExam projectExam;

    private String search;

    private String criteria;

    public GenericExamController() {
        this.exam = new Exam();
        this.writtenExams = new ArrayList<>();
        this.writtenExam = new WrittenExam();
        this.projectExams = new ArrayList<>();
        this.projectExam = new ProjectExam();
    }

    public String searchExams() {
        List<SearchFields> fields = new ArrayList<>();
        if (criteria == null || criteria.isEmpty())
            criteria = "name";

        fields.add(new SearchFields(criteria, search));
        writtenExams = examSearch.searchUser(fields);

        return "showExams?faces-redirect=true";
    }

    public List<WrittenExam> getWrittenExams() {
        if(search == null || search.isEmpty())
            writtenExams = writtenExamGenericExamRepository.getWritten();

        return writtenExams;
    }

    public void setWrittenExams(List<WrittenExam> writtenExams) {
        this.writtenExams = writtenExams;
    }

    public WrittenExam getWrittenExam() {
        return writtenExam;
    }

    public void setWrittenExam(WrittenExam writtenExam) {
        this.writtenExam = writtenExam;
    }

    public List<ProjectExam> getProjectExams() {
        projectExams = projectExamGenericExamRepository.getProject();

        return projectExams;
    }

    public void setProjectExams(List<ProjectExam> projectExams) {
        this.projectExams = projectExams;
    }

    public ProjectExam getProjectExam() {
        return projectExam;
    }

    public void setProjectExam(ProjectExam projectExam) {
        this.projectExam = projectExam;
    }

    public String addWrittenExam() {
        writtenExam = new WrittenExam(exam, writtenExam.getPages());
        writtenExamGenericExamRepository.create(writtenExam);

        return "showExams";
    }

    public String addProjectExam() {
        projectExam = new ProjectExam(exam, projectExam.getBibliography());
        projectExamGenericExamRepository.create(projectExam);

        return "showExams";
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}
