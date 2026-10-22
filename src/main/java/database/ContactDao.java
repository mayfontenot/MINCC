package database;

import entities.Contact;
import interfaces.DaoStrong;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao for contact entity
 * 
 * @author May Fontenot (may@mayware.net)
 */
public class ContactDao implements DaoStrong<Integer, Contact> {
    /**
     * Inserts tuple to the database
     * 
     * @param value, the tuple to be inserted
     * @throws SQLException 
     */
    @Override
    public void insert(Contact value) throws SQLException {
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO contact VALUES(?, ?, ?, ?)");
        ps.setInt(1, value.getContactId());
        ps.setString(2, value.getName());
        ps.setString(3, value.getDomain());
        ps.setBytes(4, value.getPublicKey());
        ps.executeUpdate();
    }

    /**
     * Updates tuple in the database
     * 
     * @param value, the tuple to be updated
     * @throws SQLException 
     */
    @Override
    public void update(Contact value) throws SQLException {
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE contact SET name = ?, domain = ?, public_key_data = ? WHERE contact_id = ?");
        ps.setString(1, value.getName());
        ps.setString(2, value.getDomain());
        ps.setBytes(3, value.getPublicKey());
        ps.setInt(4, value.getContactId());
        ps.executeUpdate();
    }

    /**
     * Deletes tuple from the database
     * 
     * @param key of the tuple to be deleted
     * @throws SQLException 
     */
    @Override
    public void delete(Integer key) throws SQLException {
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("DELETE FROM contact WHERE contact_id = ?");
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
    public Contact select(Integer key) throws SQLException {
        Contact value = null;
        
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM contact WHERE contact_id = ?");
        ps.setInt(1, key);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            value = new Contact(key, rs.getString(2), rs.getString(3), rs.getBytes(4));
        
        return value;
    }

    /**
     * Selects the entities related to the tuples in the database
     * 
     * @return the entities of the desired tuples
     * @throws SQLException 
     */
    @Override
    public List<Contact> select() throws SQLException {
        List<Contact> values = new ArrayList<>();
        
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM contact ORDER BY name");
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            values.add(new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBytes(4)));
        
        return values;
    }
}
