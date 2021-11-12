package com.fii.laboratory_6.controllers;

import com.fii.laboratory_6.entities.Exam;
import com.fii.laboratory_6.entities.ProjectExam;
import com.fii.laboratory_6.entities.Resource;
import com.fii.laboratory_6.entities.WrittenExam;
import com.fii.laboratory_6.repositories.GenericExamRepository;
import com.fii.laboratory_6.repositories.ResourceRespository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class GenericExamController {
    @EJB
    GenericExamRepository<WrittenExam> writtenExamGenericExamRepository;

    @EJB
    GenericExamRepository<ProjectExam> projectExamGenericExamRepository;

    @EJB
    ResourceRespository resourceRespository;

    private Exam exam;

    private List<WrittenExam> writtenExams;

    private WrittenExam writtenExam;

    private List<ProjectExam> projectExams;

    private ProjectExam projectExam;

    private List<Resource> resources;

    private String[] chosenResources;

    public GenericExamController() {
        this.exam = new Exam();
        this.writtenExams = new ArrayList<>();
        this.writtenExam = new WrittenExam();
        this.projectExams = new ArrayList<>();
        this.projectExam = new ProjectExam();
        this.resources = new ArrayList<>();
    }

    public String addWrittenExam() {
        writtenExam = new WrittenExam(exam, writtenExam.getPages());
        WrittenExam result = writtenExamGenericExamRepository.createWritten(writtenExam, chosenResources);

        if(result == null) {
            return "errorResource";
        }

        return "showExams?faces-redirect=true";
    }

    public String addProjectExam() {
        projectExam = new ProjectExam(exam, projectExam.getBibliography());
        ProjectExam result = projectExamGenericExamRepository.createProject(projectExam, chosenResources);

        if(result == null) {
            return "errorResource";
        }

        return "showExams?faces-redirect=true";
    }

    public List<WrittenExam> getWrittenExams() {
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

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Resource> getResources() {
        resources = resourceRespository.get();
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String[] getChosenResources() {
        return chosenResources;
    }

    public void setChosenResources(String[] chosenResources) {
        this.chosenResources = chosenResources;
    }
}
