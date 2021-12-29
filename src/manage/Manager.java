package manage;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    private static final String PATH_NAME = "src/saveFileCSV.csv";
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
        sc.nextLine();
        try {
            checkAge(age);
        } catch (checkAgeException e) {
//            System.err.print("Fail");
            return null;
        }

        boolean check;
        String gender;
        do {
            check = true;
            System.out.print("Gender (Male/Female/Other): ");
            gender = sc.nextLine();
            String regexName = "(Male|Female|Other)$";
            Pattern pattern = Pattern.compile(regexName);
            Matcher matcher = pattern.matcher(gender);
            if (!matcher.find()) {
                System.out.println("không đúng yêu cầu!");
                System.out.println("Nhập lại");
                check = false;
            }
        }
        while (!check);

        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Average Mark: ");
        double averageMark = 0;
        try {
            averageMark = sc.nextDouble();
            checkMark(averageMark);
        } catch (checkMarkException e) {
//            System.err.println("Fail!!!");
            return null;
        }

        sc.nextLine();
        return new Student(name, age, gender, address, averageMark);
//        return new Student();
    }

    public void addStudent() {
        Student student = creatStudent();
        if (student != null) {
            students.add(student);
            System.out.println("Add Success");
        } else {
            System.out.println("Fail!!!");
        }
        writeFileCSV(displayStudent());

    }

    public void deleteStudent(String name) {
        boolean check = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                System.out.println(students.get(i));
                System.out.println("Delete Success");
                check = true;
                students.remove(students.get(i));
            }
        }
//        for (Student student : students) {
//            if (student.getName().equals(name)) {
//                System.out.println(student);
//                System.out.println("Delete Success");
//                check = true;
//                students.remove(student);
//            }
//        }
        if (!check) {
            System.out.println("Name doesn't exist!!!");
        }
//        students.removeIf(student -> student.getName().matches(name));
        writeFileCSV(displayStudent());
    }

    public void editStudent(String name) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                students.set(i, creatStudent());
                System.out.println(students.get(i));
                System.out.println("Edit Success");
            }
        }
        writeFileCSV(displayStudent());
    }

    public ArrayList<Student> displayStudent() {
        return students;
    }

    public ArrayList<Student> displayStudentByMark() {
        ArrayList<Student> highMarkStudent = new ArrayList<>();
        for (Student student : students) {
            if (student.getAverageMark() > 7.5) {
                highMarkStudent.add(student);
            }
        }
        return highMarkStudent;
    }

    public String displayStringF() {
        String display = "";
        String range;
        for (Student student : students) {
            if (student.getAverageMark() > 8) {
                range = "Good";
            } else if (student.getAverageMark() > 6) {
                range = "Fairly";
            } else {
                range = "Avenger Assemble";
            }
            display += "Student: Name: " + student.getName() + ", AverageMark: " + student.getAverageMark() + ", Range: " + range;
            display += "\n";
        }
        return display;
    }

    public void writeFileCSV(ArrayList<Student> students) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_NAME));
            for (Student student : students) {
                bufferedWriter.write(student.getName() + "," + student.getAge() + "," + student.getGender() + "," +
                        student.getAddress() + "," + student.getAverageMark());
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Student> readFileCSV() {
        ArrayList<Student> studentList = new ArrayList<>();
        File file = new File(PATH_NAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_NAME));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] info = line.split(",");
                studentList.add(new Student(info[0], Integer.parseInt(info[1]), info[2], info[3], Double.parseDouble(info[4])));
            }
            ;
        } catch (IOException e) {
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
        if (averageMark < 0 || averageMark > 10) {
            throw new checkMarkException();
        }
    }

    private static class checkAgeException extends Exception {
    }

    private static class checkMarkException extends Exception {

    }
}

