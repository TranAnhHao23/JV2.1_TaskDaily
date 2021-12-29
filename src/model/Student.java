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

    public String display() {
        String range;
        if (this.averageMark > 8){
            range = "Good";
        } else if (this.averageMark >6){
            range = "Fairly";
        } else {
            range = "Avegare";
        }
        return "Student: Name: "+ this.getName() + " - AverageMark: " + this.averageMark + ", Range: "+ range;
    }
}
