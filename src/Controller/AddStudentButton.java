package Controller;

import Model.Address;
import Model.DataBase;
import Model.Student;

public class AddStudentButton {
    DataBase dataBase;
    public AddStudentButton(DataBase dataBase, String firstName, String secondName, String surName, String country, String city, String region, String street, String house, String housing, String flat) {
        Student student = new Student(firstName, secondName, surName);
        dataBase.addStudent(student);
        Address address = new Address(country, city, region, street, house, housing, flat);
        dataBase.addAddress(address);
    }
}
