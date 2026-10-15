package interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO for strong entities
 * 
 * @author May Fontenot (may@mayware.net)
 * @param <K> key
 * @param <V> value
 */
public interface DaoStrong<K, V> {
    /**
     * Inserts tuple to the database
     * 
     * @param value, the tuple to be inserted
     * @throws SQLException 
     */
    public void insert(V value) throws SQLException;
    
    /**
     * Updates tuple in the database
     * 
     * @param value, the tuple to be updated
     * @throws SQLException 
     */
    public void update(V value) throws SQLException;
    
    /**
     * Deletes tuple from the database
     * 
     * @param key of the tuple to be deleted
     * @throws SQLException 
     */
    public void delete(K key) throws SQLException;
    
    /**
     * Selects the entity related to the tuple in the database
     * 
     * @param key of the tuple to be selected
     * @return the desired tuple
     * @throws SQLException 
     */
    public V select(K key) throws SQLException;
    
    /**
     * Selects the entities related to the tuples in the database
     * 
     * @return the entities of the desired tuples
     * @throws SQLException 
     */
    public List<V> select() throws SQLException;
}
