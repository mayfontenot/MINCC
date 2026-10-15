package database;

import entities.Contact;
import entities.Message;
import interfaces.DaoStrong;
import interfaces.DaoWeak;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao for Message entity
 * 
 * @author May Fontenot (may@mayware.net)
 */
public class MessageDao implements DaoWeak<Integer, Integer, Message> {
    private final DaoStrong<Integer, Contact> contactDao = new ContactDao();
    
    /**
     * Inserts tuple to the database
     * 
     * @param foreignKey, the primary key of the entity in which the weak entity depends on
     * @param value, the tuple to be inserted
     * @throws SQLException 
     */
    @Override
    public void insert(Integer foreignKey, Message value) throws SQLException {
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO message VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setInt(1, value.getMessageId());
        ps.setInt(2, foreignKey);
        ps.setInt(3, value.getSender().getContactId());
        ps.setBytes(4, value.getContent());
        ps.setBytes(5, value.getSignature());
        ps.setString(6, value.getMimeType());
        ps.setString(7, value.getFileName());
        ps.setTimestamp(8, value.getTimeReceived());
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
    public void update(Integer foreignKey, Message value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Deletes tuple from the database
     * 
     * @param foreignKey, the primary key of the entity in which the weak entity depends on
     * @param key of the tuple to be deleted
     * @throws SQLException 
     */
    @Override
    public void delete(Integer foreignKey, Integer key) throws SQLException {
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("DELETE FROM message WHERE message_id = ? AND conversation_id = ?");
        ps.setInt(1, key);
        ps.setInt(2, foreignKey);
        ps.executeUpdate();
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
    public Message select(Integer foreignKey, Integer key) throws SQLException {
        Message value = null;
        
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM message WHERE message_id = ? AND conversation_id = ?");
        ps.setInt(1, key);
        ps.setInt(2, foreignKey);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            value = new Message(key, contactDao.select(rs.getInt(3)), rs.getBytes(4), rs.getBytes(5), rs.getString(6), rs.getString(7), rs.getTimestamp(8));
        
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
    public List<Message> select(Integer foreignKey) throws SQLException {
        List<Message> values = new ArrayList<>();
        
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM message WHERE conversation_id = ?");
        ps.setInt(1, foreignKey);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            values.add(new Message(rs.getInt(1), contactDao.select(rs.getInt(3)), rs.getBytes(4), rs.getBytes(5), rs.getString(6), rs.getString(7), rs.getTimestamp(8)));
        
        return values;
    }
}
