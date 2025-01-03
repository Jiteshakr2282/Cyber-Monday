package cybermonday;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ProductManagement extends JFrame implements ActionListener {

    private JTextField nameField, descriptionField, priceField, stockField;
    private JButton addProductButton, editProductButton, deleteProductButton;
    private DefaultListModel<String> productModel;
    private JList<String> productList;

    private ArrayList<Product> products;  // Simulate a database

    public ProductManagement() {
        setTitle("Product Management");
        setLayout(null);
        setBounds(200, 100, 800, 600);

        products = new ArrayList<>();  // Initialize the product list

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 30);
        add(nameField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20, 60, 100, 30);
        add(descriptionLabel);

        descriptionField = new JTextField();
        descriptionField.setBounds(120, 60, 200, 30);
        add(descriptionField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 100, 100, 30);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(120, 100, 200, 30);
        add(priceField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(20, 140, 100, 30);
        add(stockLabel);

        stockField = new JTextField();
        stockField.setBounds(120, 140, 200, 30);
        add(stockField);

        addProductButton = new JButton("Add Product");
        addProductButton.setBounds(350, 20, 150, 30);
        addProductButton.addActionListener(this);
        add(addProductButton);

        editProductButton = new JButton("Edit Product");
        editProductButton.setBounds(350, 60, 150, 30);
        editProductButton.addActionListener(this);
        add(editProductButton);

        deleteProductButton = new JButton("Delete Product");
        deleteProductButton.setBounds(350, 100, 150, 30);
        deleteProductButton.addActionListener(this);
        add(deleteProductButton);

        JLabel productListLabel = new JLabel("Products:");
        productListLabel.setBounds(20, 200, 100, 30);
        add(productListLabel);

        productModel = new DefaultListModel<>();
        productList = new JList<>(productModel);
        JScrollPane scrollPane = new JScrollPane(productList);
        scrollPane.setBounds(20, 240, 740, 300);
        add(scrollPane);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals("Add Product")) {
            addProduct();
        } else if (action.equals("Edit Product")) {
            editProduct();
        } else if (action.equals("Delete Product")) {
            deleteProduct();
        }
    }

    private void addProduct() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String priceText = priceField.getText();
        String stockText = stockField.getText();

        if (name.isEmpty() || description.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            int stock = Integer.parseInt(stockText);

            Product newProduct = new Product(name, description, price, stock);
            products.add(newProduct);
            productModel.addElement(newProduct.toString());
            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price or stock value!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editProduct() {
        int selectedIndex = productList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to edit!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = nameField.getText();
        String description = descriptionField.getText();
        String priceText = priceField.getText();
        String stockText = stockField.getText();

        if (name.isEmpty() || description.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            int stock = Integer.parseInt(stockText);

            Product selectedProduct = products.get(selectedIndex);
            selectedProduct.setName(name);
            selectedProduct.setDescription(description);
            selectedProduct.setPrice(price);
            selectedProduct.setStock(stock);

            productModel.set(selectedIndex, selectedProduct.toString());
            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price or stock value!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProduct() {
        int selectedIndex = productList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        products.remove(selectedIndex);
        productModel.remove(selectedIndex);
    }

    private void clearFields() {
        nameField.setText("");
        descriptionField.setText("");
        priceField.setText("");
        stockField.setText("");
    }

    public static void main(String[] args) {
        new ProductManagement();
    }

    // Inner class for Product object
    class Product {
        private String name, description;
        private double price;
        private int stock;

        public Product(String name, String description, double price, int stock) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.stock = stock;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        @Override
        public String toString() {
            return name + " - $" + price + " (" + stock + " in stock)";
        }
    }
}
