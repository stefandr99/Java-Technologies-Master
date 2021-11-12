package com.fii.laboratory_6.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resources")
@NamedQueries({
        @NamedQuery(name = "Resources.all", query = "select re from Resource re"),
        @NamedQuery(name = "Resources.byName", query = "select re from Resource re where re.name = :name"),
        @NamedQuery(name = "Resources.getFromWritten", query = "select re from Resource re join re.writtenExams wex where wex.data = :data"),
        @NamedQuery(name = "Resources.getFromProject", query = "select re from Resource re join re.projectExams pex where pex.data = :data")
})
public class Resource implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private int number;

    @ManyToMany
    @JoinTable( name = "writtenReservations",
            joinColumns = @JoinColumn(name = "resourceid"),
            inverseJoinColumns = @JoinColumn(name = "examid") )
    private List<WrittenExam> writtenExams;

    @ManyToMany
    @JoinTable( name = "projectReservations",
            joinColumns = @JoinColumn(name = "resourceid"),
            inverseJoinColumns = @JoinColumn(name = "examid") )
    private List<ProjectExam> projectExams;

    public Resource() {
        writtenExams = new ArrayList<>();
        projectExams = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<WrittenExam> getWrittenExams() {
        return writtenExams;
    }

    public void setWrittenExams(List<WrittenExam> writtenExams) {
        this.writtenExams = writtenExams;
    }

    public List<ProjectExam> getProjectExams() {
        return projectExams;
    }

    public void setProjectExams(List<ProjectExam> projectExams) {
        this.projectExams = projectExams;
    }
}
