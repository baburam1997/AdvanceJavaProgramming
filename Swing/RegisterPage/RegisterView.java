import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RegisterView {
    private JFrame frame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField collegeNameField;
    private JTextField yearField;
    private JTextField semesterField;
    private JTextField rollNoField;
    private JButton btnSubmit;

    public RegisterView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Register Page");
        frame.setBounds(100, 100, 450, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(11, 2, 10, 10));

        JLabel lblFirstName = new JLabel("First Name:");
        firstNameField = new JTextField();
        frame.getContentPane().add(lblFirstName);
        frame.getContentPane().add(firstNameField);

        JLabel lblLastName = new JLabel("Last Name:");
        lastNameField = new JTextField();
        frame.getContentPane().add(lblLastName);
        frame.getContentPane().add(lastNameField);

        JLabel lblAddress = new JLabel("Address:");
        addressField = new JTextField();
        frame.getContentPane().add(lblAddress);
        frame.getContentPane().add(addressField);

        JLabel lblPhone = new JLabel("Phone:");
        phoneField = new JTextField();
        frame.getContentPane().add(lblPhone);
        frame.getContentPane().add(phoneField);

        JLabel lblEmail = new JLabel("Email:");
        emailField = new JTextField();
        frame.getContentPane().add(lblEmail);
        frame.getContentPane().add(emailField);

        JLabel lblPassword = new JLabel("Password:");
        passwordField = new JPasswordField();
        frame.getContentPane().add(lblPassword);
        frame.getContentPane().add(passwordField);

        JLabel lblCollegeName = new JLabel("College Name:");
        collegeNameField = new JTextField();
        frame.getContentPane().add(lblCollegeName);
        frame.getContentPane().add(collegeNameField);

        JLabel lblYear = new JLabel("Year:");
        yearField = new JTextField();
        frame.getContentPane().add(lblYear);
        frame.getContentPane().add(yearField);

        JLabel lblSemester = new JLabel("Semester:");
        semesterField = new JTextField();
        frame.getContentPane().add(lblSemester);
        frame.getContentPane().add(semesterField);

        JLabel lblRollNo = new JLabel("Roll No:");
        rollNoField = new JTextField();
        frame.getContentPane().add(lblRollNo);
        frame.getContentPane().add(rollNoField);

        btnSubmit = new JButton("Submit");
        frame.getContentPane().add(new JLabel());
        frame.getContentPane().add(btnSubmit);

        frame.setVisible(true);
    }

    public String getFirstName() { return firstNameField.getText(); }
    public String getLastName() { return lastNameField.getText(); }
    public String getAddress() { return addressField.getText(); }
    public String getPhone() { return phoneField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }
    public String getCollegeName() { return collegeNameField.getText(); }
    public String getYear() { return yearField.getText(); }
    public String getSemester() { return semesterField.getText(); }
    public String getRollNo() { return rollNoField.getText(); }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public void addSubmitListener(ActionListener actionListener) {
        btnSubmit.addActionListener(actionListener);
    }
}
