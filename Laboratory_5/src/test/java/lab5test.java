import com.fii.laboratory_5.entities.Exam;
import com.fii.laboratory_5.repositories.ExamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class lab5test {
    ExamRepository examRepository;

    public lab5test() {
    }

    @BeforeEach
    public void setUp() {
        examRepository = mock(ExamRepository.class);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void getShouldReturnCorrectExams() {
        List<Exam> examList = new ArrayList<>();
        examList.add(new Exam("Java EE", new Date(System.currentTimeMillis()), 45));
        examList.add(new Exam("Java SE", new Date(System.currentTimeMillis()), 60));

        when(examRepository.get()).thenReturn(examList);

        List<Exam> result = examRepository.get();
        assertEquals(examList, result);
    }

    @Test
    public void getByNameShouldReturnCorrectExam() {
        Exam exam = new Exam("Java EE", new Date(System.currentTimeMillis()), 45);

        when(examRepository.getByName("Java EE")).thenReturn(exam);

        Exam result = examRepository.getByName("Java EE");
        assertEquals(exam, result);
    }

    @Test
    public void createShouldReturnExamWithId() {
        Exam exam = new Exam("Java EE", new Date(System.currentTimeMillis()), 45);
        Exam exam1 = exam;
        exam1.setId(1L);
        when(examRepository.create(exam)).thenReturn(exam1);

        Exam result = examRepository.create(exam);
        assertEquals(exam1, result);
    }
}