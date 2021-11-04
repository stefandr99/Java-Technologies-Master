import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.List;

@ManagedBean(name = "student")
public class Student {
    private String name;

    private ExamCheckbox exams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExamCheckbox getExams() {
        return exams;
    }

    public void setExams(ExamCheckbox exams) {
        this.exams = exams;
    }

    private void populateExams() {
        try(Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, data, duration FROM exams"))
        {
            while(rs.next()){
                Exam exam = new Exam(rs.getInt("id"), rs.getString("name"), rs.getDate("data"), rs.getInt("duration"));
                exams.getExamList().add(exam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String addStudentView() {
        populateExams();
        return "addStudent";
    }

    public String addStudent() {
        Connection conn = DbConnection.getConnection();
        int studentId = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into students(Name) values(?)");
            stmt.setString(1, name);
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    studentId = generatedKeys.getInt(1);
                }
            }
            for (int i = 0; i < exams.getSelectedExams().length; i++) {
                stmt = conn.prepareStatement("insert into schedule(examId, studentId) values(?,?)");
                stmt.setInt(1, exams.getSelectedExams()[i].getId());
                stmt.setInt(2, studentId);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "addStudent";
    }
}
