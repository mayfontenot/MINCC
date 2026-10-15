package database;

import entities.Contact;
import entities.Conversation;
import entities.Message;
import interfaces.DaoStrong;
import interfaces.DaoWeak;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao for conversation entity
 * 
 * @author May Fontenot (may@mayware.net)
 */
public class ConversationDao implements DaoStrong<Integer, Conversation> {
    private final DaoWeak<Integer, Integer, Contact> conversationRecipientDao = new ConversationRecipientDao();
    private final DaoWeak<Integer, Integer, Message> messageDao = new MessageDao();
    
    /**
     * Inserts tuple to the database
     * 
     * @param value, the tuple to be inserted
     * @throws SQLException 
     */
    @Override
    public void insert(Conversation value) throws SQLException {
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO conversation VALUES(?, ?)");
        ps.setInt(1, value.getConversationId());
        ps.setString(2, value.getName());
        ps.executeUpdate();
    }

    /**
     * Unsupported operation
     * 
     * @param value
     * @throws UnsupportedOperationException 
     */
    @Override
    public void update(Conversation value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Deletes tuple from the database
     * 
     * @param key of the tuple to be deleted
     * @throws SQLException 
     */
    @Override
    public void delete(Integer key) throws SQLException {
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("DELETE FROM conversation WHERE conversation_id = ?");
        ps.setInt(1, key);
        ps.executeUpdate();
    }

    /**
     * Selects the entity related to the tuple in the database
     * 
     * @param key of the tuple to be selected
     * @return the desired tuple
     * @throws SQLException 
     */
    @Override
    public Conversation select(Integer key) throws SQLException {
        Conversation value = null;
        
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM conversation WHERE conversation_id = ?");
        ps.setInt(1, key);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            List<Contact> recipients = conversationRecipientDao.select(key);
            List<Message> messages = messageDao.select(key);
        
            value = new Conversation(key, rs.getString(2), recipients, messages);
        }
        
        return value;
    }

    /**
     * Selects the entities related to the tuples in the database
     * 
     * @return the entities of the desired tuples
     * @throws SQLException 
     */
    @Override
    public List<Conversation> select() throws SQLException {
        List<Conversation> values = new ArrayList<>();
        
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM conversation");
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            Integer conversationPK = rs.getInt(1);
            List<Contact> recipients = conversationRecipientDao.select(conversationPK);
            List<Message> messages = messageDao.select(conversationPK);
        
            values.add(new Conversation(conversationPK, rs.getString(2), recipients, messages));
        }
        
        return values;
    }
}
