package Model;

import java.util.ArrayList;

public class DataBase {
    public ArrayList<Student> studentList = new ArrayList<>();
    public ArrayList<Address> addressList = new ArrayList<>();

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void addAddress(Address address) {
        addressList.add(address);
    }

    public void deleteStudent(int index) {
        studentList.remove(index);
    }

    public void deleteAddress(int index) {
        addressList.remove(index);
    }

    public Student getStudent(int index) {
        return studentList.get(index);
    }

    public Address getAddress(int index) {
        return addressList.get(index);
    }
}
