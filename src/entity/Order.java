package entity;

import dto.OrderDetailsDTO;

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
    private ArrayList<OrderDetailsDTO> items;

    public Order() {
    }

    public Order(String orderID, String orderDate, String custID, String total) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.custID = custID;
        this.total = total;
    }

    public Order(String orderID, String orderDate, String custID, String total, ArrayList<OrderDetailsDTO> items) {
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

    public ArrayList<OrderDetailsDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderDetailsDTO> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
