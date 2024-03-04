// AddProduct.java
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProduct {
    public static Product addProduct(JFrame parent) {
        JTextField productNameField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField weightField = new JTextField();
        JComboBox<String> weightUnitComboBox = new JComboBox<>(new String[]{"kg", "l"});
        JTextField expiryDateField = new JTextField();  // Add expiry date field

        Object[] fields = {
                "Product Name:", productNameField,
                "Quantity:", quantityField,
                "Price:", priceField,
                "Weight:", weightField,
                "Weight Unit:", weightUnitComboBox,
                "Expiry Date (yyyy-MM-dd):", expiryDateField  // Add expiry date input
        };

        int option = JOptionPane.showConfirmDialog(parent, fields, "Add Product", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String productName = productNameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                double weight = Double.parseDouble(weightField.getText());
                String weightUnit = (String) weightUnitComboBox.getSelectedItem();
                Date expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(expiryDateField.getText());

                Product newProduct = new Product(productName, quantity, price, weight, weightUnit, expiryDate);

                // Check for expiration and remove from available stock if expired
                if (isExpired(newProduct)) {
                    JOptionPane.showMessageDialog(parent, "Product is expired and will not be added.");
                    return null;
                }

                return newProduct;
            } catch (NumberFormatException | ParseException e) {
                JOptionPane.showMessageDialog(parent, "Invalid input. Please enter valid values.");
            }
        }

        return null;
    }

    private static boolean isExpired(Product product) {
        Date currentDate = new Date();
        return product.getExpiryDate() != null && product.getExpiryDate().before(currentDate);
    }
}
