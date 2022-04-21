package sample;

public class test_slettes {
    public static void main(String[] args) {

        Database db = new Database();
/*
        db.InstallDatabase();
        System.out.println("database er måske oprettet");
        db.createUserTable();
        System.out.println("tabel user måske oprettet");
        db.createAppointmentTable();
        System.out.println("tabel appointments måske oprettet");
        db.createWorkTIME();
        System.out.println("worktmime");
        db.createPreDefinedAppointments();
        System.out.println("preapp");
*/
        db.addUser("lada" , "ja" , "minemailblaasky..dk" , 1515151588 , "userjensen" , "123456" , true);
    }
}
