package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {
    private String usertype, meter;

    Home(String usertype, String meter) {
        this.usertype = usertype;
        this.meter = meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/home-screen-1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1540, 850, Image.SCALE_DEFAULT);
        JLabel background = new JLabel(new ImageIcon(i2));

        JLabel title = new JLabel("Edison's Electricity");
        title.setFont(new Font("Raleway", Font.BOLD, 85));
        title.setForeground(new Color(136, 24, 158));
        title.setBounds(380, 110, 850, 95);
        background.add(title);
        add(background);

        JLabel tagline = new JLabel("Lights up your life");
        tagline.setFont(new Font("Ink Free", Font.ITALIC, 35));
        tagline.setForeground(Color.DARK_GRAY);
        tagline.setBounds(480, 220, 400, 45);
        background.add(tagline);

        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);

        JMenu master = createJMenu("   Master   ", Color.BLUE);

        JMenuItem newcustomer = createMenuItem("New Customer", "icon/new-customer-2.png");
        newcustomer.setMnemonic('N');
        newcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        master.add(newcustomer);

        JMenuItem customerdetails = createMenuItem("Customer Details", "icon/customer-details.png");
        customerdetails.setMnemonic('D');
        customerdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        master.add(customerdetails);

        JMenuItem depositdetails = createMenuItem("Deposit Details", "icon/deposit-details.png");
        depositdetails.setMnemonic('P');
        depositdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        master.add(depositdetails);

        JMenuItem calculatebill = createMenuItem("Calculate Bill", "icon/calculate-bill-icon.png");
        calculatebill.setMnemonic('B');
        calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        master.add(calculatebill);

        JMenu info = createJMenu(" Information", Color.RED);

        JMenuItem viewinformation = createMenuItem("View Information", "icon/view-information.png");
        viewinformation.setMnemonic('V');
        viewinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        info.add(viewinformation);

        JMenuItem updateinformation = createMenuItem("Update Information", "icon/update-information.png");
        updateinformation.setMnemonic('U');
        updateinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        info.add(updateinformation);

        JMenu user = createJMenu("    User    ", Color.BLUE);

        JMenuItem paybill = createMenuItem("Pay Bill", "icon/pay-bill.png");
        paybill.setMnemonic('Y');
        paybill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        user.add(paybill);

        JMenuItem billdetails = createMenuItem("Bill Details", "icon/bill-details.png");
        billdetails.setMnemonic('L');
        billdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        user.add(billdetails);

        JMenu report = createJMenu("   Report   ", Color.RED);

        JMenuItem generatebill = createMenuItem("Generate Bill", "icon/generate-bill.png");
        generatebill.setMnemonic('G');
        generatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        report.add(generatebill);

        JMenu utility = createJMenu(" Utilities  ", Color.BLUE);

        JMenuItem notepad = createMenuItem("Notepad", "icon/notepad.png");
        notepad.setMnemonic('T');
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        utility.add(notepad);

        JMenuItem calculator = createMenuItem("Calculator", "icon/calculator.png");
        calculator.setMnemonic('C');
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        utility.add(calculator);

        JMenu mexit = createJMenu("    Exit    ", Color.RED);

        JMenuItem exit = createMenuItem("Exit", "icon/cross-icon.png");
        exit.setMnemonic('W');
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        mexit.add(exit);

        if (this.usertype.equals("Admin")) {
            mb.add(master);
        } else {
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }

        mb.add(utility);
        mb.add(mexit);

        setLayout(new FlowLayout());
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("New Customer")) {
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
            new CustomerDetails();
        } else if (msg.equals("Deposit Details")) {
            new DepositDetails();
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill();
        } else if (msg.equals("View Information")) {
            new ViewInformation(meter);
        } else if (msg.equals("Update Information")) {
            new UpdateInformation(meter);
        } else if (msg.equals("Bill Details")) {
            new BillDetails(meter);
        } else if (msg.equals("Pay Bill")) {
            new PayBill(meter);
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Generate Bill")) {
            new GenerateBill(meter);
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Exit")) {
            setVisible(false);
            new Login();
        }
    }

    private JMenu createJMenu(String text, Color color) {
        JMenu menu = new JMenu(text);
        menu.setForeground(color);
        menu.setFont(new Font("Tahoma", Font.BOLD, 18));
        return menu;
    }

    private JMenuItem createMenuItem(String text, String iconURL) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(new Font("monospace", Font.PLAIN, 18));
        item.setBackground(new Color(240, 240, 240));
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(iconURL));
        Image i2 = i1.getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT);
        item.setIcon(new ImageIcon(i2));
        item.addActionListener(this);
        return item;
    }
}