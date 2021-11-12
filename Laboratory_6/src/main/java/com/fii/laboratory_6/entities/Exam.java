package com.fii.laboratory_6.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
/*
@Entity
@Table(name = "exams")
@NamedQueries({
        @NamedQuery(name = "Exam.all", query = "select ex from Exam ex order by ex.id"),
        @NamedQuery(name = "Exam.byName", query = "select ex from Exam ex where ex.name = :name")
})*/
@MappedSuperclass
public class Exam implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "data")
    private Date data;

    @Column(name = "duration")
    private int duration;

    @ManyToMany
    @JoinTable( name = "schedule",
            joinColumns = @JoinColumn(name = "examid"),
            inverseJoinColumns = @JoinColumn(name = "studentid") )
    private List<Student> students;

    @ManyToMany
    @JoinTable( name = "reservation",
            joinColumns = @JoinColumn(name = "examid"),
            inverseJoinColumns = @JoinColumn(name = "resourceid") )
    private List<Resource> resources;

    public Exam() {
        students = new ArrayList<>();
        resources = new ArrayList<>();
    }

    public Exam(String name, Date data, int duration) {
        this.name = name;
        this.data = data;
        this.duration = duration;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return duration == exam.duration && Objects.equals(id, exam.id) && Objects.equals(name, exam.name) && Objects.equals(data, exam.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, data, duration);
    }

    @Override
    public String toString() {
        return name + ", on " + data + " lasting " + duration + " minutes";
    }
}
