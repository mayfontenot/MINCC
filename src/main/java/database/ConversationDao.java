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
 *
 * @author azurith
 */
public class ConversationDao implements DaoStrong<Integer, Conversation> {
    private final DaoWeak<Integer, Integer, Contact> conversationRecipientDao = new ConversationRecipientDao();
    private final DaoWeak<Integer, Integer, Message> messageDao = new MessageDao();
    
    @Override
    public void insert(Conversation value) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("INSERT INTO conversation VALUES(?, ?)");
        ps.setInt(1, value.getConversationId());
        ps.setString(2, value.getName());
        ps.executeUpdate();
    }

    @Override
    public void update(Conversation value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer key) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("DELETE FROM conversation WHERE conversation_id = ?");
        ps.setInt(1, key);
        ps.executeUpdate();
    }

    @Override
    public Conversation select(Integer key) throws SQLException {
        Conversation value = null;
        
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("SELECT * FROM conversation WHERE conversation_id = ?");
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

    @Override
    public List<Conversation> select() throws SQLException {
        List<Conversation> values = new ArrayList<>();
        
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("SELECT * FROM conversation");
        
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
