package interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author azurith
 */
public interface DaoStrong<K, V> {
    public void insert(V value) throws SQLException;
    public void update(V value) throws SQLException;
    public void delete(K key) throws SQLException;
    public V select(K key) throws SQLException;
    public List<V> select() throws SQLException;
}
