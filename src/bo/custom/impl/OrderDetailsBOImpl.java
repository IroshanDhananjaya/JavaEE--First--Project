package bo.custom.impl;

import bo.custom.OrderDetailsBO;
import dao.DAOFactory;
import dao.custom.OrderDetailsDAO;

import javax.json.JsonArrayBuilder;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class OrderDetailsBOImpl implements OrderDetailsBO {

    private final OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public JsonArrayBuilder loadAllOrderDetails(DataSource d) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.getAll(d);
    }
}
