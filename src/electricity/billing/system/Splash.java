package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    Splash() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/splash screen.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 550, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        add(img);

        setUndecorated(true);
        setVisible(true);
        int x = 1;
        for (int i = 2; i < 550; i += 4, x++) {
            setSize(i + x, i);
            setLocation(800 - (i + x) / 2, 400 - i / 2);
            try {
                Thread.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        new Thread(this).start();
    }

    public void run() {
        try {
            Thread.sleep(5000);
            setVisible(false);
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        new Splash();
    }
}