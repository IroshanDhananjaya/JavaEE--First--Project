package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import entity.Order;
import entity.OrderDetails;

import javax.json.JsonArrayBuilder;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class OrderBOImpl implements OrderBO {

    private final OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    @Override
    public JsonArrayBuilder loadAllOrder(DataSource d) throws SQLException, ClassNotFoundException {
        return orderDAO.getAll(d);
    }

    @Override
    public boolean placeOrder(OrderDTO dto, DataSource d)throws SQLException, ClassNotFoundException {
        Connection connection = null;

        try {
            connection=d.getConnection();
            connection.setAutoCommit(false);

            if(orderDAO.add(new Order(dto.getOrderID(), dto.getOrderDate(), dto.getCustID(), dto.getTotal()),d)){
                for (OrderDetailsDTO temp:dto.getItems()) {
                    OrderDetails orderDetails=new OrderDetails(temp.getOrderID(), temp.getItemCode(), temp.getOrderQTY(), temp.getItemPrice(), temp.getTotal());
                    if(orderDetailsDAO.add(orderDetails,d)){
                        connection.commit();
                        return true;
                    }else {
                        connection.rollback();
                        return false;
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
