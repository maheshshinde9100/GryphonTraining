package day8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class Day8 extends JFrame implements ActionListener {
    JTextField tfName, tfAge, tfGender, tfCourse, tfYear,
            tfEmail, tfPhone, tfAddress, tfGPA, tfId;
    JButton btnInsert, btnFetch, btnUpdate, btnDelete, btnOrderBy;

    JTable table;
    DefaultTableModel model;

    JTextField tfSearch1, tfSearch2;
    JComboBox<String> cbSearchType;
    JButton btnSearch;

    // Jdbc configuration
    Connection con;
    Statement stmt;

    //database configuration
    final String username = "root";
    final String password = "mahesh";
    final String dbUrl = "jdbc:mysql://localhost:3306/crud_db";

    Day8() {
        setTitle("Mahesh's Team Management System.");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for form.
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 5, 5));
        
        formPanel.add(new JLabel("ID : "));
        tfId = new JTextField();
        formPanel.add(tfId);
        
        formPanel.add(new JLabel("Name : "));
        tfName = new JTextField();
        formPanel.add(tfName);

        formPanel.add(new JLabel("Age : "));
        tfAge = new JTextField();
        formPanel.add(tfAge);

        formPanel.add(new JLabel("Gender : "));
        tfGender = new JTextField();
        formPanel.add(tfGender);

        formPanel.add(new JLabel("Course : "));
        tfCourse = new JTextField();
        formPanel.add(tfCourse);

        formPanel.add(new JLabel("Year (YYYY-MM-DD) : "));
        tfYear = new JTextField();
        formPanel.add(tfYear);

        formPanel.add(new JLabel("Email : "));
        tfEmail = new JTextField();
        formPanel.add(tfEmail);

        formPanel.add(new JLabel("Phone : "));
        tfPhone = new JTextField();
        formPanel.add(tfPhone);

        formPanel.add(new JLabel("Address : "));
        tfAddress = new JTextField();
        formPanel.add(tfAddress);

        formPanel.add(new JLabel("GPA : "));
        tfGPA = new JTextField();
        formPanel.add(tfGPA);

        btnInsert = new JButton("Insert");
        btnDelete = new JButton("Delete");
        btnFetch = new JButton("Fetch");
        btnOrderBy = new JButton("Order By GPA");
        btnUpdate = new JButton("Update");

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnFetch);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnOrderBy);

        cbSearchType = new JComboBox<>(new String[] {
                "WHERE GPA >", "LIKE Name", "BETWEEN GPA"
        });

        tfSearch1 = new JTextField(10);
        tfSearch2 = new JTextField(10);
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);

        // search panel
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search Type : "));
        searchPanel.add(cbSearchType);
        searchPanel.add(tfSearch1);
        searchPanel.add(tfSearch2);
        searchPanel.add(btnSearch);

        add(searchPanel, BorderLayout.SOUTH);

        formPanel.add(buttonPanel);
        add(formPanel, BorderLayout.NORTH);

        // Table initialization
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane, BorderLayout.CENTER);

        // Set table columns
        String[] columns = {"ID", "Name", "Age", "Gender", "Course", "Year", "Email", "Phone", "Address", "GPA"};
        model.setColumnIdentifiers(columns);

        // button listeners
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnOrderBy.addActionListener(this);
        btnFetch.addActionListener(this);
        btnInsert.addActionListener(this);

        // database connection
        try {
            con = DriverManager.getConnection(dbUrl, username, password);
            stmt = con.createStatement();
            JOptionPane.showMessageDialog(this, "Connected to Database!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error : " + e.getMessage());
        }
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFetch) {
            fetchData();
        }
        if (e.getSource() == btnInsert) {
            insertData();
        }
        if (e.getSource() == btnSearch) {
            searchData();
        }
        if (e.getSource() == btnUpdate) {
            updateData();
        }
        if (e.getSource() == btnDelete) {
            deleteData();
        }
        if (e.getSource() == btnOrderBy) {
            orderByGPA();
        }
    }

    private void fetchData() {
        try {
            String query = "SELECT * FROM student_db";
            ResultSet rs = stmt.executeQuery(query);
            
            // Clear existing data
            model.setRowCount(0);
            
            // Populate table
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("course"),
                    rs.getDate("year"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getDouble("gpa")
                };
                model.addRow(row);
            }
            
            JOptionPane.showMessageDialog(this, "Data fetched successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage());
        }
    }

    private void insertData() {
        try {
            String id = tfId.getText().trim();
            String name = tfName.getText().trim();
            String age = tfAge.getText().trim();
            String gender = tfGender.getText().trim();
            String course = tfCourse.getText().trim();
            String year = tfYear.getText().trim();
            String email = tfEmail.getText().trim();
            String phone = tfPhone.getText().trim();
            String address = tfAddress.getText().trim();
            String gpa = tfGPA.getText().trim();

            // Validate required fields
            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID and Name are required!");
                return;
            }

            String query = "INSERT INTO student_db (id, name, age, gender, course, year, email, phone, address, gpa) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.setString(2, name);
            pstmt.setInt(3, age.isEmpty() ? 0 : Integer.parseInt(age));
            pstmt.setString(4, gender);
            pstmt.setString(5, course);
            pstmt.setDate(6, year.isEmpty() ? null : Date.valueOf(year));
            pstmt.setString(7, email);
            pstmt.setString(8, phone);
            pstmt.setString(9, address);
            pstmt.setDouble(10, gpa.isEmpty() ? 0.0 : Double.parseDouble(gpa));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record inserted successfully!");
                clearFields();
                fetchData();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inserting data: " + ex.getMessage());
        }
    }

    private void updateData() {
        try {
            String id = tfId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter ID to update!");
                return;
            }

            String name = tfName.getText().trim();
            String age = tfAge.getText().trim();
            String gender = tfGender.getText().trim();
            String course = tfCourse.getText().trim();
            String year = tfYear.getText().trim();
            String email = tfEmail.getText().trim();
            String phone = tfPhone.getText().trim();
            String address = tfAddress.getText().trim();
            String gpa = tfGPA.getText().trim();

            StringBuilder query = new StringBuilder("UPDATE student_db SET ");
            boolean hasUpdates = false;

            if (!name.isEmpty()) {
                query.append("name=?, ");
                hasUpdates = true;
            }
            if (!age.isEmpty()) {
                query.append("age=?, ");
                hasUpdates = true;
            }
            if (!gender.isEmpty()) {
                query.append("gender=?, ");
                hasUpdates = true;
            }
            if (!course.isEmpty()) {
                query.append("course=?, ");
                hasUpdates = true;
            }
            if (!year.isEmpty()) {
                query.append("year=?, ");
                hasUpdates = true;
            }
            if (!email.isEmpty()) {
                query.append("email=?, ");
                hasUpdates = true;
            }
            if (!phone.isEmpty()) {
                query.append("phone=?, ");
                hasUpdates = true;
            }
            if (!address.isEmpty()) {
                query.append("address=?, ");
                hasUpdates = true;
            }
            if (!gpa.isEmpty()) {
                query.append("gpa=?, ");
                hasUpdates = true;
            }

            if (!hasUpdates) {
                JOptionPane.showMessageDialog(this, "No fields to update!");
                return;
            }

            // Remove last comma and space
            query.delete(query.length() - 2, query.length());
            query.append(" WHERE id=?");

            PreparedStatement pstmt = con.prepareStatement(query.toString());
            int paramIndex = 1;

            if (!name.isEmpty()) pstmt.setString(paramIndex++, name);
            if (!age.isEmpty()) pstmt.setInt(paramIndex++, Integer.parseInt(age));
            if (!gender.isEmpty()) pstmt.setString(paramIndex++, gender);
            if (!course.isEmpty()) pstmt.setString(paramIndex++, course);
            if (!year.isEmpty()) pstmt.setDate(paramIndex++, Date.valueOf(year));
            if (!email.isEmpty()) pstmt.setString(paramIndex++, email);
            if (!phone.isEmpty()) pstmt.setString(paramIndex++, phone);
            if (!address.isEmpty()) pstmt.setString(paramIndex++, address);
            if (!gpa.isEmpty()) pstmt.setDouble(paramIndex++, Double.parseDouble(gpa));

            pstmt.setInt(paramIndex, Integer.parseInt(id));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully!");
                clearFields();
                fetchData();
            } else {
                JOptionPane.showMessageDialog(this, "No record found with ID: " + id);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating data: " + ex.getMessage());
        }
    }

    private void deleteData() {
        try {
            String id = tfId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter ID to delete!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete record with ID: " + id + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                String query = "DELETE FROM student_db WHERE id=?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, Integer.parseInt(id));

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Record deleted successfully!");
                    clearFields();
                    fetchData();
                } else {
                    JOptionPane.showMessageDialog(this, "No record found with ID: " + id);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting data: " + ex.getMessage());
        }
    }

    private void searchData() {
        try {
            String searchType = (String) cbSearchType.getSelectedItem();
            String value1 = tfSearch1.getText().trim();
            String value2 = tfSearch2.getText().trim();

            String query = "SELECT * FROM student_db WHERE ";

            switch (searchType) {
                case "WHERE GPA >":
                    if (value1.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter GPA value!");
                        return;
                    }
                    query += "gpa > " + value1;
                    break;
                    
                case "LIKE Name":
                    if (value1.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter name pattern!");
                        return;
                    }
                    query += "name LIKE '%" + value1 + "%'";
                    break;
                    
                case "BETWEEN GPA":
                    if (value1.isEmpty() || value2.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter both GPA values!");
                        return;
                    }
                    query += "gpa BETWEEN " + value1 + " AND " + value2;
                    break;
            }

            ResultSet rs = stmt.executeQuery(query);
            model.setRowCount(0);
            
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("course"),
                    rs.getDate("year"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getDouble("gpa")
                };
                model.addRow(row);
            }
            
            if (!hasResults) {
                JOptionPane.showMessageDialog(this, "No records found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error searching data: " + ex.getMessage());
        }
    }

    private void orderByGPA() {
        try {
            String query = "SELECT * FROM student_db ORDER BY gpa DESC";
            ResultSet rs = stmt.executeQuery(query);
            
            model.setRowCount(0);
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("course"),
                    rs.getDate("year"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getDouble("gpa")
                };
                model.addRow(row);
            }
            
            JOptionPane.showMessageDialog(this, "Data ordered by GPA (descending)!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error ordering data: " + ex.getMessage());
        }
    }

    private void clearFields() {
        tfId.setText("");
        tfName.setText("");
        tfAge.setText("");
        tfGender.setText("");
        tfCourse.setText("");
        tfYear.setText("");
        tfEmail.setText("");
        tfPhone.setText("");
        tfAddress.setText("");
        tfGPA.setText("");
        tfSearch1.setText("");
        tfSearch2.setText("");
    }

    public static void main(String[] args) {
        new Day8();
    }
}