import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;


public class WorkingWith2D extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.fill(new Ellipse2D.Double(10, 10, 1, 1));

        Line2D line = new Line2D.Double(20, 20, 100, 100);
        g2.draw(line);

        Path2D triangle = new Path2D.Double();
        triangle.moveTo(150, 150);
        triangle.lineTo(200, 50);
        triangle.lineTo(250, 150);
        triangle.closePath();
        g2.draw(triangle);

        Rectangle2D floatRect = new Rectangle2D.Float(300, 50, 100, 50);
        g2.draw(floatRect);

        Rectangle2D floatSquare = new Rectangle2D.Float(450, 50, 50, 50);
        g2.draw(floatSquare);

        Ellipse2D floatOval = new Ellipse2D.Float(50, 200, 100, 50);
        g2.draw(floatOval);

        Ellipse2D doubleEllipse = new Ellipse2D.Double(200, 200, 100, 50);
        g2.draw(doubleEllipse);

        RoundRectangle2D floatRoundRect = new RoundRectangle2D.Float(350, 200, 100, 50, 20, 20);
        g2.draw(floatRoundRect);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Shapes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new WorkingWith2D());
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}

