package com.fii.laboratory_6.repositories;

import com.fii.laboratory_6.entities.ProjectExam;
import com.fii.laboratory_6.entities.Student;
import com.fii.laboratory_6.entities.WrittenExam;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
public class StudentRepository {
    @PersistenceContext(unitName = "com.jpaPU")
    private EntityManager em;

    @EJB
    GenericExamRepository<WrittenExam> writtenExamGenericExamRepository;

    @EJB
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
