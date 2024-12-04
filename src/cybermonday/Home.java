
package cybermonday;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener{
    
    public Home() {
        setLayout(null);
        
        // Background image
        /*ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinesreservationsystem/icons/front.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1350, 650);
        add(image);*/
        
        // Heading text
        JLabel heading = new JLabel(" WELCOMES YOU");
        heading.setBounds(500, 40, 1000, 40);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 36));
        add(heading);
        
        // Menu bar and menu items
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        // Details menu
        JMenu details = new JMenu("Details");
        menubar.add(details);
        
        // Menu items under "Details"
        JMenuItem UserManagement = new JMenuItem("User Management");
        UserManagement.addActionListener(this);
        details.add(UserManagement);
        
        JMenuItem ProductManagement = new JMenuItem("Product Management");
        ProductManagement.addActionListener(this);
        details.add(ProductManagement);
        
        JMenuItem DealManagement = new JMenuItem("Deal Management");
        DealManagement.addActionListener(this);
        details.add(DealManagement);
        
        JMenuItem SalesAnalysis = new JMenuItem("Sales Analysis");
        SalesAnalysis.addActionListener(this);
        details.add(SalesAnalysis);
        
         JMenuItem OrderManagement = new JMenuItem("Order Management");
        OrderManagement.addActionListener(this);
        details.add(OrderManagement);
        
        /* Ticket menu
       JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);
        
        // Boarding Pass menu item inside the "Ticket" menu
        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);  // Add ActionListener
        ticket.add(boardingPass);*/
        
        // Maximizing the window
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();
        
        if (text.equals("ProductManagement")) {
            new ProductManagement();  // Open AddCustomer window
        } else if (text.equals("User Management")) {
            new UserManagement();  // Open FlightInfo window
        } else if (text.equals("Deal Management")) {
            new DealManagement();  // Open BookFlight window
        } else if (text.equals("Sales Analysis")) {
            new SalesAnalysis();  // Open JourneyDetails window
        } else if (text.equals("Order Management")) {
            new OrderManagement();  // Open Cancel window
       }
    }
    
    public static void main(String[] args) {
        new Home();  // Create Home object and display the window
    }
}