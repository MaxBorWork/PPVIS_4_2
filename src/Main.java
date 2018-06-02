import Controller.StudentController;
import Model.DataBase;
import View.MyView;

public class Main {

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        StudentController studentController = new StudentController(dataBase);
        MyView myView = new MyView(studentController);
    }
}