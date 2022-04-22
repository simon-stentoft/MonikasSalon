package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public TextField nameTextField, genderTextField, emailTextField, phoneNrTextField, passwordTextField, emailLoginTextField, passwordLoginTextField;
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

    public void kallendarRequest(){

    }

    public void kallendarAdd(){

    }

    public void searchAllUsers(){

    }

    public void searchOneUser(){

    }

    //create a user and receive a database call including the user info which is just created
    public static User createNewUser(String name, String gender, String email, int phoneNumber, String username , String password , Boolean isWorker ){
        Database d = new Database();
        d.addUser(name,gender,email,phoneNumber,username,password,isWorker);
        return d.getLastUser();
    }

    //return last added user
    public static User lastUser(){
        Database d = new Database();
        return d.getLastUser();
    }

    //Delete user
    public static void deleteUser(int userID){
        Database d = new Database();
        d.DeleteUser(userID);
    }

   /* public void switchSceneIfLoginSuccesful(ActionEvent event) throws IOException {
        String loginEmail = emailLoginTextField.getText();
        String loginPassword = passwordLoginTextField.getText();

        db.login(event, loginEmail, loginPassword);
    }*/

}
