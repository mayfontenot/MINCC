package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author azurith
 */
public class ConnectionSingleton {
    private static Connection connection;
    
    public static Connection getConnection() {
        if (connection == null)
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:P2PAsymCryptoChat.db");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        
        return connection;
    }
}
