package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    private JButton create, back;
    private Choice accountType;
    private JTextField meter, username, name, password;

    Signup() {
        setBounds(450, 150, 720, 370);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(20, 25, 680, 325);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(60, 170, 250), 2), "Create Account",
                TitledBorder.LEADING, TitledBorder.TOP, new Font("Raleway", Font.BOLD, 16), new Color(60, 160, 255)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 39, 34));
        add(panel);

        final Font LABEL_FONT = new Font("Tahoma", Font.BOLD, 16);

        panel.add(Styles.createJLabel("Create Account As", 60, 50,150,20, LABEL_FONT, Color.DARK_GRAY));

        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(230, 50, 150, 20);
        panel.add(accountType);

        JLabel lblmeter = Styles.createJLabel("Meter Number", 70,90,140,20, LABEL_FONT, Color.DARK_GRAY);
        //lblmeter.setVisible(false);
        panel.add(lblmeter);

        meter = new JTextField();
        meter.setBounds(230, 90, 150, 20);
        meter.setVisible(false);
        panel.add(meter);

        panel.add(Styles.createJLabel("Username", 70, 130, 140,20, LABEL_FONT, Color.DARK_GRAY));

        username = new JTextField();
        username.setBounds(230, 130, 150, 20);
        panel.add(username);

        panel.add(Styles.createJLabel("Full Name", 70, 170, 140,20, LABEL_FONT, Color.DARK_GRAY));

        name = new JTextField();
        name.setBounds(230, 170, 150, 20);
        panel.add(name);

        meter.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent fe) {
            }

            public void focusLost(FocusEvent fe) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no='" + meter.getText() + "'");
                    while (rs.next()) {
                        name.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        panel.add(Styles.createJLabel("Password", 70, 210, 140,20, LABEL_FONT, Color.DARK_GRAY));

        password = new JTextField();
        password.setBounds(230, 210, 150, 20);
        panel.add(password);

        accountType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                String user = accountType.getSelectedItem();
                if (user.equals("Customer")) {
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                } else {
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });

        create = Styles.createJButton("Create", 100, 260);
        create.addActionListener(this);
        panel.add(create);

        back = Styles.createJButton("Back", 240, 260);
        back.addActionListener(this);
        panel.add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signup-img.png"));
        Image i2 = i1.getImage().getScaledInstance(235, 235, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(415, 45, 235, 235);
        panel.add(img);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            String atype = accountType.getSelectedItem();
            String smeter = meter.getText();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            try {
                Conn c = new Conn();
                String query = null;
                if (atype.equals("Admin")) {
                    query = "insert into login values('" + smeter + "', '" + susername + "', '" + sname + "', '"
                            + spassword + "', '" + atype + "')";
                } else {
                    query = "update login set username='" + susername + "', password='" + spassword + "', usertype='"
                            + atype + "' where meter_no='" + smeter + "'";
                }

                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Account Created Successfully");

                setVisible(false);
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }
}