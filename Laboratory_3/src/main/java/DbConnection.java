import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }
}
