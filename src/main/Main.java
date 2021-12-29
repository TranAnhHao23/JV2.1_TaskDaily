package main;

import manage.Manager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);


            Manager manager = new Manager();
            int choice;
            do {
                System.out.println("----------------------Menu------------------------");
                System.out.println("1.Add new Student");
                System.out.println("2.Delete Student via Name");
                System.out.println("3.Edit Student via Name");
                System.out.println("4.Display Student list");
                System.out.println("5.Display Student have 7.5 average mark or higher");
                System.out.println("6.Display Student by syntax");
                System.out.println("7. Write to CSV File");
                System.out.println("8. Read from CSV File");
                System.out.println("0. Exit");
                System.out.println("--------------------------------------------------");
                System.out.print("Your choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        manager.addStudent();
                        break;
                    case 2:
                        System.out.print("Input Student Name: ");
                        String nameDelete = sc.nextLine();
                        manager.deleteStudent(nameDelete);
                        break;
                    case 3:
                        System.out.println("Input Student Name: ");
                        String nameEdit = sc.nextLine();
                        manager.editStudent(nameEdit);
                        break;
                    case 4:
                        System.out.println("Student List: ");
                        manager.displayStudent().forEach((student) -> System.out.println(student));
                        break;
                    case 5:
                        System.out.println("Student have 7.5 average mark or higher: ");
                        if (manager.displayStudentByMark() != null) {
                            manager.displayStudentByMark().forEach((student) -> System.out.println(student));
                        } else {
                            System.out.println("Nobody!");
                        }
                        break;
                    case 6:
                        System.out.println("Display Student by Syntax: ");
                        System.out.println("Name - Average Mark - Range");
                        System.out.println(manager.displayStringF());
                        break;
                    case 7:
                        manager.writeFileCSV(manager.displayStudent());
                        break;
                    case 8:
                        manager.readFileCSV();
                        System.out.println("Print Test");
                        manager.readFileCSV().forEach((student) -> System.out.println(student));
                    case 0:
                        System.out.println("See ya <3");
                        break;
                    default:
                        System.out.println("Wrong choice!!!");
                        break;
                }
            } while (choice != 0);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
