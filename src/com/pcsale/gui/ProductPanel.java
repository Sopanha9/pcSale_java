package com.pcsale.gui;

import com.pcsale.dao.ProductDAO;
import com.pcsale.dao.CategoryDAO;
import com.pcsale.model.Product;
import com.pcsale.model.Category;
import com.pcsale.util.Formatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * ProductPanel - Product management interface
 */
public class ProductPanel extends JPanel {
    
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    
    public ProductPanel() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
        initComponents();
        loadProducts();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel - Title and search
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel("Product Management");
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
                searchProducts();
            }
        });
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(new Color(149, 165, 166));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFocusPainted(false);
        btnRefresh.addActionListener(e -> loadProducts());
        
        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnRefresh);
        
        topPanel.add(searchPanel, BorderLayout.EAST);
        
        // Table
        String[] columns = {"ID", "Barcode", "Name", "Category", "Cost", "Selling Price", "Stock", "Status"};
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
        
        JButton btnAdd = new JButton("Add Product");
        btnAdd.setBackground(new Color(46, 213, 115));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        btnAdd.setPreferredSize(new Dimension(150, 40));
        btnAdd.addActionListener(e -> showAddDialog());
        
        JButton btnEdit = new JButton("Edit Product");
        btnEdit.setBackground(new Color(52, 152, 219));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Arial", Font.BOLD, 14));
        btnEdit.setFocusPainted(false);
        btnEdit.setPreferredSize(new Dimension(150, 40));
        btnEdit.addActionListener(e -> showEditDialog());
        
        JButton btnDelete = new JButton("Delete Product");
        btnDelete.setBackground(new Color(231, 76, 60));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        btnDelete.setPreferredSize(new Dimension(150, 40));
        btnDelete.addActionListener(e -> deleteProduct());
        
        JButton btnLowStock = new JButton("Low Stock");
        btnLowStock.setBackground(new Color(243, 156, 18));
        btnLowStock.setForeground(Color.WHITE);
        btnLowStock.setFont(new Font("Arial", Font.BOLD, 14));
        btnLowStock.setFocusPainted(false);
        btnLowStock.setPreferredSize(new Dimension(150, 40));
        btnLowStock.addActionListener(e -> showLowStock());
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnLowStock);
        
        // Add to main panel
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadProducts() {
        tableModel.setRowCount(0);
        List<Product> products = productDAO.getAllProducts();
        for (Product product : products) {
            tableModel.addRow(new Object[]{
                product.getId(),
                product.getBarcode(),
                product.getName(),
                product.getCategoryName() != null ? product.getCategoryName() : "-",
                Formatter.formatCurrency(product.getCostPrice().doubleValue()),
                Formatter.formatCurrency(product.getSellingPrice().doubleValue()),
                product.getStockQuantity(),
                product.getStatus()
            });
        }
    }
    
    private void searchProducts() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            loadProducts();
            return;
        }
        
        tableModel.setRowCount(0);
        List<Product> products = productDAO.searchProducts(keyword);
        for (Product product : products) {
            tableModel.addRow(new Object[]{
                product.getId(),
                product.getBarcode(),
                product.getName(),
                product.getCategoryName() != null ? product.getCategoryName() : "-",
                Formatter.formatCurrency(product.getCostPrice().doubleValue()),
                Formatter.formatCurrency(product.getSellingPrice().doubleValue()),
                product.getStockQuantity(),
                product.getStatus()
            });
        }
    }
    
    private void showLowStock() {
        tableModel.setRowCount(0);
        List<Product> products = productDAO.getLowStockProducts();
        for (Product product : products) {
            tableModel.addRow(new Object[]{
                product.getId(),
                product.getBarcode(),
                product.getName(),
                product.getCategoryName() != null ? product.getCategoryName() : "-",
                Formatter.formatCurrency(product.getCostPrice().doubleValue()),
                Formatter.formatCurrency(product.getSellingPrice().doubleValue()),
                product.getStockQuantity(),
                product.getStatus()
            });
        }
    }
    
    private void showAddDialog() {
        ProductDialog dialog = new ProductDialog((Frame) SwingUtilities.getWindowAncestor(this), null);
        dialog.setVisible(true);
        if (dialog.isSuccess()) {
            loadProducts();
        }
    }
    
    private void showEditDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to edit!");
            return;
        }
        
        int productId = (int) tableModel.getValueAt(selectedRow, 0);
        Product product = productDAO.getProductById(productId);
        
        ProductDialog dialog = new ProductDialog((Frame) SwingUtilities.getWindowAncestor(this), product);
        dialog.setVisible(true);
        if (dialog.isSuccess()) {
            loadProducts();
        }
    }
    
    private void deleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to delete!");
            return;
        }
        
        int option = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this product?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            int productId = (int) tableModel.getValueAt(selectedRow, 0);
            boolean success = productDAO.deleteProduct(productId);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                loadProducts();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete product!");
            }
        }
    }
    
    /**
     * Inner class - Product Add/Edit Dialog
     */
    class ProductDialog extends JDialog {
        private JTextField txtBarcode, txtName, txtCostPrice, txtSellingPrice, txtStock, txtReorderLevel;
        private JComboBox<Category> cboCategory;
        private JComboBox<String> cboStatus;
        private Product product;
        private boolean success = false;
        
        public ProductDialog(Frame parent, Product product) {
            super(parent, product == null ? "Add Product" : "Edit Product", true);
            this.product = product;
            initDialog();
            if (product != null) {
                fillData();
            }
            setLocationRelativeTo(parent);
        }
        
        private void initDialog() {
            setSize(500, 450);
            setLayout(null);
            
            int y = 20;
            
            // Barcode
            JLabel lblBarcode = new JLabel("Barcode:");
            lblBarcode.setBounds(30, y, 120, 25);
            add(lblBarcode);
            
            txtBarcode = new JTextField();
            txtBarcode.setBounds(160, y, 300, 30);
            add(txtBarcode);
            y += 40;
            
            // Name
            JLabel lblName = new JLabel("Product Name:");
            lblName.setBounds(30, y, 120, 25);
            add(lblName);
            
            txtName = new JTextField();
            txtName.setBounds(160, y, 300, 30);
            add(txtName);
            y += 40;
            
            // Category
            JLabel lblCategory = new JLabel("Category:");
            lblCategory.setBounds(30, y, 120, 25);
            add(lblCategory);
            
            cboCategory = new JComboBox<>();
            cboCategory.addItem(null); // No category option
            List<Category> categories = categoryDAO.getAllCategories();
            for (Category cat : categories) {
                cboCategory.addItem(cat);
            }
            cboCategory.setBounds(160, y, 300, 30);
            add(cboCategory);
            y += 40;
            
            // Cost Price
            JLabel lblCost = new JLabel("Cost Price:");
            lblCost.setBounds(30, y, 120, 25);
            add(lblCost);
            
            txtCostPrice = new JTextField();
            txtCostPrice.setBounds(160, y, 140, 30);
            add(txtCostPrice);
            y += 40;
            
            // Selling Price
            JLabel lblSelling = new JLabel("Selling Price:");
            lblSelling.setBounds(30, y, 120, 25);
            add(lblSelling);
            
            txtSellingPrice = new JTextField();
            txtSellingPrice.setBounds(160, y, 140, 30);
            add(txtSellingPrice);
            y += 40;
            
            // Stock
            JLabel lblStock = new JLabel("Stock Quantity:");
            lblStock.setBounds(30, y, 120, 25);
            add(lblStock);
            
            txtStock = new JTextField();
            txtStock.setBounds(160, y, 140, 30);
            add(txtStock);
            y += 40;
            
            // Reorder Level
            JLabel lblReorder = new JLabel("Reorder Level:");
            lblReorder.setBounds(30, y, 120, 25);
            add(lblReorder);
            
            txtReorderLevel = new JTextField("10");
            txtReorderLevel.setBounds(160, y, 140, 30);
            add(txtReorderLevel);
            y += 40;
            
            // Status
            JLabel lblStatus = new JLabel("Status:");
            lblStatus.setBounds(30, y, 120, 25);
            add(lblStatus);
            
            cboStatus = new JComboBox<>(new String[]{"active", "inactive"});
            cboStatus.setBounds(160, y, 140, 30);
            add(cboStatus);
            y += 50;
            
            // Buttons
            JButton btnSave = new JButton("Save");
            btnSave.setBounds(160, y, 120, 35);
            btnSave.setBackground(new Color(46, 213, 115));
            btnSave.setForeground(Color.WHITE);
            btnSave.setFocusPainted(false);
            btnSave.addActionListener(e -> saveProduct());
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
            txtBarcode.setText(product.getBarcode());
            txtName.setText(product.getName());
            txtCostPrice.setText(product.getCostPrice().toString());
            txtSellingPrice.setText(product.getSellingPrice().toString());
            txtStock.setText(String.valueOf(product.getStockQuantity()));
            txtReorderLevel.setText(String.valueOf(product.getReorderLevel()));
            cboStatus.setSelectedItem(product.getStatus());
            
            // Set category
            if (product.getCategoryId() != null) {
                for (int i = 0; i < cboCategory.getItemCount(); i++) {
                    Category cat = cboCategory.getItemAt(i);
                    if (cat != null && cat.getId() == product.getCategoryId()) {
                        cboCategory.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
        
        private void saveProduct() {
            // Validate
            if (txtBarcode.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() ||
                txtCostPrice.getText().trim().isEmpty() || txtSellingPrice.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields!");
                return;
            }
            
            try {
                Product p = product != null ? product : new Product();
                p.setBarcode(txtBarcode.getText().trim());
                p.setName(txtName.getText().trim());
                
                Category selectedCat = (Category) cboCategory.getSelectedItem();
                p.setCategoryId(selectedCat != null ? selectedCat.getId() : null);
                
                p.setCostPrice(new BigDecimal(txtCostPrice.getText().trim()));
                p.setSellingPrice(new BigDecimal(txtSellingPrice.getText().trim()));
                p.setStockQuantity(Integer.parseInt(txtStock.getText().trim()));
                p.setReorderLevel(Integer.parseInt(txtReorderLevel.getText().trim()));
                p.setStatus(cboStatus.getSelectedItem().toString());
                
                boolean result;
                if (product == null) {
                    result = productDAO.addProduct(p);
                } else {
                    result = productDAO.updateProduct(p);
                }
                
                if (result) {
                    success = true;
                    JOptionPane.showMessageDialog(this, "Product saved successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to save product!");
                }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid number format!");
            }
        }
        
        public boolean isSuccess() {
            return success;
        }
    }
}
