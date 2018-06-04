package Model;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<Student> studentList = new ArrayList<>();
    private List<Address> addressList = new ArrayList<>();

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

    public int getStudentListSize() {
        return studentList.size();
    }

    public int getAddressListSize() {
        return addressList.size();
    }
}
