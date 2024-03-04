import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {

    private List<Product> inventory;
    private Map<String, List<OrderItem>> ordersByClient;

    public Main() {
        inventory = new ArrayList<>();
        ordersByClient = new HashMap<>();

        setTitle("Inventory Management System");
        setSize(585, 585);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton addButton = new JButton("Add Product");
        addButton.setBounds(10, 20, 175, 25);
        panel.add(addButton);

        JButton sellButton = new JButton("Sell Product");
        sellButton.setBounds(195, 20, 175, 25);
        panel.add(sellButton);

        JButton updateButton = new JButton("Update Product");
        updateButton.setBounds(380, 20, 175, 25);
        panel.add(updateButton);

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.setBounds(10, 60, 175, 25);
        panel.add(generateReportButton);

        JButton stockAlertButton = new JButton("Stock and Expiry Alert");
        stockAlertButton.setBounds(195, 60, 175, 25);
        panel.add(stockAlertButton);

        JButton viewOrdersButton = new JButton("View Orders");
        viewOrdersButton.setBounds(380, 60, 175, 25);
        panel.add(viewOrdersButton);

        JTextArea reportArea = new JTextArea();
        reportArea.setBounds(10, 120, 545, 400);
        panel.add(reportArea);

        addButton.addActionListener(e -> addProduct());
        sellButton.addActionListener(e -> sellProduct());
        updateButton.addActionListener(e -> updateProduct());
        generateReportButton.addActionListener(e -> generateReport(reportArea));
        stockAlertButton.addActionListener(e -> checkAndNotifyStockAndExpiryAlert(reportArea));
        viewOrdersButton.addActionListener(e -> viewOrders(reportArea));
    }

    private void addProduct() {
        Product newProduct = AddProduct.addProduct(this);
        if (newProduct != null) {
            inventory.add(newProduct);
            updateReport("Product added: " + newProduct.getName() + " Quantity: " + newProduct.getQuantity());
        }
    }

    private void sellProduct() {
        List<OrderItem> orderItems = SellProduct.showDialog(this, inventory);

        if (orderItems != null && !orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                String clientName = orderItem.getClientName();
                ordersByClient.computeIfAbsent(clientName, k -> new ArrayList<>()).add(orderItem);

                updateReport("Product sold: " + orderItem.getProduct().getName() +
                        " Quantity: " + orderItem.getQuantity() +
                        " Net Weight: " + orderItem.getProduct().getWeight() +
                        " " + orderItem.getProduct().getWeightUnit() +
                        " Client: " + clientName);
            }
        }
    }

    private void updateProduct() {
        UpdateProduct.updateProduct(this, inventory);
    }

    private void generateReport(JTextArea reportArea) {
        StringBuilder report = new StringBuilder("Inventory Report:\n");

        for (Product product : inventory) {
            report.append(product.getName())
                    .append(": Quantity - ")
                    .append(product.getQuantity())
                    .append(", Price - cfa")
                    .append(product.getPrice())
                    .append(", Net Weight - ")
                    .append(product.getWeight())
                    .append(" ")
                    .append(product.getWeightUnit())
                    .append("\n");

            reportArea.setForeground(Color.BLACK);
            reportArea.append(report.toString() + "\n");
            checkAndNotifyExpiryAlert(reportArea, product);
        }

        report.append("\nSales Record:\n");

        for (Map.Entry<String, List<OrderItem>> entry : ordersByClient.entrySet()) {
            String clientName = entry.getKey();
            List<OrderItem> orders = entry.getValue();

            report.append("Client: ")
                    .append(clientName)
                    .append("\n");

            for (OrderItem orderItem : orders) {
                report.append("Product: ")
                        .append(orderItem.getProduct().getName())
                        .append(", Quantity Sold: ")
                        .append(orderItem.getQuantity())
                        .append("\n");
            }
        }

        reportArea.setText(report.toString());
    }

    private void checkAndNotifyExpiryAlert(JTextArea reportArea, Product product) {
        if (isExpired(product)) {
            reportArea.append(" (Expired)\n");
            reportArea.setForeground(Color.RED);
        } else if (willExpireSoon(product, 30)) {
            reportArea.append(" (Going to expire in 1 month)\n");
            reportArea.setForeground(Color.YELLOW);
        } else {
            reportArea.append("\n");
        }

        reportArea.setForeground(Color.BLACK);
    }

    private boolean isExpired(Product product) {
        Date currentDate = new Date();
        return product.getExpiryDate() != null && product.getExpiryDate().before(currentDate);
    }

    private boolean willExpireSoon(Product product, int days) {
        Date currentDate = new Date();
        long millisInDay = 24 * 60 * 60 * 1000;  // Number of milliseconds in a day
        long millisInFuture = currentDate.getTime() + days * millisInDay;

        return product.getExpiryDate() != null && product.getExpiryDate().getTime() < millisInFuture;
    }

    private void updateReport(String message) {
        System.out.println(message);
    }

    private void checkAndNotifyStockAndExpiryAlert(JTextArea reportArea) {
    StringBuilder stockAlert = new StringBuilder("Stock Alert:\n");
    StringBuilder expiryAlert = new StringBuilder("Expiry Alert:\n");

    for (Product product : inventory) {
        if (product.getQuantity() <= 25) {
            stockAlert.append(product.getName())
                    .append(" has low stock: ")
                    .append(product.getQuantity())
                    .append(" units remaining, Net Weight: ")
                    .append(product.getWeight())
                    .append(" ")
                    .append(product.getWeightUnit())
                    .append("\n");
        }

        if (isExpired(product)) {
            expiryAlert.append(product.getName())
                    .append(" has expired. Net Weight: ")
                    .append(product.getWeight())
                    .append(" ")
                    .append(product.getWeightUnit())
                    .append("\n");
        } else if (willExpireSoon(product, 30)) {
            expiryAlert.append(product.getName())
                    .append(" is going to expire in 1 month. Net Weight: ")
                    .append(product.getWeight())
                    .append(" ")
                    .append(product.getWeightUnit())
                    .append("\n");
        }
    }

    if (stockAlert.length() > 14) {
        reportArea.append(stockAlert.toString());
    } else {
        reportArea.append("No products with low stock.\n");
    }

    if (expiryAlert.length() > 16) {
        reportArea.append(expiryAlert.toString());
    } else {
        reportArea.append("No products with expiry alerts.\n");
    }
}


    private void viewOrders(JTextArea reportArea) {
        StringBuilder ordersReport = new StringBuilder("Orders by Client:\n");

        for (Map.Entry<String, List<OrderItem>> entry : ordersByClient.entrySet()) {
            String clientName = entry.getKey();
            List<OrderItem> orders = entry.getValue();

            ordersReport.append("Client: ")
                    .append(clientName)
                    .append("\n");

            for (OrderItem orderItem : orders) {
                ordersReport.append("Product: ")
                        .append(orderItem.getProduct().getName())
                        .append(", Quantity Sold: ")
                        .append(orderItem.getQuantity())
                        .append("\n");
            }
        }

        reportArea.setText(ordersReport.toString());
    }

    public static void main(String[] args) {
        new Main();
    }
}
