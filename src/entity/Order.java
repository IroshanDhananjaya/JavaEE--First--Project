package entity;

import java.util.ArrayList;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class Order {
    private String orderID;
    private String orderDate;
    private String custID;
    private String total;
    private ArrayList<OrderDetails> items;

    public Order() {
    }

    public Order(String orderID, String orderDate, String custID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.custID = custID;
    }

    public Order(String orderID, String orderDate, String custID, String total, ArrayList<OrderDetails> items) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.custID = custID;
        this.total = total;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", custID='" + custID + '\'' +
                ", total='" + total + '\'' +
                ", items=" + items +
                '}';
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public ArrayList<OrderDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderDetails> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
