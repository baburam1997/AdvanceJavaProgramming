import java.awt.*;
import javax.swing.*;

public class SignupView extends JFrame {
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JTextField emailField = new JTextField(20);
    private JTextField phoneField = new JTextField(20);
    private JButton submitButton = new JButton("Sign Up");

    public SignupView() {
        // Set up the frame
        this.setTitle("Signup Page");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Create a panel for the form elements
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        // Add form elements to the panel
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);

        panel.add(submitButton);

        // Add panel to the frame
        this.add(panel, BorderLayout.CENTER);
    }

    // Getters for the fields and button
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }
}

