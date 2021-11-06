package com.fii.laboratory_5.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@NamedQuery(name = "Student.all", query = "select st from Student st join st.writtenExams ex join st.projectExams prex")
public class Student implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable( name = "writtenschedule",
            joinColumns = @JoinColumn(name = "studentid"),
            inverseJoinColumns = @JoinColumn(name = "examid") )
    private List<WrittenExam> writtenExams;

    @ManyToMany
    @JoinTable( name = "projectschedule",
            joinColumns = @JoinColumn(name = "studentid"),
            inverseJoinColumns = @JoinColumn(name = "examid") )
    private List<ProjectExam> projectExams;

    public Student() {
        writtenExams = new ArrayList<>();
        projectExams = new ArrayList<>();
    }

    public Student(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WrittenExam> getWrittenExams() {
        return writtenExams;
    }

    public void setWrittenExams(List<WrittenExam> exams) {
        this.writtenExams = exams;
    }

    public List<ProjectExam> getProjectExams() {
        return projectExams;
    }

    public void setProjectExams(List<ProjectExam> projectExams) {
        this.projectExams = projectExams;
    }
}
