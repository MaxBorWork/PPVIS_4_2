package Controller;

import Model.Address;
import Model.DataBase;
import Model.Student;

public class StudentController {
    private DataBase dataBase;

    public StudentController(DataBase dataBase) {
         this.dataBase = dataBase;
    }

    public void addStudentToDataBase(Student student) {
        dataBase.addStudent(student);
    }

    public void addAddressToDataBase(Address address) {
        dataBase.addAddress(address);
    }

    public Student getStudentFromDataBase(int index) {
        return dataBase.getStudent(index);
    }

    public Address getAddressFromDataBase(int index) {
        return dataBase.getAddress(index);
    }

    public void deleteStudentFromDataBase(int index) {
        dataBase.deleteStudent(index);
    }

    public void deleteAddressFromDataBase(int index) {
        dataBase.deleteAddress(index);
    }

    public int getStudentListSize() {
        return dataBase.getStudentListSize();
    }

    public int getAddressListSize() {
        return dataBase.getAddressListSize();
    }
}
