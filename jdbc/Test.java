import java.sql.*;

public class Test {
    public static void main(String[] args) {
        String username = "root";
        String password = "mahesh";
        String dbUrl = "jdbc:mysql://localhost:3306/crud_db";

        try {
            Connection con = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from students;");
            System.out.println("RollNo |     Name     |  Stipend");
            System.out.println("--------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getDouble(3));
            }
            con.close();
            System.out.println("--------------------------");
            System.out.println("Connected successfully...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
