package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public boolean addNewItem(ItemDTO dto, DataSource d) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(dto.getItemCode(), dto.getItemName(), dto.getQty(), dto.getUnitPrice()),d);
    }

    @Override
    public JsonArrayBuilder loadAllItem(DataSource d) throws SQLException, ClassNotFoundException {
       return itemDAO.getAll(d);
    }

    @Override
    public boolean deleteItem(String id, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id,dataSource);
    }

    @Override
    public boolean updateItem(ItemDTO i, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(i.getItemCode(),i.getItemName(),i.getQty(),i.getUnitPrice()),dataSource);
    }

    @Override
    public JsonObjectBuilder getItem(String id, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return null;
    }
}
