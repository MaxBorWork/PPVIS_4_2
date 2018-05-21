package Model;

public class Student {
    String firstName;
    String secondName;
    String surName;

    public Student(String sFirstName, String sSecondName, String sSurName){
        this.firstName = sFirstName;
        this.secondName = sSecondName;
        this.surName = sSurName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurName() {
        return surName;
    }

    public String getFullName() {
        return (firstName + " " + secondName + " " + surName );
    }
}
