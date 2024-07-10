

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class informationInFrame {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Information in Frame");
        frame.setSize(1000, 800);
        JLabel label = new JLabel("Label Name");
        frame.setVisible(true);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
