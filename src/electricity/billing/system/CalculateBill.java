package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener {
    private JTextField tfunits;
    private JButton cancel, submit;
    private Choice meternumbers, months;

    CalculateBill() {
        setSize(1000, 430);
        setLocation(300, 150);

        add(Styles.createJLabel("CALCULATE ELECTRIC BILL",540,25,400,40, new Font("Tahoma", Font.BOLD, 25), Color.BLACK));

        final Font LABEL_FONT = new Font("Tahoma", Font.PLAIN, 16);

        add(Styles.createJLabel("Meter Number", 520,80, LABEL_FONT, Color.DARK_GRAY));

        meternumbers = new Choice();
        meternumbers.setBounds(680, 80, 200, 20);
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

        add(Styles.createJLabel("Name", 520,130,LABEL_FONT, Color.DARK_GRAY));

        JLabel name = Styles.createJLabel("", 680,130,150,20,new Font("Helvetica", Font.BOLD, 16), Color.BLACK);
        add(name);

        add(Styles.createJLabel("Address", 520,180,LABEL_FONT, Color.DARK_GRAY));

        JLabel address = Styles.createJLabel("", 680,180,new Font("Helvetica", Font.PLAIN, 16), Color.DARK_GRAY);
        add(address);

        meternumbers.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(
                            "SELECT * FROM customer WHERE meter_no='" + meternumbers.getSelectedItem() + "'");
                    while (rs.next()) {
                        name.setText(rs.getString("name"));
                        address.setText(rs.getString("address"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        add(Styles.createJLabel("Units Consumed", 520,230,LABEL_FONT, Color.DARK_GRAY));

        tfunits = new JTextField();
        tfunits.setBounds(680, 230, 200, 20);
        add(tfunits);

        add(Styles.createJLabel("Month", 520,280,LABEL_FONT, Color.DARK_GRAY));

        months = new Choice();
        months.setBounds(680, 280, 200, 20);
        String[] monthList = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        for (String month : monthList) {
            months.add(month);
        }
        add(months);

        submit = Styles.createJButtonWithIcon("Submit", 570, 350, Styles.DEFAULT_BUTTON_COLOR, "icon/submit-icon.png");
        submit.addActionListener(this);
        add(submit);

        cancel = Styles.createJButtonWithIcon("Cancel", 730, 350, Styles.CANCEL_BUTTON, "icon/cross-icon.png");
        cancel.addActionListener(this);
        add(cancel);

        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/calculate-bill.png"));
        Image i2 = i1.getImage().getScaledInstance(420, 420, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(20,20,420,420);
        add(img);

        getContentPane().setBackground(Color.WHITE);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String meter = meternumbers.getSelectedItem();
            String units = tfunits.getText();
            String month = months.getSelectedItem();
            int totalBill = 0;
            int units_consumed = Integer.parseInt(units);

            try {
                Conn c = new Conn();
                String query = "SELECT * FROM tax";
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    totalBill += units_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(rs.getString("meter_rent"));
                    totalBill += Integer.parseInt(rs.getString("service_charge"));
                    totalBill += Integer.parseInt(rs.getString("service_tax"));
                    totalBill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalBill += Integer.parseInt(rs.getString("fixed_tax"));
                    ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                String query = "INSERT INTO bill VALUES('" + meter + "', '" + units + "', '" + month + "', '"
                        + totalBill + "', 'Not Paid')";
                Conn c = new Conn();
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Customer Bill updated successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);

        }
    }
}