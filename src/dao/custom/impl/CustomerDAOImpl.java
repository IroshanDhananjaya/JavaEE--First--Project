package dao.custom.impl;

import dao.custom.CustomerDAO;
import entity.Customer;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class CustomerDAOImpl implements CustomerDAO {
  /*  @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;*/

    @Override
    public boolean add(Customer customer,DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?)");

        stm.setString(1,customer.getCustId());
        stm.setString(2,customer.getCustName());
        stm.setString(3,customer.getAddress());
        stm.setString(4,customer.getSalary());

        if(stm.executeUpdate()>0){
            connection.close();
            return true;
        }else {
            return false;
        }



    }

    @Override
    public boolean delete(String id,DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        if (connection.prepareStatement("DELETE FROM Customer WHERE CustID='"+id+"'").executeUpdate()>0){
            connection.close();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(Customer c,DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stm = connection.prepareStatement("UPDATE Customer SET CustName=?, CustAddress=?, Salary=? WHERE CustID=?");
        stm.setObject(1,c.getCustName());
        stm.setObject(2,c.getAddress());
        stm.setObject(3,c.getSalary());
        stm.setObject(4,c.getCustId());

        if(stm.executeUpdate()>0){
            connection.close();
            return true;
        }else {
            return false;
        }
    }

    @Override
    public JsonObjectBuilder search(String s, DataSource dataSource) throws SQLException, ClassNotFoundException {


        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        Connection connection = dataSource.getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * From Customer WHERE CustID=?");
        stm.setString(1, s);
        ResultSet rst = stm.executeQuery();
        Customer customer;
        while (rst.next()) {

            String id = rst.getString(1);
            String name = rst.getString(2);
            String address = rst.getString(3);
            String salary = rst.getString(4);

            objectBuilder.add("id",id);
            objectBuilder.add("name",name);
            objectBuilder.add("address",address);
            objectBuilder.add("salary",salary);



        }
        connection.close();
        return objectBuilder;
    }

    @Override
    public JsonArrayBuilder getAll(DataSource dataSource) throws SQLException, ClassNotFoundException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement("select * from customer");
            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String salary = resultSet.getString(4);

                objectBuilder.add("id",id);
                objectBuilder.add("name",name);
                objectBuilder.add("address",address);
                objectBuilder.add("salary",salary);

                JsonObject build = objectBuilder.build();
                arrayBuilder.add(build);
            }

            connection.close();

           return arrayBuilder;




        } catch (SQLException e) {
            e.printStackTrace();
        }


          return null;
    }

}
