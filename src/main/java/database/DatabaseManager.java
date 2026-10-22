package database;

import entities.Contact;
import entities.Conversation;
import interfaces.DaoStrong;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author May Fontenot (may@mayware.net)
 */
public class DatabaseManager {
    private static Connection connection;
    
    private static final DaoStrong<Integer, Contact> myContactDao = new MyContactDao(), contactDao = new ContactDao();
    private static final DaoStrong<Integer, Conversation> conversationDao = new ConversationDao();
    
    private static Contact myContact;
    private static List<Contact> myContacts;
    private static List<Conversation> myConversations;
    
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
    
    public static void initialize() {
        try {
            myContact = myContactDao.select().get(0);
            myContacts = contactDao.select();
            myConversations = conversationDao.select();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        if (myContact == null)
            myContact = new Contact("Jane Doe", "mincc.example.com", new byte[] {});
    }
    
    public static Contact getMyContact() {
        return myContact;
    }

    public static List<Contact> getMyContacts() {
        return myContacts;
    }

    public static List<Conversation> getMyConversations() {
        return myConversations;
    }
}
