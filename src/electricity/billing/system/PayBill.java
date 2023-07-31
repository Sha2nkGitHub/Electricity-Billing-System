package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener {
    private String meter;
    private JButton pay, close;
    private Choice months;

    PayBill(String meter) {
        this.meter = meter;
        setLayout(null);
        setBounds(300, 150, 1000, 480);

        add(Styles.createJLabel("ELECTRIC BILL", 150,25,200,40,  new Font("Tahoma", Font.BOLD, 25), Color.BLACK));

        final Font LABEL_FONT = new Font("Tahoma", Font.PLAIN, 16);

        add(Styles.createJLabel("Meter Number", 40, 80, LABEL_FONT, Color.DARK_GRAY));

        JLabel meternumber = Styles.createJLabel("", 200, 80,200, 20, new Font("Helvetica", Font.BOLD, 17), Color.BLACK);
        add(meternumber);

        add(Styles.createJLabel("Name", 40,130, LABEL_FONT, Color.DARK_GRAY));

        JLabel name = Styles.createJLabel("", 200, 130, 200, 20,new Font("Helvetica", Font.BOLD, 17), Color.BLACK);
        add(name);

        add(Styles.createJLabel("Month", 40,180, LABEL_FONT, Color.DARK_GRAY));

        months = new Choice();
        months.setBounds(200, 180, 200, 20);
        String[] monthList = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        for (String month : monthList) {
            months.add(month);
        }
        add(months);

        add(Styles.createJLabel("Units", 40,230, LABEL_FONT, Color.DARK_GRAY));

        JLabel units = Styles.createJLabel("", 200, 230, new Font("Helvetica", Font.BOLD, 17), Color.BLACK);
        add(units);

        add(Styles.createJLabel("Total Bill", 40,280, LABEL_FONT, Color.DARK_GRAY));

        JLabel totalbill = Styles.createJLabel("", 200, 280, new Font("Helvetica", Font.BOLD, 17), Color.BLACK);
        add(totalbill);

        add(Styles.createJLabel("Status", 40,330, LABEL_FONT, Color.DARK_GRAY));

        JLabel status = Styles.createJLabel("", 200, 330, new Font("Helvetica", Font.BOLD, 17), Color.BLACK);
        add(status);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE meter_no='" + meter + "'");
            while (rs.next()) {
                meternumber.setText(meter);
                name.setText(rs.getString("name"));
            }

            rs = c.s.executeQuery(
                    "SELECT * FROM bill WHERE meter_no='" + meter + "' AND month='" + months.getSelectedItem() + "'");
            while (rs.next()) {
                units.setText(rs.getString("units"));
                totalbill.setText(rs.getString("totalbill"));
                status.setText(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        months.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(
                            "SELECT * FROM bill WHERE meter_no='" + meter + "' AND month='" + months.getSelectedItem()
                                    + "'");
                    while (rs.next()) {
                        units.setText(rs.getString("units"));
                        totalbill.setText(rs.getString("totalbill"));
                        status.setText(rs.getString("status"));
                    }
                    if(status.getText().equals("Paid")){
                        pay.setEnabled(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pay = Styles.createJButton("Pay", 120,380);
        pay.addActionListener(this);
        add(pay);

        close = Styles.createJButton("Close", 280,380, Styles.CANCEL_BUTTON);
        close.addActionListener(event -> setVisible(false));
        add(close);

        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/pay-bill-2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(480, 20, 450, 450);
        add(img);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            try {
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status='Paid' where meter_no='" + meter + "' and month='"
                        + months.getSelectedItem() + "'");

            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        } else if (ae.getSource() == close) {
            setVisible(false);
        }
    }
}
