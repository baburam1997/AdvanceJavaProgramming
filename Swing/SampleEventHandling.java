package Swing;

import java.awt.event.*;
import javax.swing.*;

public class SampleEventHandling {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Handling Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JButton button = new JButton("Click Me");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Button was clicked!");
            }
        });

        frame.getContentPane().add(button);
        
        frame.setVisible(true);
    }
}

