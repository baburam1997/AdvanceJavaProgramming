public class RegisterController {
    private RegisterModel model;
    private RegisterView view;

    public RegisterController(RegisterModel model, RegisterView view) {
        this.model = model;
        this.view = view;
        view.addSubmitListener(e -> handleSubmit());
    }

    private void handleSubmit() {
        try {
            model.setFirstName(view.getFirstName());
            model.setLastName(view.getLastName());
            model.setAddress(view.getAddress());
            model.setPhone(view.getPhone());
            model.setEmail(view.getEmail());
            model.setPassword(view.getPassword());
            model.setCollegeName(view.getCollegeName());
            model.setYear(Integer.parseInt(view.getYear()));
            model.setSemester(Integer.parseInt(view.getSemester()));
            model.setRollNo(Integer.parseInt(view.getRollNo()));
            
            view.showMessage("Registration Successful!");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }
}
