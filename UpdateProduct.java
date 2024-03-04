// UpdateProduct.java
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;



public class UpdateProduct {
    public static void main(String[] args) {
        // Assuming you have products
    List<Product> products = new ArrayList<>();
        // Update a product
        updateProduct(new JFrame(), products);
    }

    public static void updateProduct(JFrame parent, List<Product> products) {
        JTextField productNameField = new JTextField();
        JTextField weightField = new JTextField();
        JComboBox<String> weightUnitComboBox = new JComboBox<>(new String[]{"kg", "l"});
        JTextField newQuantityField = new JTextField();
        JTextField newPriceField = new JTextField();

        Object[] fields = {
                "Product Name:", productNameField,
                "Weight:", weightField,
                "Weight Unit:", weightUnitComboBox,
                "New Quantity:", newQuantityField,
                "New Price:", newPriceField
        };

        int option = JOptionPane.showConfirmDialog(parent, fields, "Update Product", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String productName = productNameField.getText();
            double weight = Double.parseDouble(weightField.getText());
            String weightUnit = (String) weightUnitComboBox.getSelectedItem();
            int newQuantity = Integer.parseInt(newQuantityField.getText());
            double newPrice = Double.parseDouble(newPriceField.getText());

            for (Product product : products) {
                if (product.getName().equals(productName) && product.getWeight() == weight &&
                    product.getWeightUnit().equals(weightUnit)) {
                    // Update the product
                    product.setQuantity(newQuantity);
                    product.setPrice(newPrice);

                    // Display a message indicating the update
                    JOptionPane.showMessageDialog(parent, "Product updated: " + product.getName() +
                            " Quantity: " + product.getQuantity() +
                            " New Price: cfa" + product.getPrice());
                    return;
                }
            }

            // If the product is not found
            JOptionPane.showMessageDialog(parent, "Product not found.");
        }
    }
}
