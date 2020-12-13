package model;

public class Customer {

    private long customerId;
    private String customerName;
    private String customerGender;
    private String customerPhoneNumber;
    private String customerAddress;

    public Customer() {
    }

    public Customer(long customerId, String customerName, String customerGender, String customerPhoneNumber, String customerAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerGender = customerGender;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAddress = customerAddress;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }


    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }


    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

}
