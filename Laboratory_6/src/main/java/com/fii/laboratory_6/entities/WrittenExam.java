package com.fii.laboratory_6.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "writtenexams")
@NamedQueries({
        @NamedQuery(name = "WrittenExam.all", query = "select ex from WrittenExam ex order by ex.id"),
        @NamedQuery(name = "WrittenExam.byName", query = "select ex from WrittenExam ex where ex.name = :name"),
        @NamedQuery(name = "WrittenExam.allWithResources", query = "select ex from WrittenExam ex join ex.resources res")
})
public class WrittenExam extends Exam {
    @Column(name = "pages")
    private int pages;

    @ManyToMany
    @JoinTable( name = "writtenReservations",
            joinColumns = @JoinColumn(name = "examid"),
            inverseJoinColumns = @JoinColumn(name = "resourceid") )
    private List<Resource> resources;

    public WrittenExam(String name, Date data, int duration, int pages) {
        super(name, data, duration);
        this.pages = pages;
        resources = new ArrayList<>();
    }

    public WrittenExam(Exam exam, int pages) {
        super(exam.getName(), exam.getData(), exam.getDuration());
        this.pages = pages;
        resources = new ArrayList<>();
    }

    public WrittenExam() {
        resources = new ArrayList<>();
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
