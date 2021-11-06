package com.fii.laboratory_5.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "projectexams")
@NamedQueries({
        @NamedQuery(name = "ProjectExam.all", query = "select ex from ProjectExam ex order by ex.id"),
        @NamedQuery(name = "ProjectExam.byName", query = "select ex from ProjectExam ex where ex.name = :name")
})
public class ProjectExam extends Exam {
    @Column(name = "bibliography")
    private String bibliography;

    public ProjectExam() {
    }

    public ProjectExam(String name, Date data, int duration, String bibliography) {
        super(name, data, duration);
        this.bibliography = bibliography;
    }

    public ProjectExam(Exam exam, String bibliography) {
        super(exam.getName(), exam.getData(), exam.getDuration());
        this.bibliography = bibliography;
    }

    public String getBibliography() {
        return bibliography;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }
}
