package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Database db = new Database();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public TextField nameTextField, genderTextField, emailTextField, phoneNrTextField, phoneLoginTextField, passwordLoginTextField, deleteUserTextField;
    public PasswordField passwordPasswordField, passwordLoginPasswordField;

    @FXML
    public ChoiceBox<String> barberChoiceBox = new ChoiceBox<>();
    public ChoiceBox<String> timeChoiceBox = new ChoiceBox<>();

    //List of different Barbers to choose from.
    public String[] barbers = {"Monika","Anton"};
    public String[] time = {"08:00", "08:30", "09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barberChoiceBox.getItems().addAll(barbers);
        timeChoiceBox.getItems().addAll(time);
    }

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

    //Switches scene to startpage-view.fxml .Logging out
    public void switchSceneToStartPageLogout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startpage-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    //Deletes a user using their email from the database.
    public void deleteUserWithEmail(ActionEvent event) throws IOException {
        db.deleteUserEmail(event, deleteUserTextField.getText());
    }

    //return last added user
    public User lastUser(){
        return db.getLastUser();
    }



    /*//Delete user
    public void deleteUser(int userID){
        db.DeleteUser(userID);
    }*/

   /* public void switchSceneIfLoginSuccesful(ActionEvent event) throws IOException {
        String loginEmail = emailLoginTextField.getText();
        String loginPassword = passwordLoginTextField.getText();

        db.login(event, loginEmail, loginPassword);
    }*/

}
