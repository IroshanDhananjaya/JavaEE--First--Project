package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.OrderDAO;

import javax.json.JsonArrayBuilder;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class OrderBOImpl implements OrderBO {

    private final OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public JsonArrayBuilder loadAllOrder(DataSource d) throws SQLException, ClassNotFoundException {
        return orderDAO.getAll(d);
    }
}
