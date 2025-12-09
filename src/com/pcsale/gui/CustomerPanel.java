package com.pcsale.gui;

import com.pcsale.dao.CustomerDAO;
import com.pcsale.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * CustomerPanel - Customer management interface
 */
public class CustomerPanel extends JPanel {
    
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private CustomerDAO customerDAO;
    
    public CustomerPanel() {
        customerDAO = new CustomerDAO();
        initComponents();
        loadCustomers();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel("Customer Management");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(lblTitle, BorderLayout.WEST);
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(Color.WHITE);
        
        JLabel lblSearch = new JLabel("Search:");
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchCustomers();
            }
        });
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(new Color(149, 165, 166));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFocusPainted(false);
        btnRefresh.addActionListener(e -> loadCustomers());
        
        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnRefresh);
        
        topPanel.add(searchPanel, BorderLayout.EAST);
        
        // Table
        String[] columns = {"ID", "Code", "Name", "Phone", "Email", "Address", "Points"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnAdd = new JButton("Add Customer");
        btnAdd.setBackground(new Color(46, 213, 115));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        btnAdd.setPreferredSize(new Dimension(150, 40));
        btnAdd.addActionListener(e -> showAddDialog());
        
        JButton btnEdit = new JButton("Edit Customer");
        btnEdit.setBackground(new Color(52, 152, 219));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Arial", Font.BOLD, 14));
        btnEdit.setFocusPainted(false);
        btnEdit.setPreferredSize(new Dimension(150, 40));
        btnEdit.addActionListener(e -> showEditDialog());
        
        JButton btnDelete = new JButton("Delete Customer");
        btnDelete.setBackground(new Color(231, 76, 60));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        btnDelete.setPreferredSize(new Dimension(150, 40));
        btnDelete.addActionListener(e -> deleteCustomer());
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        
        // Add to main panel
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadCustomers() {
        tableModel.setRowCount(0);
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer customer : customers) {
            tableModel.addRow(new Object[]{
                customer.getId(),
                customer.getCustomerCode(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPoints()
            });
        }
    }
    
    private void searchCustomers() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            loadCustomers();
            return;
        }
        
        tableModel.setRowCount(0);
        List<Customer> customers = customerDAO.searchCustomers(keyword);
        for (Customer customer : customers) {
            tableModel.addRow(new Object[]{
                customer.getId(),
                customer.getCustomerCode(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPoints()
            });
        }
    }
    
    private void showAddDialog() {
        CustomerDialog dialog = new CustomerDialog((Frame) SwingUtilities.getWindowAncestor(this), null);
        dialog.setVisible(true);
        if (dialog.isSuccess()) {
            loadCustomers();
        }
    }
    
    private void showEditDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to edit!");
            return;
        }
        
        int customerId = (int) tableModel.getValueAt(selectedRow, 0);
        Customer customer = customerDAO.getCustomerById(customerId);
        
        CustomerDialog dialog = new CustomerDialog((Frame) SwingUtilities.getWindowAncestor(this), customer);
        dialog.setVisible(true);
        if (dialog.isSuccess()) {
            loadCustomers();
        }
    }
    
    private void deleteCustomer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to delete!");
            return;
        }
        
        int option = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this customer?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            int customerId = (int) tableModel.getValueAt(selectedRow, 0);
            boolean success = customerDAO.deleteCustomer(customerId);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
                loadCustomers();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete customer!");
            }
        }
    }
    
    /**
     * Inner class - Customer Add/Edit Dialog
     */
    class CustomerDialog extends JDialog {
        private JTextField txtCode, txtName, txtPhone, txtEmail, txtAddress, txtPoints;
        private Customer customer;
        private boolean success = false;
        
        public CustomerDialog(Frame parent, Customer customer) {
            super(parent, customer == null ? "Add Customer" : "Edit Customer", true);
            this.customer = customer;
            initDialog();
            if (customer != null) {
                fillData();
            } else {
                txtCode.setText(customerDAO.generateCustomerCode());
            }
            setLocationRelativeTo(parent);
        }
        
        private void initDialog() {
            setSize(500, 400);
            setLayout(null);
            
            int y = 20;
            
            // Customer Code
            JLabel lblCode = new JLabel("Customer Code:");
            lblCode.setBounds(30, y, 120, 25);
            add(lblCode);
            
            txtCode = new JTextField();
            txtCode.setBounds(160, y, 300, 30);
            txtCode.setEditable(false);
            txtCode.setBackground(Color.LIGHT_GRAY);
            add(txtCode);
            y += 40;
            
            // Name
            JLabel lblName = new JLabel("Name:");
            lblName.setBounds(30, y, 120, 25);
            add(lblName);
            
            txtName = new JTextField();
            txtName.setBounds(160, y, 300, 30);
            add(txtName);
            y += 40;
            
            // Phone
            JLabel lblPhone = new JLabel("Phone:");
            lblPhone.setBounds(30, y, 120, 25);
            add(lblPhone);
            
            txtPhone = new JTextField();
            txtPhone.setBounds(160, y, 300, 30);
            add(txtPhone);
            y += 40;
            
            // Email
            JLabel lblEmail = new JLabel("Email:");
            lblEmail.setBounds(30, y, 120, 25);
            add(lblEmail);
            
            txtEmail = new JTextField();
            txtEmail.setBounds(160, y, 300, 30);
            add(txtEmail);
            y += 40;
            
            // Address
            JLabel lblAddress = new JLabel("Address:");
            lblAddress.setBounds(30, y, 120, 25);
            add(lblAddress);
            
            txtAddress = new JTextField();
            txtAddress.setBounds(160, y, 300, 30);
            add(txtAddress);
            y += 40;
            
            // Points
            JLabel lblPoints = new JLabel("Points:");
            lblPoints.setBounds(30, y, 120, 25);
            add(lblPoints);
            
            txtPoints = new JTextField("0");
            txtPoints.setBounds(160, y, 140, 30);
            add(txtPoints);
            y += 50;
            
            // Buttons
            JButton btnSave = new JButton("Save");
            btnSave.setBounds(160, y, 120, 35);
            btnSave.setBackground(new Color(46, 213, 115));
            btnSave.setForeground(Color.WHITE);
            btnSave.setFocusPainted(false);
            btnSave.addActionListener(e -> saveCustomer());
            add(btnSave);
            
            JButton btnCancel = new JButton("Cancel");
            btnCancel.setBounds(300, y, 120, 35);
            btnCancel.setBackground(new Color(149, 165, 166));
            btnCancel.setForeground(Color.WHITE);
            btnCancel.setFocusPainted(false);
            btnCancel.addActionListener(e -> dispose());
            add(btnCancel);
        }
        
        private void fillData() {
            txtCode.setText(customer.getCustomerCode());
            txtName.setText(customer.getName());
            txtPhone.setText(customer.getPhone());
            txtEmail.setText(customer.getEmail());
            txtAddress.setText(customer.getAddress());
            txtPoints.setText(String.valueOf(customer.getPoints()));
        }
        
        private void saveCustomer() {
            // Validate
            if (txtName.getText().trim().isEmpty() || txtPhone.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in Name and Phone!");
                return;
            }
            
            try {
                Customer c = customer != null ? customer : new Customer();
                c.setCustomerCode(txtCode.getText().trim());
                c.setName(txtName.getText().trim());
                c.setPhone(txtPhone.getText().trim());
                c.setEmail(txtEmail.getText().trim());
                c.setAddress(txtAddress.getText().trim());
                c.setPoints(Integer.parseInt(txtPoints.getText().trim()));
                
                boolean result;
                if (customer == null) {
                    result = customerDAO.addCustomer(c);
                } else {
                    result = customerDAO.updateCustomer(c);
                }
                
                if (result) {
                    success = true;
                    JOptionPane.showMessageDialog(this, "Customer saved successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to save customer!");
                }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid points value!");
            }
        }
        
        public boolean isSuccess() {
            return success;
        }
    }
}
