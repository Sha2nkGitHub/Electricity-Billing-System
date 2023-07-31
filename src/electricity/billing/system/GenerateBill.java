package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener {
    Choice months;
    String meter;
    JButton bill;
    JTextArea area;

    GenerateBill(String meter) {
        this.meter = meter;
        setSize(550, 780);
        setLocation(500, 30);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JLabel heading = new JLabel("Generate Bill");
        JLabel lblmeter = new JLabel(meter);

        months = new Choice();
        months.setBounds(680, 280, 200, 20);
        String[] monthList = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        for (String month : monthList) {
            months.add(month);
        }

        area = new JTextArea(50, 15);
        area.setText(
                "\n\n\t-----------Click on the  --------------\n\tGenerate Bill Button to get \n\tthe bill of selected month");
        area.setFont(new Font("sans-serif", Font.PLAIN, 18));
        area.setEditable(false);

        JScrollPane sp = new JScrollPane(area);

        JButton bill = new JButton("Generate Bill");
        bill.addActionListener(this);
        bill.setBackground(new Color(2, 147, 253));
        bill.setForeground(Color.WHITE);
        bill.setSize(WIDTH, 28);

        panel.add(heading);
        panel.add(lblmeter);
        panel.add(months);
        sp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panel, "North");
        add(sp, "Center");
        add(bill, "South");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            String month = months.getSelectedItem();
            area.setText("\t       Edison's Electricity\n              ELECTRICITY BILL GENERATED FOR\n\tTHE MONTH OF "
                    + month + " , 2023\n");
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no='" + meter + "'");
            if (rs.next()) {
                area.append("\n    Customer Name\t:  " + rs.getString("name"));
                area.append("\n    Meter Number \t:  " + rs.getString("meter_no"));
                area.append("\n    Address\t\t:  " + rs.getString("address"));
                area.append("\n    City\t\t:  " + rs.getString("city"));
                area.append("\n    State\t\t:  " + rs.getString("state"));
                area.append("\n    Email\t\t:  " + rs.getString("email"));
                area.append("\n    Phone\t\t:  " + rs.getString("phone"));
                area.append("\n    :::::::::::::::::::::::::::::::::::::::::::::::::\n");
            }

            rs = c.s.executeQuery("SELECT * FROM meter_info WHERE meter_no='" + meter + "'");
            if (rs.next()) {
                area.append("\n    Meter Location\t\t:  " + rs.getString("meter_location"));
                area.append("\n    Meter Type\t\t:  " + rs.getString("meter_type"));
                area.append("\n    Phase Code\t:  " + rs.getString("phase_code"));
                area.append("\n    Bill Type\t\t:  " + rs.getString("bill_type"));
                area.append("\n    Days\t\t:  " + rs.getString("days"));
                area.append("\n    :::::::::::::::::::::::::::::::::::::::::::::::::\n");
            }

            rs = c.s.executeQuery("SELECT * FROM tax");
            if (rs.next()) {
                area.append("\n    Cost per Unit\t:  " + rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent\t\t:  " + rs.getString("meter_rent"));
                area.append("\n    Service Charge\t:  " + rs.getString("service_charge"));
                area.append("\n    Service Tax\t:  " + rs.getString("service_tax"));
                area.append("\n    Swacch Bharat Cess\t:  " + rs.getString("swacch_bharat_cess"));
                area.append("\n    Fixed Tax\t\t:  " + rs.getString("fixed_tax"));
                area.append("\n    :::::::::::::::::::::::::::::::::::::::::::::::::\n");
            }

            rs = c.s.executeQuery("SELECT * FROM bill WHERE meter_no='" + meter + "' AND month='" + month + "'");
            if (rs.next()) {
                area.append("\n    Current Month\t:  " + rs.getString("month"));
                area.append("\n    Units Consumed\t:  " + rs.getString("units"));
                area.append("\n    Total Charges\t:  " + rs.getString("totalbill"));
                area.append("\n    :::::::::::::::::::::::::::::::::::::::::::::::::\n");
                area.append("\n   TOTAL PAYABLE\t:  ₹" + rs.getString("totalbill") + " /-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
