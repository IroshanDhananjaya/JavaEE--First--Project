package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public interface ItemBO extends SuperBO {
    boolean addNewItem(ItemDTO dto , DataSource d)throws SQLException, ClassNotFoundException ;
    JsonArrayBuilder loadAllItem(DataSource d) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id,DataSource dataSource) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO i,DataSource dataSource)throws SQLException, ClassNotFoundException;
    JsonObjectBuilder getItem(String id, DataSource dataSource)throws SQLException, ClassNotFoundException;
}
