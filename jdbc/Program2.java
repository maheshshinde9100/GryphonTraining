import java.sql.*;

public class Program2 {
    public static void main(String[] args) {
        String username = "root";
        String password = "mahesh";
        String dbUrl = "jdbc:mysql://localhost:3306/crud_db";

        try {
            Connection con = DriverManager.getConnection(dbUrl, username, password);
            // Statement stmt = con.createStatement();

            // String query = "CREATE TABLE IF NOT EXISTS STUDENTS(rollno int primary key, name varchar(100),stipend double);";
            // stmt.executeUpdate(query);

            String insertionQuery = "INSERT INTO students(rollno,name,stipend) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(insertionQuery);
            ps.setInt(1, 001);
            ps.setString(2,"Mahesh Shinde");
            ps.setDouble(3, 8000.10);
            ps.executeUpdate();

            ps.setInt(1, 002);
            ps.setString(2,"Peter Parker");
            ps.setDouble(3, 5000.10);
            ps.executeUpdate();

            ps.setInt(1, 003);
            ps.setString(2,"Henry Cavil");
            ps.setDouble(3, 9000.10);
            ps.executeUpdate();

            con.close();
            System.out.println("Table created successfully...");
            System.out.println("Insertion succesfully....");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
