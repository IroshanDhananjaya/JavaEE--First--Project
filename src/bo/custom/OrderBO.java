package bo.custom;

import bo.SuperBO;

import javax.json.JsonArrayBuilder;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public interface OrderBO extends SuperBO {
    JsonArrayBuilder loadAllOrder(DataSource d) throws SQLException, ClassNotFoundException;
}
