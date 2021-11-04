import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "examCheckbox")
public class ExamCheckbox {
    private Exam[] selectedExams;

    private List<Exam> examList;

    public ExamCheckbox() {
        this.selectedExams = new Exam[]{};
        this.examList = new ArrayList<>();
    }

    public Exam[] getSelectedExams() {
        return selectedExams;
    }

    public void setSelectedExams(Exam[] selectedExams) {
        this.selectedExams = selectedExams;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    private void populateExams() {
        try(Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, data, duration FROM exams"))
        {
            while(rs.next()){
                Exam exam = new Exam(rs.getInt("id"), rs.getString("name"), rs.getDate("data"), rs.getInt("duration"));
                examList.add(exam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String showExams() {
        populateExams();
        return "showExams";
    }
}
