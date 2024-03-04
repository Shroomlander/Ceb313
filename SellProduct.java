// SellProduct.java
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SellProduct {
    public static List<OrderItem> showDialog(JFrame parent, List<Product> products) {
        JTextField clientNameField = new JTextField();
        JTextField phoneNumberField = new JTextField();
        JTextField addressField = new JTextField();

        Object[] clientFields = {
                "Client Name:", clientNameField,
                "Phone Number:", phoneNumberField,
                "Address:", addressField
        };

        int clientOption = JOptionPane.showConfirmDialog(parent, clientFields, "Client Details", JOptionPane.OK_CANCEL_OPTION);

        if (clientOption == JOptionPane.OK_OPTION) {
            String clientName = clientNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String address = addressField.getText();

            List<OrderItem> orderItems = new ArrayList<>();
            boolean continueAddingProducts = true;

            while (continueAddingProducts) {
                JTextField productNameField = new JTextField();

                Object[] productFields = {
                        "Product Name:", productNameField
                };

                int productOption = JOptionPane.showConfirmDialog(parent, productFields, "Add Product to Order", JOptionPane.OK_CANCEL_OPTION);

                if (productOption == JOptionPane.OK_OPTION) {
                    String enteredProductName = productNameField.getText();

                    showAvailableNetWeights(parent, products, enteredProductName, orderItems, clientName);

                    int addMoreOption = JOptionPane.showConfirmDialog(parent, "Add more products to the order?", "Continue", JOptionPane.YES_NO_OPTION);
                    continueAddingProducts = (addMoreOption == JOptionPane.YES_OPTION);
                } else {
                    continueAddingProducts = false;
                }
            }

            showOrderSummary(parent, orderItems, clientName, phoneNumber, address);
            return orderItems;
        }

        return null;
    }

    private static void showAvailableNetWeights(JFrame parent, List<Product> products, String productName, List<OrderItem> orderItems, String clientName) {
        List<String> availableNetWeights = new ArrayList<>();

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName) && product.getQuantity() > 0) {
                availableNetWeights.add(product.getWeight() + " " + product.getWeightUnit());
            }
        }

        if (availableNetWeights.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Product not found or out of stock: " + productName, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JComboBox<String> netWeightComboBox = new JComboBox<>(availableNetWeights.toArray(new String[0]));

            Object[] netWeightFields = {
                    "Available Net Weights:", netWeightComboBox
            };

            int netWeightOption = JOptionPane.showConfirmDialog(parent, netWeightFields, "Select Net Weight", JOptionPane.OK_CANCEL_OPTION);

            if (netWeightOption == JOptionPane.OK_OPTION) {
                String selectedNetWeight = (String) netWeightComboBox.getSelectedItem();
                Product selectedProduct = findProductByNetWeight(products, productName, selectedNetWeight);

                if (selectedProduct != null) {
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog(parent, "Enter Quantity:", "Quantity", JOptionPane.PLAIN_MESSAGE));

                    if (quantity > 0 && quantity <= selectedProduct.getQuantity()) {
                        orderItems.add(new OrderItem(selectedProduct, quantity, clientName));
                        selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);
                    } else {
                        JOptionPane.showMessageDialog(parent, "Invalid quantity or insufficient stock.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private static Product findProductByNetWeight(List<Product> products, String productName, String netWeight) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName) && (product.getWeight() + " " + product.getWeightUnit()).equals(netWeight)) {
                return product;
            }
        }
        return null;
    }

    private static void showOrderSummary(JFrame parent, List<OrderItem> orderItems, String clientName, String phoneNumber, String address) {
        StringBuilder summary = new StringBuilder("Order Summary:\n");
        double totalAmount = 0;

        summary.append("Client Name: ").append(clientName).append("\n");
        summary.append("Phone Number: ").append(phoneNumber).append("\n");
        summary.append("Address: ").append(address).append("\n\n");

        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            double subtotal = product.getPrice() * orderItem.getQuantity();
            totalAmount += subtotal;

            summary.append("Product: ").append(product.getName())
                    .append(", Quantity: ").append(orderItem.getQuantity())
                    .append(", Subtotal: cfa").append(subtotal)
                    .append("\n");
        }

        summary.append("\nTotal Amount: cfa").append(totalAmount);

        JOptionPane.showMessageDialog(parent, summary.toString(), "Order Summary", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(summary.toString()); // Print to console
    }
}
