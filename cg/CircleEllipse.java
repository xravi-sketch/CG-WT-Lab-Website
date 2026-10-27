
import javax.swing.*;
import java.awt.*;

public class CircleEllipse extends JPanel {

    public CircleEllipse() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 18));

        // Draw Midpoint Circle
        g.setColor(Color.BLUE);
        g.drawString("Midpoint Circle Algorithm", 35, 35);
        drawCircle(g, 150, 170, 90);

        // Draw Midpoint Ellipse
        g.setColor(Color.RED);
        g.drawString("Midpoint Ellipse Algorithm", 330, 35);
        drawEllipse(g, 430, 170, 130, 75);
    }

    // Midpoint Circle Drawing Algorithm
    private void drawCircle(Graphics g, int xc, int yc, int r) {
        int x = 0;
        int y = r;
        int p = 1 - r;

        while (x <= y) {
            plotCirclePoints(g, xc, yc, x, y);

            x++;

            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
        }
    }

    // Plot eight symmetric points of a circle
    private void plotCirclePoints(
            Graphics g, int xc, int yc, int x, int y) {

        g.fillRect(xc + x, yc + y, 3, 3);
        g.fillRect(xc - x, yc + y, 3, 3);
        g.fillRect(xc + x, yc - y, 3, 3);
        g.fillRect(xc - x, yc - y, 3, 3);

        g.fillRect(xc + y, yc + x, 3, 3);
        g.fillRect(xc - y, yc + x, 3, 3);
        g.fillRect(xc + y, yc - x, 3, 3);
        g.fillRect(xc - y, yc - x, 3, 3);
    }

    // Midpoint Ellipse Drawing Algorithm
    private void drawEllipse(
            Graphics g, int xc, int yc, int rx, int ry) {

        long rx2 = (long) rx * rx;
        long ry2 = (long) ry * ry;

        long x = 0;
        long y = ry;

        long dx = 0;
        long dy = 2 * rx2 * y;

        double p1 = ry2 - rx2 * ry + 0.25 * rx2;

        // Region 1
        while (dx < dy) {
            plotEllipsePoints(g, xc, yc, (int) x, (int) y);

            x++;
            dx += 2 * ry2;

            if (p1 < 0) {
                p1 += dx + ry2;
            } else {
                y--;
                dy -= 2 * rx2;
                p1 += dx - dy + ry2;
            }
        }

        // Initial decision parameter for Region 2
        double p2 = ry2 * (x + 0.5) * (x + 0.5)
                  + rx2 * (y - 1.0) * (y - 1.0)
                  - rx2 * ry2;

        // Region 2
        while (y >= 0) {
            plotEllipsePoints(g, xc, yc, (int) x, (int) y);

            y--;
            dy -= 2 * rx2;

            if (p2 > 0) {
                p2 += rx2 - dy;
            } else {
                x++;
                dx += 2 * ry2;
                p2 += dx - dy + rx2;
            }
        }
    }

    // Plot four symmetric points of an ellipse
    private void plotEllipsePoints(
            Graphics g, int xc, int yc, int x, int y) {

        g.fillRect(xc + x, yc + y, 3, 3);
        g.fillRect(xc - x, yc + y, 3, 3);
        g.fillRect(xc + x, yc - y, 3, 3);
        g.fillRect(xc - x, yc - y, 3, 3);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(
                    "Midpoint Circle and Ellipse Algorithms");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(650, 350);
            frame.setLocationRelativeTo(null);
            frame.add(new CircleEllipse());
            frame.setVisible(true);
        });
    }
}
