package com.fii.laboratory_6.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projectexams")
@NamedQueries({
        @NamedQuery(name = "ProjectExam.all", query = "select ex from ProjectExam ex order by ex.id"),
        @NamedQuery(name = "ProjectExam.byName", query = "select ex from ProjectExam ex where ex.name = :name"),
        @NamedQuery(name = "ProjectExam.allWithResources", query = "select ex from ProjectExam ex join ex.resources res")
})
public class ProjectExam extends Exam {
    @Column(name = "bibliography")
    private String bibliography;

    @ManyToMany
    @JoinTable( name = "projectReservations",
            joinColumns = @JoinColumn(name = "examid"),
            inverseJoinColumns = @JoinColumn(name = "resourceid") )
    private List<Resource> resources;

    public ProjectExam() {
        resources = new ArrayList<>();
    }

    public ProjectExam(String name, Date data, int duration, String bibliography) {
        super(name, data, duration);
        this.bibliography = bibliography;
        resources = new ArrayList<>();
    }

    public ProjectExam(Exam exam, String bibliography) {
        super(exam.getName(), exam.getData(), exam.getDuration());
        this.bibliography = bibliography;
        resources = new ArrayList<>();
    }

    public String getBibliography() {
        return bibliography;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
