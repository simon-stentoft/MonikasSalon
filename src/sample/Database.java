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

 /*   //Install database
    public void InstallDatabase() {
        Database d =new Database();
        if (d.connection == null) {  //todo er altid not null
            DbsqlUpdate("CREATE DATABASE "+url);
            System.out.println("Database opretet");
        }
    }*/

    public void createTable() {

            String sql = "CREATE TABLE IF NOT EXISTS USER(\n"
                    + " name TEXT NOT NULL,\n"
                    + "	gender TEXT NOT NULL,\n"
                    + "	email TEXT PRIMARY KEY,\n"
                    + "	phoneNumber INTEGER NOT NULL,\n"
                    + "	password TEXT NOT NULL, \n"
                    + "	expiringDate TEXT NOT NULL \n"  //Aftaler må først slettes efter 5 år.
                    + ");";

        DbsqlUpdate(sql);
    }

    public void addUser(ActionEvent event, String name, String gender, String email, int phoneNumber, String password , String expiringDate) {
       // Controller c = new Controller();
        String sql = "INSERT INTO USER(name, gender, email, phoneNumber, password , expiringDate) " +
                "VALUES ('" + name + "','" + gender + "','" + email + "','" + phoneNumber + "','" + password + "','" + expiringDate + "')";

       DbsqlUpdateAndPrint(sql,"User created.");


    }

    public void login(ActionEvent event, String email, String password) { //Skal rettes lidt i efter metoder i Controller er lavet
        Controller c = new Controller();
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
    }
}
