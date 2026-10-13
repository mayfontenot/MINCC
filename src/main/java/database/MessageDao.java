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
 *
 * @author azurith
 */
public class MessageDao implements DaoWeak<Integer, Integer, Message> {
    private final DaoStrong<Integer, Contact> contactDao = new ContactDao();
    
    @Override
    public void insert(Integer foreignKey, Message value) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("INSERT INTO message VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
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

    @Override
    public void update(Integer foreignKey, Message value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer foreignKey, Integer key) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("DELETE FROM message WHERE message_id = ? AND conversation_id = ?");
        ps.setInt(1, key);
        ps.setInt(2, foreignKey);
        ps.executeUpdate();
    }

    @Override
    public Message select(Integer foreignKey, Integer key) throws SQLException {
        Message value = null;
        
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("SELECT * FROM message WHERE message_id = ? AND conversation_id = ?");
        ps.setInt(1, key);
        ps.setInt(2, foreignKey);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            value = new Message(key, contactDao.select(rs.getInt(3)), rs.getBytes(4), rs.getBytes(5), rs.getString(6), rs.getString(7), rs.getTimestamp(8));
        
        return value;
    }

    @Override
    public List<Message> select(Integer foreignKey) throws SQLException {
        List<Message> values = new ArrayList<>();
        
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("SELECT * FROM message WHERE conversation_id = ?");
        ps.setInt(1, foreignKey);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            values.add(new Message(rs.getInt(1), contactDao.select(rs.getInt(3)), rs.getBytes(4), rs.getBytes(5), rs.getString(6), rs.getString(7), rs.getTimestamp(8)));
        
        return values;
    }
}
