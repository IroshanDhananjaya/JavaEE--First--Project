package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import javafx.collections.ObservableList;

import javax.json.JsonArrayBuilder;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public interface CustomerBO extends SuperBO {
    boolean addNewCustomer(CustomerDTO dto ,DataSource d)throws SQLException, ClassNotFoundException ;
    JsonArrayBuilder loadAllCustomerforTable(DataSource d) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id,DataSource dataSource) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO  c,DataSource dataSource)throws SQLException, ClassNotFoundException;

}
