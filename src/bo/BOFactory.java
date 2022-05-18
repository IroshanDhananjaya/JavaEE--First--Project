package bo;



import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.ItemBOImpl;
import bo.custom.impl.OrderBOImpl;
import bo.custom.impl.OrderDetailsBOImpl;


public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case Customer:
                return new CustomerBOImpl();
            case Item:
                return new ItemBOImpl();
            case Order:
                return new OrderBOImpl();
            case OrderDetails:
                return new OrderDetailsBOImpl();

                default:
                return null;
        }
    }

    public enum BoTypes {
      Order,Customer,Item,OrderDetails
    }
}
