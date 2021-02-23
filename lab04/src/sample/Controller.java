package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    public TextField fullName;
    public TextField userName;
    public PasswordField password;
    public TextField email;
    public TextField phoneNum;
    public DatePicker dateOfB;


    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        System.out.println("Username: " + userName.getText());
        System.out.println("Password: " + password.getText());
        System.out.println("Full Name: " + fullName.getText());
        System.out.println("E-Mail: " + email.getText());
        System.out.println("Phone #: " + phoneNum.getText());
        System.out.println("Date of Birth: " + dateOfB.getValue());
    }
}
