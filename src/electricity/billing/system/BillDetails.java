package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame {
    String meter;

    BillDetails(String meter) {
        this.meter = meter;
        setSize(800, 660);
        setLocation(350, 70);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        add(Styles.createJLabel("BILL DETAILS", 300,20,200,30, new Font("Tahoma", Font.BOLD, 20), Color.BLACK));

        JTable table = new JTable();
        try {
            Conn c = new Conn();
            String query = "SELECT meter_no AS 'Meter Number', units AS 'Units', month AS 'Month', totalbill AS 'Total Bill', status AS 'Status' FROM bill WHERE meter_no='" + meter + "'";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Styles.styleJTable(table);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10, 60, 770, 560);
        add(sp);

        setVisible(true);
    }
}
