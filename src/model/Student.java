package model;

import java.io.Serializable;

public class Student extends Human implements Serializable {
    private double averageMark;

    public Student() {
    }

    public Student(String name, int age, String gender, String address, double averageMark) {
        super(name, age, gender, address);
        this.averageMark = averageMark;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", gender='" + super.getGender() + '\'' +
                ", address='" + super.getAddress() + '\'' +
                ", averageMark='" + averageMark + '\'' +
                '}';
    }
}
