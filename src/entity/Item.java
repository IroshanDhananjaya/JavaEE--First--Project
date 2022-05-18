package entity;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class Item {

    private String itemCode;
    private String itemName;
    private String qty;
    private String unitPrice;

    public Item() {
    }

    public Item(String itemCode, String itemName, String qty, String unitPrice) {
        this.setItemCode(itemCode);
        this.setItemName(itemName);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
