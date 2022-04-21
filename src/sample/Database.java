package sample;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.*;

public class Database {

    private Connection connection; //make final
    private Statement stmt;
    private final String databaseName = "Monicas.db";
    private final String url = "jdbc:sqlite:"+databaseName;

    Database() {
        connection = null; //move to final and add throws clause to database constructor
        stmt = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //prepared SQL-exequteUpdate
    public void DbsqlUpdate(String SQLUpdate){
        try{
            Statement sta = connection.createStatement();
            sta.executeUpdate(SQLUpdate);
            sta.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //prepared SQL-exequteUpdate with print
    public void DbsqlUpdateAndPrint(String SQLUpdate,String print){
        try{
            Statement sta = connection.createStatement();
            sta.executeUpdate(SQLUpdate);
            sta.close();
            System.out.println(print);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //prepared SQL-query todo skal måske slettes
    public void DbsqlQuery(String sql){
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement prep = connection.prepareStatement(sql);
            try (ResultSet res = prep.executeQuery()){
                while (res.next()){
                    System.out.println(res.getString(1));
                }
            }
        }catch (SQLException e){
            e.getErrorCode();
        }
    }


    //  Adding statements--------------------------------------------------


    //create new user

    public void addUser(String name, String gender, String email, int phoneNumber, String username , String password , Boolean worker ) {
        String sql = "INSERT INTO USER( name, gender , email, phoneNumber, username, password, worker, createdDate )\n" +
                "VALUES ('"+name+" ', '"+gender+"' , '"+email+"' ,'"+phoneNumber+"' , '"+username+"' , "+password+" , "+worker+" ,date('now'))";
        DbsqlUpdate(sql);
    }

    //  Queries -------------------------------------------------------------

    // get last created user
    public User getLastUser() {
        User user = new User();
        String sql = "select * FROM USER ORDER BY userID DESC LIMIT 1";
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement prep = connection.prepareStatement(sql);
            try (ResultSet res = prep.executeQuery()){
                while (res.next()){
                    user.setUserID(res.getInt(1));
                    user.setName(res.getString(2));
                    user.setGender(res.getString(3));
                    user.setEmail(res.getString(4));
                    user.setPhoneNumber(res.getInt(5));
                    user.setUsername(res.getString(6));
                    user.setPassword(res.getString(7));
                    user.setWorker(res.getBoolean(8));
                    user.setCreatedDate(res.getString(9));
                }
            }
        }catch (SQLException e){
            e.getErrorCode();
        }
        return user;
    }
    // get one user
    public User getUser(User user, int userID ){
        String sql ="select * FROM USER WHERE userID = '"+userID+"'";
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement prep = connection.prepareStatement(sql);
            try (ResultSet res = prep.executeQuery()){
                while (res.next()){
                    user.setUserID(res.getInt(1));
                    user.setName(res.getString(2));
                    user.setGender(res.getString(3));
                    user.setEmail(res.getString(4));
                    user.setPhoneNumber(res.getInt(5));
                    user.setUsername(res.getString(6));
                    user.setPassword(res.getString(7));
                    user.setWorker(res.getBoolean(8));
                    user.setCreatedDate(res.getString(9));
                }
            }
        }catch (SQLException e){
            e.getErrorCode();
        }
        return user;
    }

// Delete statements -----------------------------------------------------------------

    //Delete user
    public void DeleteUser( int userID ){
        String sql ="DELETE FROM USER WHERE userID = '"+userID+"'";
        DbsqlUpdate(sql);
    }



//  Install database and create tables -----------------------------------------------

    //Install database
    public void InstallDatabase() {
        Database d =new Database();
        if (d.connection == null) {  // er altid not null
            DbsqlUpdate("CREATE DATABASE "+url);
            System.out.println("Database opretet");
        }
    }
    //USER TABLE
    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USER(\n"
                + " userID INTEGER PRIMARY KEY, \n"
                + " name TEXT NOT NULL,\n"
                + "	gender TEXT NOT NULL,\n"
                + "	email TEXT ,\n"
                + "	phoneNumber INTEGER NOT NULL,\n"
                + "	username TEXT NOT NULL,\n"
                + "	password TEXT NOT NULL, \n"
                + " worker INTEGER, \n "
                + "	createdDate TEXT NOT NULL \n"  //Aftaler må først slettes efter 5 år.
                + ");";
        DbsqlUpdate(sql);
    }
    //WORKER TABLE for specified users
    public void createWorkTIME() {
        String sql = "CREATE TABLE IF NOT EXISTS WORKTIME(\n"
                + " worktimeID INTEGER PRIMARY KEY, \n"
                + " userFK INTEGER , \n"
                + " weekday   INTEGER , \n"
                + " startTime     TEXT , \n"
                + " endTime     TEXT , \n"
                + " FOREIGN KEY (userFK) REFERENCES User (userID) \n"
                + " );";
        DbsqlUpdate(sql);
    }
    //APPOINTMENT TABLE
    public void createAppointmentTable() {
        String sql = "CREATE TABLE IF NOT EXISTS APPOINTMENTS(\n"
                + " appointmentID INTEGER PRIMARY KEY, \n"
                + "	worker INTEGER,\n"
                + "	costumer INTEGER,\n"
                + " description TEXT ,\n"
                + "	startDateTime TEXT NOT NULL,\n"
                + "	duration INTEGER NOT NULL,\n"
                + "	price REAL,\n"
                + " note TEXT ,\n"
                + "	created date now NOT NULL, \n"  //Aftaler må først slettes efter 5 år.
                + " FOREIGN KEY (worker) REFERENCES User (userID) \n"
                + " FOREIGN KEY (costumer) REFERENCES User (userID) \n"
                + ");";
        DbsqlUpdate(sql);
    }
    //PREDEFINEDAPPOINTMENTS TABLE
    public void createPreDefinedAppointments() {
        String sql = "CREATE TABLE IF NOT EXISTS PREDEFINEDAPPOINTMENTS(\n"
                + " preappointmentID INTEGER PRIMARY KEY, \n"
                + " description TEXT ,\n"
                + "	duration INTEGER NOT NULL,\n"
                + "	price REAL,\n"
                + "	created TEXT \n"
                + ");";
        DbsqlUpdate(sql);
    }





  /*  public void login(ActionEvent event, String email, String password) { //Skal rettes lidt i efter metoder i Controller er lavet
       // Controller c = new Controller();
        String sql = "SELECT * FROM USER WHERE phoneNumber = ? AND password = ?";
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement prestmt = connection.prepareStatement(sql);
            prestmt.setString(1, email);
            prestmt.setString(2, password);
            ResultSet rs = prestmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful.");
                //c.switchToTransferPage(event);
            } else {
                System.out.println("Wrong login information.");
                //c.switchToLogInView(event);
            }
            connection.close();
            prestmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
