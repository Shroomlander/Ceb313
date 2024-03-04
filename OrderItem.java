// OrderItem.java
public class OrderItem {
    private Product product;
    private int quantity;
    private String clientName;

    // Constructor with arguments
    public OrderItem(Product product, int quantity, String clientName) {
        this.product = product;
        this.quantity = quantity;
        this.clientName = clientName;
    }

    // Getter methods
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getClientName() {
        return clientName;
    }
}
