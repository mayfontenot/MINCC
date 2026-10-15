package interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO for weak entities
 * 
 * @author May Fontenot (may@mayware.net)
 * @param <FK> foreign key, the primary key of the entity in which the weak entity depends on
 * @param <K> key
 * @param <V> value
 */
public interface DaoWeak<FK, K, V> {
    /**
     * Inserts tuple to the database
     * 
     * @param foreignKey, the primary key of the entity in which the weak entity depends on
     * @param value, the tuple to be inserted
     * @throws SQLException 
     */
    public void insert(FK foreignKey, V value) throws SQLException;
    
    /**
     * Updates tuple in the database
     * 
     * @param foreignKey, the primary key of the entity in which the weak entity depends on
     * @param value, the tuple to be updated
     * @throws SQLException 
     */
    public void update(FK foreignKey, V value) throws SQLException;
    
    
    /**
     * Deletes tuple from the database
     * 
     * @param foreignKey, the primary key of the entity in which the weak entity depends on
     * @param key of the tuple to be deleted
     * @throws SQLException 
     */
    public void delete(FK foreignKey, K key) throws SQLException;
    
    /**
     * Selects the entity related to the tuple in the database
     * 
     * @param foreignKey, the primary key of the entity in which the weak entity depends on
     * @param key of the tuple to be selected
     * @return the desired tuple
     * @throws SQLException 
     */
    public V select(FK foreignKey, K key) throws SQLException;
    
    /**
     * Selects the entities related to the tuples in the database
     * 
     * @param foreignKey, the primary key of the entity in which the weak entity depends on
     * @return the entities of the desired tuples
     * @throws SQLException 
     */
    public List<V> select(FK foreignKey) throws SQLException;
}