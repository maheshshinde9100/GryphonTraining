/*
 * Library Management System - Window Functions Implementation
 * 
 * SQL Window Functions Used:
 * 1. RANK() - Ranks books by price in descending order
 * 2. ROW_NUMBER() - Assigns sequential numbers to books ordered by publication date
 * 3. LAG() - Compares current book price with previous book price
 * 4. LEAD() - Compares current book price with next book price
 * 5. AVG() with PARTITION BY - Calculates average price by category and shows difference
 * 
 * Additional Concepts:
 * - PARTITION BY - Used with AVG() to calculate category-wise averages
 * - ORDER BY - Used within window functions to define ordering
 * - Dynamic CRUD Operations with JDBC
 * - Java Swing GUI with Event Handling
 */

package day9;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class LibraryManagementSystem extends JFrame {
    
    static final String dburl = "jdbc:mysql://localhost:3306/crud_db";
    static final String username = "root";
    static final String password = "mahesh";
    
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel buttonPanel;
    private JPanel inputPanel;
    
    private JTextField txtBookName, txtAuthor, txtCategory, txtPrice, txtDate;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
    
    public LibraryManagementSystem() {
        setTitle("Library Management System - Window Functions Demo");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        createInputPanel();
        
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                loadSelectedRowData();
            }
        });
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Window Function Operations"));
        
        JButton btnRankByPrice = new JButton("Rank Books by Price");
        JButton btnRowNumber = new JButton("Row Number by Date");
        JButton btnPreviousPrice = new JButton("Compare Previous (LAG)");
        JButton btnNextPrice = new JButton("Compare Next (LEAD)");
        JButton btnAvgByCategory = new JButton("Avg Price by Category");
        JButton btnInitData = new JButton("Initialize Sample Data");
        
        btnRankByPrice.addActionListener(e -> rankBooksByPrice());
        btnRowNumber.addActionListener(e -> rowNumberByDate());
        btnPreviousPrice.addActionListener(e -> compareWithPreviousPrice());
        btnNextPrice.addActionListener(e -> compareWithNextPrice());
        btnAvgByCategory.addActionListener(e -> avgPriceByCategory());
        btnInitData.addActionListener(e -> initializeSampleData());
        
        buttonPanel.add(btnInitData);
        buttonPanel.add(btnRankByPrice);
        buttonPanel.add(btnRowNumber);
        buttonPanel.add(btnPreviousPrice);
        buttonPanel.add(btnNextPrice);
        buttonPanel.add(btnAvgByCategory);
        
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        initializeDatabase();
    }
    
    private void createInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Book Management (Dynamic Operations)"));
        
        JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        txtBookName = new JTextField(20);
        txtAuthor = new JTextField(20);
        txtCategory = new JTextField(20);
        txtPrice = new JTextField(20);
        txtDate = new JTextField(20);
        
        fieldsPanel.add(new JLabel("Book Name:"));
        fieldsPanel.add(txtBookName);
        fieldsPanel.add(new JLabel("Author:"));
        fieldsPanel.add(txtAuthor);
        fieldsPanel.add(new JLabel("Category:"));
        fieldsPanel.add(txtCategory);
        fieldsPanel.add(new JLabel("Price:"));
        fieldsPanel.add(txtPrice);
        fieldsPanel.add(new JLabel("Publication Date (YYYY-MM-DD):"));
        fieldsPanel.add(txtDate);
        
        JPanel crudPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnAdd = new JButton("Add Book");
        btnUpdate = new JButton("Update Book");
        btnDelete = new JButton("Delete Book");
        btnSearch = new JButton("Search by Category");
        JButton btnClear = new JButton("Clear Fields");
        JButton btnViewAll = new JButton("View All Books");
        
        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
        btnSearch.addActionListener(e -> searchByCategory());
        btnClear.addActionListener(e -> clearFields());
        btnViewAll.addActionListener(e -> viewAllBooks());
        
        crudPanel.add(btnAdd);
        crudPanel.add(btnUpdate);
        crudPanel.add(btnDelete);
        crudPanel.add(btnSearch);
        crudPanel.add(btnClear);
        crudPanel.add(btnViewAll);
        
        inputPanel.add(fieldsPanel, BorderLayout.CENTER);
        inputPanel.add(crudPanel, BorderLayout.SOUTH);
    }
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }
    
    private void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            String createTable = "CREATE TABLE IF NOT EXISTS library (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "book_name VARCHAR(100), " +
                    "author VARCHAR(100), " +
                    "category VARCHAR(50), " +
                    "price DECIMAL(10,2), " +
                    "publication_date DATE)";
            stmt.executeUpdate(createTable);
            
            JOptionPane.showMessageDialog(this, "Database initialized successfully!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearFields() {
        txtBookName.setText("");
        txtAuthor.setText("");
        txtCategory.setText("");
        txtPrice.setText("");
        txtDate.setText("");
        table.clearSelection();
    }
    
    private void loadSelectedRowData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String bookName = table.getValueAt(selectedRow, 0).toString();
            
            String query = "SELECT * FROM library WHERE book_name = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setString(1, bookName);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    txtBookName.setText(rs.getString("book_name"));
                    txtAuthor.setText(rs.getString("author"));
                    txtCategory.setText(rs.getString("category"));
                    txtPrice.setText(rs.getString("price"));
                    txtDate.setText(rs.getString("publication_date"));
                }
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
            }
        }
    }
    
    private void addBook() {
        if (validateInputs()) {
            String query = "INSERT INTO library (book_name, author, category, price, publication_date) VALUES (?, ?, ?, ?, ?)";
            
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setString(1, txtBookName.getText().trim());
                pstmt.setString(2, txtAuthor.getText().trim());
                pstmt.setString(3, txtCategory.getText().trim());
                pstmt.setDouble(4, Double.parseDouble(txtPrice.getText().trim()));
                pstmt.setDate(5, Date.valueOf(txtDate.getText().trim()));
                
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Book added successfully!");
                    clearFields();
                    viewAllBooks();
                }
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error adding book: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void updateBook() {
        if (validateInputs()) {
            String query = "UPDATE library SET author = ?, category = ?, price = ?, publication_date = ? WHERE book_name = ?";
            
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setString(1, txtAuthor.getText().trim());
                pstmt.setString(2, txtCategory.getText().trim());
                pstmt.setDouble(3, Double.parseDouble(txtPrice.getText().trim()));
                pstmt.setDate(4, Date.valueOf(txtDate.getText().trim()));
                pstmt.setString(5, txtBookName.getText().trim());
                
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Book updated successfully!");
                    clearFields();
                    viewAllBooks();
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found!");
                }
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error updating book: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteBook() {
        String bookName = txtBookName.getText().trim();
        
        if (bookName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter book name or select a row!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this book?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String query = "DELETE FROM library WHERE book_name = ?";
            
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setString(1, bookName);
                
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Book deleted successfully!");
                    clearFields();
                    viewAllBooks();
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found!");
                }
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting book: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void searchByCategory() {
        String category = txtCategory.getText().trim();
        
        if (category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a category to search!");
            return;
        }
        
        String query = "SELECT book_name, author, category, price, publication_date, " +
                "RANK() OVER (PARTITION BY category ORDER BY price DESC) AS category_rank " +
                "FROM library WHERE category = ? ORDER BY price DESC";
        
                    try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();
            
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            
            String[] columnNames = {"Book Name", "Author", "Category", "Price", "Publication Date", "Rank in Category"};
            for (String colName : columnNames) {
                tableModel.addColumn(colName);
            }
            
            int count = 0;
            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("book_name");
                row[1] = rs.getString("author");
                row[2] = rs.getString("category");
                row[3] = rs.getDouble("price");
                row[4] = rs.getDate("publication_date");
                row[5] = rs.getInt("category_rank");
                tableModel.addRow(row);
                count++;
            }
            
            if (count == 0) {
                JOptionPane.showMessageDialog(this, "No books found in category: " + category);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Search Error: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void viewAllBooks() {
        String query = "SELECT id, book_name, author, category, price, publication_date FROM library ORDER BY id";
        executeQuery(query, new String[]{"ID", "Book Name", "Author", "Category", "Price", "Publication Date"});
    }
    
    private boolean validateInputs() {
        if (txtBookName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter book name!");
            return false;
        }
        if (txtAuthor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter author name!");
            return false;
        }
        if (txtCategory.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter category!");
            return false;
        }
        
        try {
            Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price!");
            return false;
        }
        
        try {
            Date.valueOf(txtDate.getText().trim());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid date (YYYY-MM-DD)!");
            return false;
        }
        
        return true;
    }
    
    private void initializeSampleData() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate("TRUNCATE TABLE library");
            
            String[] insertQueries = {
                "INSERT INTO library (book_name, author, category, price, publication_date) VALUES " +
                "('Java Programming', 'James Gosling', 'Programming', 450.00, '2023-01-15')",
                
                "INSERT INTO library (book_name, author, category, price, publication_date) VALUES " +
                "('Python Basics', 'Guido van Rossum', 'Programming', 380.00, '2023-02-20')",
                
                "INSERT INTO library (book_name, author, category, price, publication_date) VALUES " +
                "('Data Structures', 'Robert Sedgewick', 'Programming', 520.00, '2023-03-10')",
                
                "INSERT INTO library (book_name, author, category, price, publication_date) VALUES " +
                "('Database Systems', 'Raghu Ramakrishnan', 'Database', 680.00, '2023-04-05')",
                
                "INSERT INTO library (book_name, author, category, price, publication_date) VALUES " +
                "('SQL Mastery', 'Joe Celko', 'Database', 550.00, '2023-05-12')",
                
                "INSERT INTO library (book_name, author, category, price, publication_date) VALUES " +
                "('Machine Learning', 'Andrew Ng', 'AI', 750.00, '2023-06-18')",
                
                "INSERT INTO library (book_name, author, category, price, publication_date) VALUES " +
                "('Deep Learning', 'Ian Goodfellow', 'AI', 820.00, '2023-07-22')",
                
                "INSERT INTO library (book_name, author, category, price, publication_date) VALUES " +
                "('Web Development', 'Jon Duckett', 'Programming', 420.00, '2023-08-14')",
                
                "INSERT INTO library (book_name, author, category, price, publication_date) VALUES " +
                "('Data Mining', 'Jiawei Han', 'Database', 640.00, '2023-09-08')",
                
                "INSERT INTO library (book_name, author, category, price, publication_date) VALUES " +
                "('AI Ethics', 'Stuart Russell', 'AI', 590.00, '2023-10-25')"
            };
            
            for (String query : insertQueries) {
                stmt.executeUpdate(query);
            }
            
            JOptionPane.showMessageDialog(this, "Sample data inserted successfully!");
            viewAllBooks();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error inserting data: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void rankBooksByPrice() {
        String query = "SELECT book_name, author, category, price, " +
                "RANK() OVER (ORDER BY price DESC) AS price_rank " +
                "FROM library";
        
        executeQuery(query, new String[]{"Book Name", "Author", "Category", "Price", "Rank"});
    }
    
    private void rowNumberByDate() {
        String query = "SELECT book_name, author, publication_date, price, " +
                "ROW_NUMBER() OVER (ORDER BY publication_date ASC) AS row_num " +
                "FROM library";
        
        executeQuery(query, new String[]{"Book Name", "Author", "Publication Date", "Price", "Row Number"});
    }
    
    private void compareWithPreviousPrice() {
        String query = "SELECT book_name, category, price, " +
                "LAG(price) OVER (ORDER BY price) AS previous_price, " +
                "ROUND(price - LAG(price) OVER (ORDER BY price), 2) AS price_difference " +
                "FROM library";
        
        executeQuery(query, new String[]{"Book Name", "Category", "Price", "Previous Price", "Difference"});
    }
    
    private void compareWithNextPrice() {
        String query = "SELECT book_name, category, price, " +
                "LEAD(price) OVER (ORDER BY price) AS next_price, " +
                "ROUND(LEAD(price) OVER (ORDER BY price) - price, 2) AS price_gap " +
                "FROM library";
        
        executeQuery(query, new String[]{"Book Name", "Category", "Price", "Next Price", "Gap"});
    }
    
    private void avgPriceByCategory() {
        String query = "SELECT book_name, category, price, " +
                "ROUND(AVG(price) OVER (PARTITION BY category), 2) AS category_avg, " +
                "ROUND(price - AVG(price) OVER (PARTITION BY category), 2) AS diff_from_avg " +
                "FROM library " +
                "ORDER BY category, price DESC";
        
        executeQuery(query, new String[]{"Book Name", "Category", "Price", "Category Avg", "Diff from Avg"});
    }
    
    private void executeQuery(String query, String[] columnNames) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            
            for (String colName : columnNames) {
                tableModel.addColumn(colName);
            }
            
            while (rs.next()) {
                Object[] row = new Object[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel.addRow(row);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Query Error: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            LibraryManagementSystem frame = new LibraryManagementSystem();
            frame.setVisible(true);
        });
    }
}