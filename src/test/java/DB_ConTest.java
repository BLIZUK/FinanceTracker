import java.sql.Connection;
import java.sql.DriverManager;

public class DB_ConTest {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "200503";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
