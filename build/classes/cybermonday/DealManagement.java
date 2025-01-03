package cybermonday;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DealManagement extends JFrame implements ActionListener {

    private JTextField productField, discountField, durationField;
    private JButton addDealButton, editDealButton, deleteDealButton;
    private DefaultListModel<String> dealModel;
    private JList<String> dealList;

    private ArrayList<Deal> deals; // Simulate a database for deals

    public DealManagement() {
        setTitle("Deal Management");
        setLayout(null);
        setBounds(200, 100, 800, 600);

        deals = new ArrayList<>(); // Initialize deal list

        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(20, 20, 100, 30);
        add(productLabel);

        productField = new JTextField();
        productField.setBounds(120, 20, 200, 30);
        add(productField);

        JLabel discountLabel = new JLabel("Discount (%):");
        discountLabel.setBounds(20, 60, 100, 30);
        add(discountLabel);

        discountField = new JTextField();
        discountField.setBounds(120, 60, 200, 30);
        add(discountField);

        JLabel durationLabel = new JLabel("Duration (days):");
        durationLabel.setBounds(20, 100, 120, 30);
        add(durationLabel);

        durationField = new JTextField();
        durationField.setBounds(150, 100, 170, 30);
        add(durationField);

        addDealButton = new JButton("Add Deal");
        addDealButton.setBounds(350, 20, 150, 30);
        addDealButton.addActionListener(this);
        add(addDealButton);

        editDealButton = new JButton("Edit Deal");
        editDealButton.setBounds(350, 60, 150, 30);
        editDealButton.addActionListener(this);
        add(editDealButton);

        deleteDealButton = new JButton("Delete Deal");
        deleteDealButton.setBounds(350, 100, 150, 30);
        deleteDealButton.addActionListener(this);
        add(deleteDealButton);

        JLabel dealListLabel = new JLabel("Deals:");
        dealListLabel.setBounds(20, 160, 100, 30);
        add(dealListLabel);

        dealModel = new DefaultListModel<>();
        dealList = new JList<>(dealModel);
        JScrollPane scrollPane = new JScrollPane(dealList);
        scrollPane.setBounds(20, 200, 740, 300);
        add(scrollPane);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals("Add Deal")) {
            addDeal();
        } else if (action.equals("Edit Deal")) {
            editDeal();
        } else if (action.equals("Delete Deal")) {
            deleteDeal();
        }
    }

    private void addDeal() {
        String product = productField.getText();
        String discountText = discountField.getText();
        String durationText = durationField.getText();

        if (product.isEmpty() || discountText.isEmpty() || durationText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int discount = Integer.parseInt(discountText);
            int duration = Integer.parseInt(durationText);

            Deal newDeal = new Deal(product, discount, duration);
            deals.add(newDeal);
            dealModel.addElement(newDeal.toString());
            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid discount or duration value!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editDeal() {
        int selectedIndex = dealList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a deal to edit!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String product = productField.getText();
        String discountText = discountField.getText();
        String durationText = durationField.getText();

        if (product.isEmpty() || discountText.isEmpty() || durationText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int discount = Integer.parseInt(discountText);
            int duration = Integer.parseInt(durationText);

            Deal selectedDeal = deals.get(selectedIndex);
            selectedDeal.setProduct(product);
            selectedDeal.setDiscount(discount);
            selectedDeal.setDuration(duration);

            dealModel.set(selectedIndex, selectedDeal.toString());
            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid discount or duration value!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteDeal() {
        int selectedIndex = dealList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a deal to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        deals.remove(selectedIndex);
        dealModel.remove(selectedIndex);
    }

    private void clearFields() {
        productField.setText("");
        discountField.setText("");
        durationField.setText("");
    }

    public static void main(String[] args) {
        new DealManagement();
    }

    // Inner class for Deal object
    class Deal {
        private String product;
        private int discount, duration;

        public Deal(String product, int discount, int duration) {
            this.product = product;
            this.discount = discount;
            this.duration = duration;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        @Override
        public String toString() {
            return product + " - " + discount + "% for " + duration + " days";
        }
    }
}
