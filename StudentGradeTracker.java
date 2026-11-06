
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTracker {

    static ArrayList<Student> students = new ArrayList<>();
    static BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int choice;

        do {
            System.out.println("\n===== STUDENT GRADE TRACKER =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Calculate Summary");
            System.out.println("4. Search Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(br.readLine());

                switch (choice) {
                    case 1:
                        addStudent();
                        break;

                    case 2:
                        displayStudents();
                        break;

                    case 3:
                        calculateSummary();
                        break;

                    case 4:
                        searchStudent();
                        break;

                    case 5:
                        System.out.println("Thank you for using Student Grade Tracker!");
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid menu number.");
                choice = 0;
            }

        } while (choice != 5);
    }

    // Add student details
    static void addStudent() throws IOException {

        System.out.print("Enter student name: ");
        String name = br.readLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter student grade (0-100): ");

        try {
            double grade = Double.parseDouble(br.readLine());

            if (grade < 0 || grade > 100) {
                System.out.println("Grade must be between 0 and 100.");
                return;
            }

            students.add(new Student(name, grade));

            System.out.println("Student added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid grade.");
        }
    }

    // Display all students
    static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\n===== STUDENT DETAILS =====");

        System.out.printf("%-20s %-10s%n", "Student Name", "Grade");

        for (Student s : students) {
            System.out.printf("%-20s %-10.2f%n",
                              s.name, s.grade);
        }
    }

    // Calculate average, highest and lowest
    static void calculateSummary() {

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        double total = 0;
        double highest = students.get(0).grade;
        double lowest = students.get(0).grade;

        String highestStudent = students.get(0).name;
        String lowestStudent = students.get(0).name;

        for (Student s : students) {

            total += s.grade;

            if (s.grade > highest) {
                highest = s.grade;
                highestStudent = s.name;
            }

            if (s.grade < lowest) {
                lowest = s.grade;
                lowestStudent = s.name;
            }
        }

        double average = total / students.size();

        System.out.println("\n===== GRADE SUMMARY =====");
        System.out.println("Total Students: " + students.size());
        System.out.printf("Average Grade: %.2f%n", average);
        System.out.println("Highest Grade: " + highest
                           + " (" + highestStudent + ")");
        System.out.println("Lowest Grade: " + lowest
                           + " (" + lowestStudent + ")");
    }

    // Search student by name
    static void searchStudent() throws IOException {

        System.out.print("Enter student name to search: ");
        String searchName = br.readLine().trim();

        boolean found = false;

        for (Student s : students) {

            if (s.name.equalsIgnoreCase(searchName)) {
                System.out.println("Student Found!");
                System.out.println("Name: " + s.name);
                System.out.println("Grade: " + s.grade);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
}
