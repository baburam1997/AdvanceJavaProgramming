import java.awt.EventQueue;

public class RegisterApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterModel model = new RegisterModel();
                    RegisterView view = new RegisterView();
                    new RegisterController(model, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
