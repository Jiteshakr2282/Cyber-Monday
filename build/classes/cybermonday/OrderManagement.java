package cybermonday;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OrderManagement extends JFrame implements ActionListener {

    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JButton updateStatusButton, deleteOrderButton;
    private ArrayList<Order> orders; // Simulate a database for orders

    public OrderManagement() {
        setTitle("Order Management");
        setBounds(200, 100, 800, 600);
        setLayout(new BorderLayout());

        // Sample orders
        orders = generateSampleOrders();

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"Order ID", "Customer", "Product", "Status"}, 0);
        orderTable = new JTable(tableModel);
        populateTable();
        add(new JScrollPane(orderTable), BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        updateStatusButton = new JButton("Update Status");
        updateStatusButton.addActionListener(this);
        buttonPanel.add(updateStatusButton);

        deleteOrderButton = new JButton("Delete Order");
        deleteOrderButton.addActionListener(this);
        buttonPanel.add(deleteOrderButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private ArrayList<Order> generateSampleOrders() {
        ArrayList<Order> sampleOrders = new ArrayList<>();
        sampleOrders.add(new Order(1, "Alice", "Laptop", "Pending"));
        sampleOrders.add(new Order(2, "Bob", "Headphones", "Shipped"));
        sampleOrders.add(new Order(3, "Charlie", "Smartphone", "Delivered"));
        return sampleOrders;
    }

    private void populateTable() {
        tableModel.setRowCount(0); // Clear existing rows
        for (Order order : orders) {
            tableModel.addRow(new Object[]{order.getOrderId(), order.getCustomerName(), order.getProductName(), order.getStatus()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateStatusButton) {
            updateOrderStatus();
        } else if (e.getSource() == deleteOrderButton) {
            deleteOrder();
        }
    }

    private void updateOrderStatus() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newStatus = JOptionPane.showInputDialog(this, "Enter new status (e.g., Shipped, Delivered):");
        if (newStatus == null || newStatus.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Status cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        orders.get(selectedRow).setStatus(newStatus);
        populateTable(); // Refresh table
    }

    private void deleteOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        orders.remove(selectedRow);
        populateTable(); // Refresh table
    }

    public static void main(String[] args) {
        new OrderManagement();
    }

    // Inner class for Order object
    class Order {
        private int orderId;
        private String customerName, productName, status;

        public Order(int orderId, String customerName, String productName, String status) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.productName = productName;
            this.status = status;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getProductName() {
            return productName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
