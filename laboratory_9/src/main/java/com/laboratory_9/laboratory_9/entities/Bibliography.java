package com.laboratory_9.laboratory_9.entities;

import javax.persistence.*;

@Entity
@Table(name = "bibliography")
@NamedQueries({
        @NamedQuery(name = "Bibliography.all", query = "select bib from Bibliography bib")
})
public class Bibliography {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "documentid")
    private Long documentId;

    @Column(name = "referenceid")
    private Long referenceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }
}
