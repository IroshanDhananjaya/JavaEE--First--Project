package bo.custom.impl;

import bo.BOFactory;
import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.json.JsonArrayBuilder;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    @Override
    public boolean addNewCustomer(CustomerDTO dto, DataSource d) throws SQLException, ClassNotFoundException {
       return customerDAO.add(new Customer(dto.getCustId(), dto.getCustName(), dto.getAddress(), dto.getSalary()),d);
    }

    @Override
    public JsonArrayBuilder loadAllCustomerforTable(DataSource d) throws SQLException, ClassNotFoundException {
    return customerDAO.getAll(d);
    }

    @Override
    public boolean deleteCustomer(String id, DataSource dataSource) throws SQLException, ClassNotFoundException {
       return customerDAO.delete(id,dataSource);
    }

}
