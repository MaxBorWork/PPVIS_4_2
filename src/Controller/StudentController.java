package Controller;

import Model.Address;
import Model.DataBase;
import Model.Student;

public class StudentController {
    DataBase dataBase;

    public StudentController(DataBase dataBase) {
         this.dataBase = dataBase;
    }

    public void addStudentToDataBase(Student student) {
        dataBase.studentList.add(student);
    }

    public void addAddressToDataBase(Address address) {
        dataBase.addressList.add(address);
    }

    public Student getStudentFromDataBase(int index) {
        return dataBase.studentList.get(index);
    }

    public Address getAddressFromDataBase(int index) {
        return dataBase.addressList.get(index);
    }

    public void deleteStudentFromDataBase(int index) {
        dataBase.studentList.remove(index);
    }

    public void deleteAddressFromDataBase(int index) {
        dataBase.addressList.remove(index);
    }

    public int getStudentListSize() {
        return dataBase.studentList.size();
    }

    public int getAddressListSize() {
        return dataBase.addressList.size();
    }
}
