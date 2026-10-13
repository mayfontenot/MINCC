package interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author azurith
 */
public interface DaoWeak<FK, K, V> {
    public void insert(FK foreignKey, V value) throws SQLException;
    public void update(FK foreignKey, V value) throws SQLException;
    public void delete(FK foreignKey, K key) throws SQLException;
    public V select(FK foreignKey, K key) throws SQLException;
    public List<V> select(FK foreignKey) throws SQLException;
}