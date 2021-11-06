package com.fii.laboratory_5.repositories;

import com.fii.laboratory_5.entities.Exam;
import com.fii.laboratory_5.entities.ProjectExam;
import com.fii.laboratory_5.entities.Student;
import com.fii.laboratory_5.entities.WrittenExam;

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

    @Inject
    GenericExamRepository<WrittenExam> writtenExamGenericExamRepository;

    @Inject
    GenericExamRepository<ProjectExam> projectExamGenericExamRepository;

    @Transactional
    public Student create(Student student, String[] writtenAssignedExams, String[] projectAssignedExams) {

        for (String examString: writtenAssignedExams) {
            String[] words = examString.split(",");
            WrittenExam exam = writtenExamGenericExamRepository.getWrittenByName(words[0]);
            student.getWrittenExams().add(exam);
        }

        for (String examString: projectAssignedExams) {
            String[] words = examString.split(",");
            ProjectExam exam = projectExamGenericExamRepository.getProjectByName(words[0]);
            student.getProjectExams().add(exam);
        }

        em.persist(student);
        em.flush();

        return student;
    }

    public List<Student> get() {
        return em.createNamedQuery("Student.all", Student.class).getResultList();
    }
}
