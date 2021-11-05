package com.fii.laboratory_5.controllers;

import com.fii.laboratory_5.entities.Exam;
import com.fii.laboratory_5.entities.Student;
import com.fii.laboratory_5.repositories.ExamRepository;
import com.fii.laboratory_5.repositories.StudentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestScoped
@Named
public class StudentController {
    @Inject
    StudentRepository studentRepository;

    @Inject
    ExamRepository examRepository;

    List<Student> students;

    Student student;

    String[] assignedExams;

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

    public String[] getAssignedExams() {
        return assignedExams;
    }

    public void setAssignedExams(String[] assignedExams) {
        this.assignedExams = assignedExams;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String addStudent() {
        studentRepository.create(student, assignedExams);

        return "showStudents";
    }
}
