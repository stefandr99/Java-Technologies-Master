package com.fii.laboratory_6.controllers;

import com.fii.laboratory_6.entities.Exam;
import com.fii.laboratory_6.repositories.ExamRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class ExamController {
    @Inject
    ExamRepository examRepository;

    private List<Exam> exams;

    private Exam exam;

    public ExamController() {
        exams = new ArrayList<>();
        exam = new Exam();
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Exam> getExams() {
        exams = examRepository.get();
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public String addExam() {
        examRepository.create(exam);    

        return "showExams";
    }
}
