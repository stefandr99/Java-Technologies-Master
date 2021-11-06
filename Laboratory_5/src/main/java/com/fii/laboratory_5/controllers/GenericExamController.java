package com.fii.laboratory_5.controllers;

import com.fii.laboratory_5.entities.Exam;
import com.fii.laboratory_5.entities.ProjectExam;
import com.fii.laboratory_5.entities.WrittenExam;
import com.fii.laboratory_5.repositories.GenericExamRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class GenericExamController {
    @Inject
    GenericExamRepository<WrittenExam> writtenExamGenericExamRepository;

    @Inject
    GenericExamRepository<ProjectExam> projectExamGenericExamRepository;

    private Exam exam;

    private List<WrittenExam> writtenExams;

    private WrittenExam writtenExam;

    private List<ProjectExam> projectExams;

    private ProjectExam projectExam;

    public GenericExamController() {
        this.exam = new Exam();
        this.writtenExams = new ArrayList<>();
        this.writtenExam = new WrittenExam();
        this.projectExams = new ArrayList<>();
        this.projectExam = new ProjectExam();
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
}
