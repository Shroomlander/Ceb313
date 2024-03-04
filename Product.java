// Product.java

import java.util.Date;

public class Product {
    private String name;
    private int quantity;
    private double price;
    private double weight;
    private String weightUnit;
    private Date expiryDate;  // Add expiry date field

    public Product(String name, int quantity, double price, double weight, String weightUnit, Date expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.expiryDate = expiryDate;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
