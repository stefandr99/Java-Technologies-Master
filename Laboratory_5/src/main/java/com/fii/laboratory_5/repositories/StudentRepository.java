package com.fii.laboratory_5.repositories;

import com.fii.laboratory_5.entities.Exam;
import com.fii.laboratory_5.entities.Student;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class StudentRepository {
    @PersistenceContext(unitName = "com.jpaPU")
    private EntityManager em;

    @Inject
    ExamRepository examRepository;

    @Transactional
    public Student create(Student student, String[] assignedExams) {

        for (String examString: assignedExams) {
            String[] words = examString.split(",");
            Exam exam = examRepository.getByName(words[0]);
            student.getExams().add(exam);
        }

        em.persist(student);
        em.flush();

        return student;
    }

    public List<Student> get() {
        return em.createNamedQuery("Student.all", Student.class).getResultList();
    }
}
