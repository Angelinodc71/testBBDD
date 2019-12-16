package TestBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
    public App() {
    }
    public Connection getConnection() {
        Connection connection = null;
        try {
             connection=DriverManager.getConnection("jdbc:mysql://192.168.22.220:3306/angelDeCaldasEscola", "root", "#Password0");
            System.out.println("BBDD Connected");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    public static void main(String[] args) {
        App app = new App();
        Connection c = app.getConnection();
    }
}
