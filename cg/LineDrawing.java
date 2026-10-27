
import javax.swing.*;
import java.awt.*;

public class LineDrawing extends JPanel {

    private int x1 = 50;
    private int y1 = 70;
    private int x2 = 250;
    private int y2 = 200;

    private String algorithm = "BOTH";

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.WHITE);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("DDA and Bresenham Line Drawing", 100, 30);

        g.setFont(new Font("Arial", Font.BOLD, 14));

        if (algorithm.equals("BOTH") || algorithm.equals("DDA")) {
            g.setColor(Color.BLUE);
            g.drawString("DDA Line", 100, 60);
            drawDDA(g, x1, y1, x2, y2);
        }

        if (algorithm.equals("BOTH") || algorithm.equals("BRESENHAM")) {
            g.setColor(Color.RED);
            g.drawString("Bresenham Line", 350, 60);
            drawBresenham(g, 300, 70, 500, 200);
        }
    }

    // DDA Line Drawing Algorithm
    private void drawDDA(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        if (steps == 0) {
            g.fillRect(x1, y1, 3, 3);
            return;
        }

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            g.fillRect(Math.round(x), Math.round(y), 3, 3);
            x += xIncrement;
            y += yIncrement;
        }
    }

    // Bresenham Line Drawing Algorithm
    private void drawBresenham(
            Graphics g, int x1, int y1, int x2, int y2) {

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;

        int error = dx - dy;

        while (true) {
            g.fillRect(x1, y1, 3, 3);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            int e2 = 2 * error;

            if (e2 > -dy) {
                error -= dy;
                x1 += sx;
            }

            if (e2 < dx) {
                error += dx;
                y1 += sy;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CG Lab - Line Drawing");

            LineDrawing panel = new LineDrawing();

            frame.add(panel);
            frame.setSize(600, 350);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
