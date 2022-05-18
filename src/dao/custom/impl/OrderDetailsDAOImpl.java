package dao.custom.impl;

import dao.custom.OrderDetailsDAO;
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

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(OrderDetails orderDetails, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Order Details` VALUES (?,?,?,?,?)");

        stm.setString(1,orderDetails.getOrderID());
        stm.setString(2,orderDetails.getItemCode());
        stm.setInt(3,orderDetails.getOrderQTY());
        stm.setDouble(4,orderDetails.getItemPrice());
        stm.setDouble(5,orderDetails.getTotal());

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
    public boolean update(OrderDetails orderDetails, DataSource dataSource) throws SQLException, ClassNotFoundException {
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
            PreparedStatement stm = connection.prepareStatement("select * from `Order Details`");
            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()){
                String itemCode = resultSet.getString(1);
                String oId = resultSet.getString(2);
                int oQty = resultSet.getInt(3);
                double total = resultSet.getDouble(4);
                double iPrice = resultSet.getDouble(5);

                objectBuilder.add("itemCode",itemCode);
                objectBuilder.add("oId",oId);
                objectBuilder.add("oQty",oQty);
                objectBuilder.add("total",total);
                objectBuilder.add("iPrice",iPrice);

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
