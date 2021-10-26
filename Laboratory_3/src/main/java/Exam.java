import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

@ManagedBean(name = "exam")
public class Exam implements Serializable {
    private int id;

    private String name;

    private Date date;

    private int duration;

    public Exam() {
    }

    public Exam(String name, Date date, int duration) {
        this.name = name;
        this.date = date;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String addExam() {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Connection conn = DbConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into Exams(Name, Data, Duration) values(?,?,?)");
            stmt.setString(1,name);
            stmt.setDate(2,sqlDate);
            stmt.setInt(3,duration);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "successAddedExam";
    }
}
