package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    Database db = new Database();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public TextField nameTextField, genderTextField, emailTextField, phoneNrTextField, phoneLoginTextField, passwordLoginTextField;
    public PasswordField passwordPasswordField, passwordLoginPasswordField;
    public CheckBox isWorkerCheckBox;

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

    public void switchToCalendarView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("calendar-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Switches scene from Login to Transfer page if the login was succesful.
    public void switchSceneIfLoginSuccesful(ActionEvent event) throws IOException {
        String loginPhoneNumber = phoneLoginTextField.getText();
        String loginPassword = passwordLoginPasswordField.getText();

        db.login(event, loginPhoneNumber, loginPassword);
    }

    public void kallendarRequest(){

    }

    public void kallendarAdd(){

    }

    public void searchAllUsers(){

    }

    public void searchOneUser(){

    }

    //Uses addUser from Database class.
    public void createUser(ActionEvent event) throws IOException {
        db.addUser(event, nameTextField.getText(), genderTextField.getText(), emailTextField.getText(), Integer.parseInt(phoneNrTextField.getText()), passwordPasswordField.getText());
    }

    //return last added user
    public User lastUser(){
        return db.getLastUser();
    }

    //Delete user
    public void deleteUser(int userID){
        db.DeleteUser(userID);
    }

   /* public void switchSceneIfLoginSuccesful(ActionEvent event) throws IOException {
        String loginEmail = emailLoginTextField.getText();
        String loginPassword = passwordLoginTextField.getText();

        db.login(event, loginEmail, loginPassword);
    }*/

}
