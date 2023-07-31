package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener {
    private JTextField address, city, state, email, phone;
    private JButton update, cancel;
    private String meter;

    UpdateInformation(String meter) {
        this.meter = meter;
        setBounds(300, 150, 1000, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110, 25, 450, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(heading);

        final Font LABEL_FONT = new Font("Helvetica", Font.PLAIN, 16);

        add(Styles.createJLabel("Name", 40,80, LABEL_FONT, Color.DARK_GRAY));

        JLabel name = Styles.createJLabel("", 200,80, LABEL_FONT, Color.BLACK);
        add(name);

        add(Styles.createJLabel("Meter Number", 40,130,LABEL_FONT, Color.DARK_GRAY));

        JLabel meterno = Styles.createJLabel("", 200,130,LABEL_FONT, Color.BLACK);
        add(meterno);

        add(Styles.createJLabel("Address", 40,180,LABEL_FONT, Color.DARK_GRAY));

        address = new JTextField();
        address.setBounds(200, 180, 200, 20);
        add(address);

        add(Styles.createJLabel("City", 40,230,LABEL_FONT, Color.DARK_GRAY));

        city = new JTextField();
        city.setBounds(200, 230, 200, 20);
        add(city);

        add(Styles.createJLabel("State", 40,280,LABEL_FONT, Color.DARK_GRAY));

        state = new JTextField();
        state.setBounds(200, 280, 200, 20);
        add(state);

        add(Styles.createJLabel("Email", 40,330,LABEL_FONT, Color.DARK_GRAY));

        email = new JTextField();
        email.setBounds(200, 330, 200, 20);
        add(email);

        add(Styles.createJLabel("Phone", 40,380,LABEL_FONT, Color.DARK_GRAY));

        phone = new JTextField();
        phone.setBounds(200, 380, 200, 20);
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

        update = Styles.createJButton("Update", 70,430);
        update.addActionListener(this);
        add(update);

        cancel = Styles.createJButton("Cancel", 230,430, Styles.CANCEL_BUTTON);
        cancel.addActionListener(event -> setVisible(false));
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update-information-2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(480, 440, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(480, 45, 480, 440);
        add(img);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String saddress = address.getText();
            String scity = city.getText();
            String sstate = state.getText();
            String semail = email.getText();
            String sphone = phone.getText();

            try {
                Conn c = new Conn();
                c.s.executeUpdate("UPDATE customer SET address='" + saddress + "', city='" + scity + "', state='"
                        + sstate + "', email='" + semail + "', phone='" + sphone + "' WHERE meter_no='" + meter + "'");
                JOptionPane.showMessageDialog(null, "User information updated successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
}