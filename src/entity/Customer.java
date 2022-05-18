package entity;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/
public class Customer {
    private String custId;
    private String custName;
    private String Address;
    private String Salary;

    public Customer() {
    }

    public Customer(String custId, String custName, String address, String salary) {
        this.custId = custId;
        this.custName = custName;
        Address = address;
        Salary = salary;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }
}
