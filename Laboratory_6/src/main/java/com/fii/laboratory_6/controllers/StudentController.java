package com.fii.laboratory_6.controllers;


import com.fii.laboratory_6.entities.Student;
import com.fii.laboratory_6.repositories.StudentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class StudentController {
    @Inject
    StudentRepository studentRepository;

    List<Student> students;

    Student student;

    String[] writtenAssignedExams;

    String[] projectAssignedExams;

    String selectedExam;

    public StudentController() {
        student = new Student();
        students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        students = studentRepository.get();
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getSelectedExam() {
        return selectedExam;
    }

    public void setSelectedExam(String selectedExam) {
        this.selectedExam = selectedExam;
    }

    public String[] getWrittenAssignedExams() {
        return writtenAssignedExams;
    }

    public void setWrittenAssignedExams(String[] writtenAssignedExams) {
        this.writtenAssignedExams = writtenAssignedExams;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String[] getProjectAssignedExams() {
        return projectAssignedExams;
    }

    public void setProjectAssignedExams(String[] projectAssignedExams) {
        this.projectAssignedExams = projectAssignedExams;
    }

    public String addStudent() {
        studentRepository.create(student, writtenAssignedExams, projectAssignedExams);

        return "showStudents?faces-redirect=true";
    }
}
