package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author May Fontenot (may@mayware.net)
 */
public class DatabaseManager {
    private static Connection connection;
    
    /**
     * Connection singleton, creates the connection only once if doesn't exist, and returns it.
     * @return the connection
     */
    public static Connection getConnection() {
        if (connection == null)
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:MINCC.db");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        
        return connection;
    }
}
