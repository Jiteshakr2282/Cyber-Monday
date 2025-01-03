package cybermonday;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserManagement extends JFrame implements ActionListener {
    
    private JTextField nameField, emailField, roleField;
    private JButton addUserButton, editUserButton, deleteUserButton;
    private DefaultListModel<String> userModel;
    private JList<String> userList;

    private ArrayList<User> users;  // Simulate a database

    public UserManagement() {
        setTitle("User Management");
        setLayout(null);
        setBounds(200, 100, 800, 600);

        users = new ArrayList<>();  // Initialize the user list

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 30);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 60, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(120, 60, 200, 30);
        add(emailField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(20, 100, 100, 30);
        add(roleLabel);

        roleField = new JTextField();
        roleField.setBounds(120, 100, 200, 30);
        add(roleField);

        addUserButton = new JButton("Add User");
        addUserButton.setBounds(350, 20, 120, 30);
        addUserButton.addActionListener(this);
        add(addUserButton);

        editUserButton = new JButton("Edit User");
        editUserButton.setBounds(350, 60, 120, 30);
        editUserButton.addActionListener(this);
        add(editUserButton);

        deleteUserButton = new JButton("Delete User");
        deleteUserButton.setBounds(350, 100, 120, 30);
        deleteUserButton.addActionListener(this);
        add(deleteUserButton);

        JLabel userListLabel = new JLabel("Users:");
        userListLabel.setBounds(20, 160, 100, 30);
        add(userListLabel);

        userModel = new DefaultListModel<>();
        userList = new JList<>(userModel);
        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setBounds(20, 200, 740, 300);
        add(scrollPane);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals("Add User")) {
            addUser();
        } else if (action.equals("Edit User")) {
            editUser();
        } else if (action.equals("Delete User")) {
            deleteUser();
        }
    }

    private void addUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String role = roleField.getText();

        if (name.isEmpty() || email.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User newUser = new User(name, email, role);
        users.add(newUser);
        userModel.addElement(newUser.toString());
        clearFields();
    }

    private void editUser() {
        int selectedIndex = userList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to edit!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = nameField.getText();
        String email = emailField.getText();
        String role = roleField.getText();

        if (name.isEmpty() || email.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User selectedUser = users.get(selectedIndex);
        selectedUser.setName(name);
        selectedUser.setEmail(email);
        selectedUser.setRole(role);

        userModel.set(selectedIndex, selectedUser.toString());
        clearFields();
    }

    private void deleteUser() {
        int selectedIndex = userList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        users.remove(selectedIndex);
        userModel.remove(selectedIndex);
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        roleField.setText("");
    }

    public static void main(String[] args) {
        new UserManagement();
    }

    // Inner class for User object
    class User {
        private String name, email, role;

        public User(String name, String email, String role) {
            this.name = name;
            this.email = email;
            this.role = role;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return name + " - " + email + " (" + role + ")";
        }
    }
}
