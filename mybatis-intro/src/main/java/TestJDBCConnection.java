import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBCConnection {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mybatis";
        String username ="root";
        String password = "vincent2017727";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connection established successfully.");

            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load JDBC Driver");

            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Failed to establish database connection.");

            throw new RuntimeException(e);
        }
    }
}
