package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class NewCustomer extends JFrame implements ActionListener {
    private JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    private JLabel meterno;
    private JButton next, cancel;

    NewCustomer() {
        setSize(1000, 500);
        setLocation(300, 150);
        setLayout(null);

        add(Styles.createJLabel("NEW CUSTOMER", 160,25,250,40, new Font("Tahoma", Font.BOLD, 25), Color.BLACK));

        final Font LABEL_FONT = new Font("Tahoma", Font.PLAIN, 16);

        add(Styles.createJLabel("Customer Name", 40,80, LABEL_FONT, Color.DARK_GRAY));

        tfname = new JTextField();
        tfname.setBounds(200, 80, 200, 20);
        add(tfname);

        add(Styles.createJLabel("Meter Number", 40,130, LABEL_FONT, Color.DARK_GRAY));

        Random ran = new Random();
        long number = ran.nextLong() % 1_000_000l;

        meterno = Styles.createJLabel("" + Math.abs(number), 220,130,new Font("Tahoma", Font.BOLD, 17), Color.BLACK);
        add(meterno);
        add(Styles.createJLabel("Address", 40,180, LABEL_FONT, Color.DARK_GRAY));

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 180, 200, 20);
        add(tfaddress);

        add(Styles.createJLabel("City", 40,230, LABEL_FONT, Color.DARK_GRAY));

        tfcity = new JTextField();
        tfcity.setBounds(200, 230, 200, 20);
        add(tfcity);

        add(Styles.createJLabel("State", 40,280, LABEL_FONT, Color.DARK_GRAY));

        tfstate = new JTextField();
        tfstate.setBounds(200, 280, 200, 20);
        add(tfstate);

        add(Styles.createJLabel("Email", 40,330, LABEL_FONT, Color.DARK_GRAY));

        tfemail = new JTextField();
        tfemail.setBounds(200, 330, 200, 20);
        add(tfemail);

        add(Styles.createJLabel("Phone Number", 40,380, LABEL_FONT, Color.DARK_GRAY));

        tfphone = new JTextField();
        tfphone.setBounds(200, 380, 200, 20);
        add(tfphone);

        next = Styles.createJButton("Next", 70,430);
        next.addActionListener(this);
        add(next);

        cancel = Styles.createJButton("Cancel", 230,430,Styles.CANCEL_BUTTON);
        cancel.addActionListener(event -> setVisible(false));
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/new-customer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(470, 470, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(500,15,470,470);
        add(img);

        getContentPane().setBackground(Color.WHITE);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String name = tfname.getText();
            String meter = meterno.getText();
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();
            try {
                String query1 = "INSERT INTO customer VALUES('" + name + "', '" + meter + "', '" + address + "', '"
                        + city + "', '" + state + "', '" + email + "', '" + phone + "')";
                String query2 = "INSERT INTO login VALUES('" + meter + "', '', '" + name + "', '', '')";

                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Customer Details added successfully");
                setVisible(false);
                new MeterInfo(meter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}