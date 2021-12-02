package com.fii.laboratory_7.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "documents2")
@NamedQueries({
        @NamedQuery(name = "Document2.all", query = "select doc from Document2 doc join doc.user us order by doc.id"),
        @NamedQuery(name = "Document2.byName", query = "select doc from Document2 doc where doc.name = :name")
})
public class Document2 implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "doc")
    private byte[] doc;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(name = "registration_number")
    private String RegistrationNumber;

    public Document2() {
    }

    public Document2(String name, byte[] bytes, User user, String registrationNumber) {
        this.name = name;
        this.doc = bytes;
        this.user = user;
        this.RegistrationNumber = registrationNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDoc() {
        return doc;
    }

    public void setDoc(byte[] doc) {
        this.doc = doc;
    }

    public String getRegistrationNumber() {
        return RegistrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        RegistrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

