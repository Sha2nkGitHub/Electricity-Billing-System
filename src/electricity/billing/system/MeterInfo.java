package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener {
    private JButton submit;
    private Choice meterlocation, metertype, phasecode, billtype;
    private String meter;

    MeterInfo(String meter) {
        this.meter = meter;
        setSize(1000, 500);
        setLocation(300, 150);
        setLayout(null);

        add(Styles.createJLabel("METER INFORMATION", 540, 25, 400, 40, new Font("Tahoma", Font.BOLD, 25), Color.BLACK));

        final Font LABEL_FONT = new Font("Tahoma", Font.PLAIN, 16);

        add(Styles.createJLabel("Meter Number", 520, 80, LABEL_FONT, Color.DARK_GRAY));

        add(Styles.createJLabel(meter, 680, 80, LABEL_FONT, Color.BLACK));

        add(Styles.createJLabel("Meter Location", 520, 130, LABEL_FONT, Color.DARK_GRAY));

        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(680, 130, 200, 20);
        add(meterlocation);

        add(Styles.createJLabel("Meter Type", 520, 180, LABEL_FONT, Color.DARK_GRAY));

        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(680, 180, 200, 20);
        add(metertype);

        add(Styles.createJLabel("Phase Code", 520, 230, LABEL_FONT, Color.DARK_GRAY));

        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(680, 230, 200, 20);
        add(phasecode);

        add(Styles.createJLabel("Bill Type", 520, 280, LABEL_FONT, Color.DARK_GRAY));

        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(680, 280, 200, 20);
        add(billtype);

        add(Styles.createJLabel("Days", 520, 330, LABEL_FONT, Color.DARK_GRAY));

        add(Styles.createJLabel("30 Days", 680, 330, LABEL_FONT, Color.DARK_GRAY));

        add(Styles.createJLabel("Note", 520, 380, LABEL_FONT, Color.DARK_GRAY));

        add(Styles.createJLabel("By default, bill is calculated for 30 Days only", 650, 380, 350, 20, LABEL_FONT,
                Color.DARK_GRAY));

        submit = Styles.createJButton("Submit", 660, 430);
        submit.addActionListener(this);
        add(submit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/meter-info.png"));
        Image i2 = i1.getImage().getScaledInstance(270, 470, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(100, 20, 270, 470);
        add(img);

        getContentPane().setBackground(Color.WHITE);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String meter = this.meter;
            String location = meterlocation.getSelectedItem();
            String mtype = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String btype = billtype.getSelectedItem();
            String days = "30";
            try {
                String query = "INSERT INTO meter_info VALUES('" + meter + "', '" + location + "', '" + mtype + "', '"
                        + code + "', '" + btype + "', '" + days + "')";

                Conn c = new Conn();
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Meter Information added successfully");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}