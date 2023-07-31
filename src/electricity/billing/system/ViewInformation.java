package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewInformation extends JFrame{
    private JButton close;
    private String meter;

    ViewInformation(String meter) {
        this.meter = meter;
        setBounds(350, 100, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("CUSTOMER DETAILS");
        heading.setBounds(280, 30, 500, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(heading);

        final Font LABEL_FONT = new Font("Tahoma", Font.PLAIN, 16);
        final Font LABEL_VALUE_FONT = new Font("Helvetica", Font.BOLD, 16);

        add(Styles.createJLabel("Name", 65, 100, 120, 20, LABEL_FONT, Color.DARK_GRAY));

        JLabel name = Styles.createJLabel("", 210,100,150,20, LABEL_VALUE_FONT, Color.BLACK);
        add(name);

        add(Styles.createJLabel("Meter Number", 65, 160, 120, 20, LABEL_FONT, Color.DARK_GRAY));

        JLabel meterno = Styles.createJLabel("", 210,160,150,20, LABEL_VALUE_FONT, Color.BLACK);
        add(meterno);

        add(Styles.createJLabel("Address", 65, 220, 120, 20, LABEL_FONT, Color.DARK_GRAY));

        JLabel address = Styles.createJLabel("", 210,220,150,20, LABEL_VALUE_FONT, Color.BLACK);
        add(address);

        add(Styles.createJLabel("City", 65, 280, 120, 20, LABEL_FONT, Color.DARK_GRAY));

        JLabel city = Styles.createJLabel("", 210,280,150,20, LABEL_VALUE_FONT, Color.BLACK);
        add(city);

        add(Styles.createJLabel("State", 440, 100, 120, 20, LABEL_FONT, Color.DARK_GRAY));

        JLabel state = Styles.createJLabel("", 585,100,150,20, LABEL_VALUE_FONT, Color.BLACK);
        add(state);

        add(Styles.createJLabel("Email", 440, 160, 120, 20, LABEL_FONT, Color.DARK_GRAY));

        JLabel email = Styles.createJLabel("", 585,160,150,20, LABEL_VALUE_FONT, Color.BLACK);
        add(email);

        add(Styles.createJLabel("Phone", 440, 220, 120, 20, LABEL_FONT, Color.DARK_GRAY));

        JLabel phone = Styles.createJLabel("", 585,220,150,20, LABEL_VALUE_FONT, Color.BLACK);
        add(phone);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE meter_no='" + this.meter + "'");
            while (rs.next()) {
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meterno.setText(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        close = new JButton("Close");
        close.setBackground(new Color(220,25,50));
        close.setForeground(Color.WHITE);
        close.setBounds(360, 340, 120, 30);
        close.addActionListener((event) -> setVisible(false));
        close.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
        add(close);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(650, 340, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(20, 320, 650, 340);
        add(img);

        setUndecorated(true);
        setVisible(true);
    }
}