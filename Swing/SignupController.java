import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class SignupController {
    private final UserModel model;
    private final SignupView view;

    public SignupController(UserModel model, SignupView view) {
        this.model = model;
        this.view = view;

        // Add action listener to the submit button
        this.view.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignup();
            }
        });
    }

    private void handleSignup() {
        // Get data from the view
        String username = view.getUsernameField().getText();
        String password = new String(view.getPasswordField().getPassword());
        String email = view.getEmailField().getText();
        String phone = view.getPhoneField().getText();

        // Update the model
        model.setUsername(username);
        model.setPassword(password);
        model.setEmail(email);
        model.setPhone(phone);

        // Simple validation and message
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Signup Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        UserModel model = new UserModel();
        SignupView view = new SignupView();
        SignupController controller = new SignupController(model, view);
        view.setVisible(true);
    }
}
