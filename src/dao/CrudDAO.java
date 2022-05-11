package dao;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public interface CrudDAO <T,ID> extends SuperDAO{

    boolean add(T t,DataSource dataSource) throws SQLException, ClassNotFoundException;

    boolean delete(ID id,DataSource dataSource) throws SQLException, ClassNotFoundException;

    boolean update(T t,DataSource dataSource) throws SQLException, ClassNotFoundException;

    JsonObjectBuilder search(ID id, DataSource dataSource) throws SQLException, ClassNotFoundException;

    JsonArrayBuilder getAll(DataSource dataSource) throws SQLException, ClassNotFoundException;

}
