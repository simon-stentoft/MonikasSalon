package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public TextField nameTextField, genderTextField, emailTextField, phoneNrTextField, passwordTextField, emailLoginTextField, passwordLoginTextField;

    public void switchToLogInView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNewUserView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("newuser-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   /* public void switchSceneIfLoginSuccesful(ActionEvent event) throws IOException {
        String loginEmail = emailLoginTextField.getText();
        String loginPassword = passwordLoginTextField.getText();

        db.login(event, loginEmail, loginPassword);
    }*/

}
