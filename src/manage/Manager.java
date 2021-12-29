package manage;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    private static String PATH_NAME = "src/saveFileCSV";
    ArrayList<Student> students = readFileCSV();
    Scanner sc = new Scanner(System.in);

    public Manager() {
    }

    public Student creatStudent() {
        System.out.println("Student Information");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        try {
            checkAge(age);
        } catch (checkAgeException e) {
            System.err.print(e.getMessage());
        }
        sc.nextLine();
        System.out.print("Gender (Male/Female/Other): ");

        String gender = sc.nextLine();
        String regexName = "(Male|Female|Other)$";
        Pattern pattern = Pattern.compile(regexName);
        Matcher matcher = pattern.matcher(gender);
        while (!matcher.find()) {
            if (!matcher.find()) {
                System.out.println("không đúng yêu cầu!");
                System.out.println("Nhập lại");
            }
            gender = sc.nextLine();
        }
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Average Mark: ");
        double averageMark = 0;
        try {
            averageMark= sc.nextDouble();
            checkMark(averageMark);
        } catch (checkMarkException e) {
            e.printStackTrace();
        }

        sc.nextLine();
        return new Student(name, age, gender, address, averageMark);
    }

    public void addStudent() {
        students.add(creatStudent());
    }

    public void deleteStudent(String name) {
        boolean check = false;
        for (Student student : students) {
            if (student.getName().matches(name)) {
                System.out.println(student);
                System.out.println("Delete Success");
                check = true;
                students.remove(student);
            }
        }
        if (!check) {
            System.out.println("Name doesn't exist!!!");
        }
//        students.removeIf(student -> student.getName().matches(name));
    }

    public void editStudent(String name) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                students.set(i, creatStudent());
                System.out.println(students.get(i));
                System.out.println("Edit Success");
            }
        }
    }

    public ArrayList<Student> displayStudent() {
        return students;
    }

    public ArrayList<Student> displayStudentByMark() {
        ArrayList<Student> highMarkStudent = null;
        for (Student student : students) {
            if (student.getAverageMark() > 7.5) {
                highMarkStudent.add(student);
            }
        }
        return highMarkStudent;
    }

    public String displayStringF() {
        String display = null;
        String range;
        for (Student student : students) {
            if (student.getAverageMark() > 8) {
                range = "Good";
            } else if (student.getAverageMark() > 6) {
                range = "Fairly";
            } else {
                range = "Avenger Assemble";
            }
            display += "Student: Name: " + student.getName() + " - AverageMark: " + student.getAverageMark() + ", Range: " + range;
            display += "\n";
        }
        return display;
    }

    public void writeFileCSV(ArrayList<Student> students) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH_NAME));
            objectOutputStream.writeObject(students);
            objectOutputStream.close();
            System.out.println("Write Success");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Write Fail!");
        }
    }

    public ArrayList<Student> readFileCSV() {
        ArrayList<Student> studentList = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH_NAME));
            studentList = (ArrayList<Student>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            System.err.println(e.getMessage());
        }
        return studentList;
    }


    public void checkAge(int age) throws checkAgeException {
        if (age < 18 || age > 60) {
            throw new checkAgeException();
        }
    }

    public void checkMark(double averageMark) throws checkMarkException {
        if (averageMark < 0 || averageMark > 10){
            throw new checkMarkException();
        }
    }

    private class checkAgeException extends Exception {
    }

    private class checkMarkException extends Exception {

    }
}

