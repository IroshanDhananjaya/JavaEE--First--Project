package entity;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class OrderDetails {
     private String orderID;
     private String itemCode;
     private int orderQTY;
     private double itemPrice;
     private double total;

    public OrderDetails() {
    }

    public OrderDetails(String orderID, String itemCode, int orderQTY, double itemPrice, double total) {
        this.orderID = orderID;
        this.itemCode = itemCode;
        this.orderQTY = orderQTY;
        this.itemPrice = itemPrice;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
