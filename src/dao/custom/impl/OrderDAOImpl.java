package dao.custom.impl;

import dao.custom.OrderDAO;
import dto.OrderDetailsDTO;
import entity.Order;
import entity.OrderDetails;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order order, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Order` VALUES (?,?,?)");

        stm.setString(1,order.getOrderID());
        stm.setString(2,order.getOrderDate());
        stm.setString(3,order.getCustID());


        if(stm.executeUpdate()>0){
            connection.close();
            return true;
        }else {
            return false;
        }


    }



    @Override
    public boolean delete(String s, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order order, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public JsonObjectBuilder search(String s, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public JsonArrayBuilder getAll(DataSource dataSource) throws SQLException, ClassNotFoundException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement("select * from `order`");
            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()){
                String oId = resultSet.getString(1);
                String cId = resultSet.getString(2);

                String oDate = resultSet.getString(3);


                objectBuilder.add("oId",oId);
                objectBuilder.add("cId",cId);
                objectBuilder.add("oDate",oDate);


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
