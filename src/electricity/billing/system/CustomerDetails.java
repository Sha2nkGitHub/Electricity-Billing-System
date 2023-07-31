package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener {
    Choice meternumbers, months;
    JTable table;
    JButton search, print;

    CustomerDetails() {
        super("Customer Details");

        setSize(1250, 650);
        setLocation(200, 70);

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT name AS 'Name', meter_no AS 'Meter Number', address AS 'Address', city AS 'City', state AS 'State', email AS 'Email', phone AS 'Phone' FROM customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.setBackground(Color.WHITE);
        Styles.styleJTable(table);

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(sp);

        print = new JButton("Print");
        print.addActionListener(this);
        add(print, "South");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            table.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}