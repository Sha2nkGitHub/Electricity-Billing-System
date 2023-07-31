package electricity.billing.system;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Sha2nk";

    Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", USERNAME, PASSWORD);
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
