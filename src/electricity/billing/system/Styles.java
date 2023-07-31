package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class Styles {
    private static final int DEFAULT_LABEL_WIDTH = 120;
    private static final int DEFAULT_LABEL_HEIGHT = 20;
    public static final Color DEFAULT_BUTTON_COLOR = new Color(2, 117, 216);
    public static final Color CANCEL_BUTTON = new Color(217, 83, 79);
    public static JLabel createJLabel(String text, int x, int y, Font font, Color color){
        return createJLabel(text, x,y,DEFAULT_LABEL_WIDTH,DEFAULT_LABEL_HEIGHT,font, color);
    }

    public static JLabel createJLabel(String text, int x, int y, int w, int h) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(new Font("Helvetica", Font.PLAIN, 16));
        return label;
    }

    public static JLabel createJLabel(String text, int x, int y, int w, int h, Font font, Color c) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(font);
        label.setForeground(c);
        return label;
    }

    public static JButton createJButtonWithIcon(String text, int x, int y, Color color, String iconURL){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(iconURL));
        Image i2 = i1.getImage().getScaledInstance(17,17,Image.SCALE_DEFAULT);
        JButton button = new JButton(text, new ImageIcon(i2));
        button.setBounds(x,y,110,30);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Helvetica", Font.BOLD, 16));
        return button;
    }

    public static JButton createJButton(String text, int x, int y, Color color){
        JButton button = new JButton(text);
        button.setBounds(x,y,110,30);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Helvetica", Font.BOLD, 16));
        return button;
    }

    public static JButton createJButton(String text, int x, int y){
        return createJButton(text,x,y,DEFAULT_BUTTON_COLOR);
    }

    public static JButton createJButton(String text, int x, int y, int w, int h){
        JButton btn = createJButton(text, x, y);
        btn.setBounds(x,y,w,h);
        return btn;
    }

    public static void styleJTable(JTable table){
        JTableHeader thead = table.getTableHeader();
        thead.setFont(new Font("Arial", Font.BOLD, 16));

        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setRowHeight(20);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0; i<table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
