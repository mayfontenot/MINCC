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
 *
 * @author azurith
 */
public class ConversationRecipientDao implements DaoWeak<Integer, Integer, Contact> {
    private final DaoStrong<Integer, Contact> contactDao = new ContactDao();
    
    @Override
    public void insert(Integer foreignKey, Contact value) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("INSERT INTO conversation_recipient VALUES(?, ?)");
        ps.setInt(1, foreignKey);
        ps.setInt(2, value.getContactId());
        ps.executeUpdate();
    }

    @Override
    public void update(Integer foreignKey, Contact value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer foreignKey, Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contact select(Integer foreignKey, Integer key) throws SQLException {
        Contact value = null;
        
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("SELECT * FROM conversation_recipient WHERE conversation_id = ? AND recipient_id = ?");
        ps.setInt(1, foreignKey);
        ps.setInt(2, key);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            value = contactDao.select(key);
        
        return value;
    }

    @Override
    public List<Contact> select(Integer foreignKey) throws SQLException {
        List<Contact> values = new ArrayList<>();
        
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("SELECT * FROM conversation_recipient WHERE conversation_id = ?");
        ps.setInt(1, foreignKey);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            values.add(contactDao.select(rs.getInt(2)));
        
        return values;
    }
}
