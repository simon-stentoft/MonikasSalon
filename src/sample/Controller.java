package sample;

public class Controller {
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


}
