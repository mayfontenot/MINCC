package database;

import entities.Contact;
import interfaces.DaoStrong;
import interfaces.DaoWeak;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao for ConversationRecipient entity
 * 
 * @author May Fontenot (may@mayware.net)
 */
public class ConversationRecipientDao implements DaoWeak<Integer, Integer, Contact> {
    private final DaoStrong<Integer, Contact> contactDao = new ContactDao();
    
    /**
     * Inserts tuple to the database
     * 
     * @param foreignKey, the primary key of the entity in which the weak entity depends on
     * @param value, the tuple to be inserted
     * @throws SQLException 
     */
    @Override
    public void insert(Integer foreignKey, Contact value) throws SQLException {
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO conversation_recipient VALUES(?, ?)");
        ps.setInt(1, foreignKey);
        ps.setInt(2, value.getContactId());
        ps.executeUpdate();
    }

    /**
     * Unsupported operation
     * 
     * @param foreignKey
     * @param value
     * @throws UnsupportedOperationException
     */
    @Override
    public void update(Integer foreignKey, Contact value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported operation
     * 
     * @param foreignKey
     * @param key
     * @throws UnsupportedOperationException 
     */
    @Override
    public void delete(Integer foreignKey, Integer key) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Selects the entity related to the tuple in the database
     * 
     * @param foreignKey, the primary key of the entity in which the weak entity depends on
     * @param key of the tuple to be selected
     * @return the desired tuple
     * @throws SQLException 
     */
    @Override
    public Contact select(Integer foreignKey, Integer key) throws SQLException {
        Contact value = null;
        
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM conversation_recipient WHERE conversation_id = ? AND recipient_id = ?");
        ps.setInt(1, foreignKey);
        ps.setInt(2, key);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            value = contactDao.select(key);
        
        return value;
    }

    /**
     * Selects the entities related to the tuples in the database
     * 
     * @param foreignKey, the primary key of the entity in which the weak entity depends on
     * @return the entities of the desired tuples
     * @throws SQLException 
     */
    @Override
    public List<Contact> select(Integer foreignKey) throws SQLException {
        List<Contact> values = new ArrayList<>();
        
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM conversation_recipient WHERE conversation_id = ?");
        ps.setInt(1, foreignKey);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            values.add(contactDao.select(rs.getInt(2)));
        
        return values;
    }
}
