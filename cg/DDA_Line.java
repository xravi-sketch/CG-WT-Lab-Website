import java.awt.*;
import javax.swing.*;

public class DDA_Line extends JPanel {
    int x1, y1, x2, y2;

    DDA_Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xi = steps == 0 ? 0 : (float) dx / steps;
        float yi = steps == 0 ? 0 : (float) dy / steps;

        float x = x1;
        float y = y1;

        g.setColor(Color.BLUE);

        for (int i = 0; i <= steps; i++) {
            g.fillRect(Math.round(x), Math.round(y), 3, 3);
            x += xi;
            y += yi;
        }
    }

    public static void main(String[] args) {
        try {
            int x1 = Integer.parseInt(
                JOptionPane.showInputDialog("Enter X1"));
            int y1 = Integer.parseInt(
                JOptionPane.showInputDialog("Enter Y1"));
            int x2 = Integer.parseInt(
                JOptionPane.showInputDialog("Enter X2"));
            int y2 = Integer.parseInt(
                JOptionPane.showInputDialog("Enter Y2"));

            JFrame f = new JFrame("DDA Line Drawing");
            f.add(new DDA_Line(x1, y1, x2, y2));
            f.setSize(650, 400);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                null, "Enter valid integer coordinates.");
        }
    }
}