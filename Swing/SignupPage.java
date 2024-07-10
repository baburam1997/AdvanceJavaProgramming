package Swing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupPage {
    private JFrame frame;
    private JPanel panel;
    private JLabel titleLabel, nameLabel, emailLabel, passwordLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton signupButton;

    public SignupPage() {
        frame = new JFrame("Signup Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        panel = new JPanel();
        panel.setLayout(null);

        titleLabel = new JLabel("Signup Page");
        titleLabel.setBounds(150, 20, 100, 20);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 70, 80, 20);

        nameField = new JTextField();
        nameField.setBounds(140, 70, 150, 20);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 80, 20);

        emailField = new JTextField();
        emailField.setBounds(140, 100, 150, 20);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 130, 80, 20);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 130, 150, 20);

        signupButton = new JButton("Signup");
        signupButton.setBounds(150, 160, 80, 30);
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                JOptionPane.showMessageDialog(frame, "Signup Successful!");
                frame.dispose();
            }
        });

        panel.add(titleLabel);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(signupButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SignupPage();
            }
        });
    }
}
