package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/reserva";
            String username = "root";
            String password = "";
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
