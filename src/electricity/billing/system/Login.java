package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    private JButton login, cancel, signup;
    private JTextField username, password;
    private Choice loginas;

    Login() {
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        add(Styles.createJLabel("Username", 300, 50, 110, 20));

        username = new JTextField();
        username.setBounds(420, 50, 150, 20);
        add(username);

        add(Styles.createJLabel("Password", 300, 90, 110, 20));

        password = new JTextField();
        password.setBounds(420, 90, 150, 20);
        add(password);

        add(Styles.createJLabel("Login As", 300, 130, 110, 20));

        loginas = new Choice();
        loginas.add("Admin");
        loginas.add("Customer");
        loginas.setBounds(420, 130, 150, 20);
        add(loginas);

        login = Styles.createJButtonWithIcon("Login", 270,220, Styles.DEFAULT_BUTTON_COLOR, "icon/log-in-icon.png");
        login.addActionListener(this);
        add(login);

        signup = Styles.createJButtonWithIcon("Signup", 390,220, Styles.DEFAULT_BUTTON_COLOR, "icon/new-registration-icon.png");
        signup.addActionListener(this);
        add(signup);

        cancel = Styles.createJButtonWithIcon("Cancel", 510,220, Styles.CANCEL_BUTTON, "icon/cross-icon.png");
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/user-icon.png"));
        Image i8 = i7.getImage().getScaledInstance(235, 235, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i8));
        img.setBounds(20, 35, 235, 235);
        add(img);

        setSize(680, 300);
        setLocation(480, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String susername = username.getText();
            String spassword = password.getText();
            String sloginas = loginas.getSelectedItem();

            try {
                Conn c = new Conn();
                String query = "SELECT * FROM login WHERE username='" + susername + "' AND password='" + spassword
                        + "' AND usertype='" + sloginas + "'";

                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Home(sloginas, meter);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
            System.exit(0);
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new Signup();
        }
    }
}