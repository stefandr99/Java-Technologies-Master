package com.fii.laboratory_5.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "writtenexams")
@NamedQueries({
        @NamedQuery(name = "WrittenExam.all", query = "select ex from WrittenExam ex order by ex.id"),
        @NamedQuery(name = "WrittenExam.byName", query = "select ex from WrittenExam ex where ex.name = :name")
})
public class WrittenExam extends Exam {
    @Column(name = "pages")
    private int pages;

    public WrittenExam(String name, Date data, int duration, int pages) {
        super(name, data, duration);
        this.pages = pages;
    }

    public WrittenExam(Exam exam, int pages) {
        super(exam.getName(), exam.getData(), exam.getDuration());
        this.pages = pages;
    }

    public WrittenExam() {

    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
