package dao.custom.impl;

import dao.custom.ItemDAO;
import entity.Customer;
import entity.Item;

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
public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(Item item, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");

        stm.setString(1,item.getItemCode());
        stm.setString(2,item.getItemName());
       stm.setInt(3, Integer.parseInt(item.getQty()));
        stm.setDouble(4,Double.parseDouble( item.getUnitPrice()));

        if(stm.executeUpdate()>0){
            connection.close();
            return true;
        }else {
            return false;
        }


    }

    @Override
    public boolean delete(String code, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        if (connection.prepareStatement("DELETE FROM item WHERE ItemCode='"+code+"'").executeUpdate()>0){
            connection.close();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(Item item, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stm = connection.prepareStatement("UPDATE item SET Description=?, qtyOnHand=?, unitPrice=? WHERE ItemCode=?");
        stm.setObject(1,item.getItemName());
        stm.setObject(2,Integer.parseInt(item.getQty()));
        stm.setObject(3,Double.parseDouble(item.getUnitPrice()));
        stm.setObject(4,item.getItemCode());

        if(stm.executeUpdate()>0){
            connection.close();
            return true;
        }else {
            return false;
        }
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
            PreparedStatement stm = connection.prepareStatement("select * from item");
            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()){
                String code = resultSet.getString(1);
                String name = resultSet.getString(2);
                String qty = resultSet.getString(3);
                String unitPrice = resultSet.getString(4);

                objectBuilder.add("code",code);
                objectBuilder.add("name",name);
                objectBuilder.add("qty",qty);
                objectBuilder.add("unitPrice",unitPrice);

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
