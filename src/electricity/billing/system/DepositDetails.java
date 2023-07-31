package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener {
    private Choice meternumbers, months;
    private JTable table;
    private JButton search, print;

    DepositDetails() {
        super("Deposit Details");

        setSize(800, 700);
        setLocation(350, 70);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        add(Styles.createJLabel("Search By Meter Number",20,20,190,20));

        meternumbers = new Choice();
        meternumbers.setBounds(220, 20, 120, 20);
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                meternumbers.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        add(meternumbers);

        add(Styles.createJLabel("Search By Month", 390,20,150,20));

        months = new Choice();
        months.setBounds(550, 20, 120, 20);
        String[] monthList = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        for (String month : monthList) {
            months.add(month);
        }
        add(months);

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(
                    "SELECT meter_no AS 'Meter Number', units AS 'Units', month AS 'Month', totalbill AS 'Total Bill', status AS 'Status' FROM bill");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Styles.styleJTable(table);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10, 110, 770, 550);
        add(sp);

        search = Styles.createJButton("Search",20,70,110,25);
        search.addActionListener(this);
        add(search);

        print = Styles.createJButton("Print",140,70,110,25);
        print.addActionListener(this);
        add(print);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "SELECT meter_no AS 'Meter Number', units AS 'Units', month AS 'Month', totalbill AS 'Total Bill', status AS 'Status' FROM bill WHERE meter_no='"
                    + meternumbers.getSelectedItem() + "' AND month='" + months.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}