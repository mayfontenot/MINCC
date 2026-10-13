package database;

import entities.Contact;
import interfaces.DaoStrong;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azurith
 */
public class MyContactDao implements DaoStrong<Integer, Contact> {
    @Override
    public void insert(Contact value) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("INSERT INTO my_contact VALUES(?, ?, ?, ?)");
        ps.setInt(1, value.getContactId());
        ps.setString(2, value.getName());
        ps.setString(3, value.getDomain());
        ps.setBytes(4, value.getPublicKey());
        ps.executeUpdate();
    }

    @Override
    public void update(Contact value) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("UPDATE my_contact SET name = ?, domain = ?, public_key_data = ? WHERE contact_id = ?");
        ps.setString(1, value.getName());
        ps.setString(2, value.getDomain());
        ps.setBytes(3, value.getPublicKey());
        ps.setInt(4, value.getContactId());
        ps.executeUpdate();
    }

    @Override
    public void delete(Integer key) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("DELETE FROM my_contact WHERE contact_id = ?");
        ps.setInt(1, key);
        ps.executeUpdate();
    }

    @Override
    public Contact select(Integer key) throws SQLException {
        Contact value = null;
        
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("SELECT * FROM my_contact WHERE contact_id = ?");
        ps.setInt(1, key);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            value = new Contact(key, rs.getString(2), rs.getString(3), rs.getBytes(4));
        
        return value;
    }

    @Override
    public List<Contact> select() throws SQLException {
        List<Contact> values = new ArrayList<>();
        
        PreparedStatement ps = ConnectionSingleton.getConnection().prepareStatement("SELECT * FROM my_contact");
        
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            values.add(new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBytes(4)));
        
        return values;
    }
}
