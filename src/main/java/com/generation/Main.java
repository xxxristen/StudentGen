package com.generation;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
            throws ParseException {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    registerStudent(studentService, scanner);
                    break;
                case 2:
                    findStudent(studentService, scanner);
                    break;
                case 3:
                    gradeStudent(studentService, scanner);
                    break;
                case 4:
                    enrollStudentToCourse(studentService, courseService, scanner);
                    break;
                case 5:
                    showStudentsSummary(studentService, scanner);
                    break;
                case 6:
                    showCoursesSummary(courseService, scanner);
                    break;
                case 7:
                    showCourseAverage(courseService, scanner);
                    break;
                case 8:
                    showEnrolledStudents(courseService, scanner);
                    break;
                case 9:
                    showPassedCourse(studentService, scanner);
                    break;
                case 10:
                    checkCourseApproved(studentService, courseService, scanner);
                    break;
                case 11:
                    checkCoursePassed(studentService, courseService, scanner);
                    break;
            }
        }
        while (option != 12);
    }

    // Option 1
    private static void registerStudent(StudentService studentService, Scanner scanner)
            throws ParseException {
        Student student = PrinterHelper.createStudentMenu(scanner);
        studentService.subscribeStudent(student);
    }

    // Option 2
    private static void findStudent(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student != null) {
            System.out.println("Student Found: ");
            System.out.println(student);
        } else {
            System.out.println("Student with Id = " + studentId + " not found");
        }
    }

    // Option 3
    // Prompt for input on student and course to grade
    private static void gradeStudent(StudentService studentService, Scanner scanner) {
        int grade = 0;
        System.out.println("Insert student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        // If student is found
        if (student != null) {
            System.out.println("Grading: " + student + "\n");
            if (!student.getApprovedCourses().isEmpty()) {
                System.out.println("Course(s) enrolled:");
                for (Course course : student.getApprovedCourses()) {
                    System.out.println(course);
                }
                System.out.println("\nInsert course code to grade: ");
                String courseCode = scanner.next();
                CourseService courseService = new CourseService();
                Course course = courseService.getCourse(courseCode);
                // If student is attending course
                if (student.isAttendingCourse(courseCode)) {
                    boolean success = false;
                    // Do while loop until user input a grade of 1 - 100
                    do {
                        System.out.println("Insert grade (1-100): ");
                        try {
                            grade = Integer.parseInt(scanner.next());
                            if (grade >= 1 && grade <= 100) {
                                success = true;
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("You did not input an integer of 1 - 100.");
                        }
                    } while (!success);
                    studentService.gradeStudent(student, course, grade);
                    // If student is not attending course
                } else System.out.println("Student is not enrolled into the course.");
            }
        }
        // If student is not found
        else {
            System.out.println("Student record is not found.");
        }
    }

    // Option 4
    private static void enrollStudentToCourse(StudentService studentService, CourseService courseService,
                                              Scanner scanner) {
        System.out.println("Insert student ID");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return;
        }
        System.out.println(student);
        System.out.println("Insert course ID");
        String courseId = scanner.next();
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Invalid Course ID");
            return;
        }
        System.out.println(course);
        courseService.enrollStudent(courseId, student);
        studentService.enrollToCourse(studentId, course);
        System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);

    }

    // Option 5
    private static void showStudentsSummary(StudentService studentService, Scanner scanner) {
        studentService.showSummary();
    }

    // Option 6
    private static void showCoursesSummary(CourseService courseService, Scanner scanner) {
        courseService.showSummary();
    }

    // Option 7
    // Challenge yourself - Implement an additional feature in the menu options that will display the average grade of all the students subscribed to a given course.
    private static void showCourseAverage(CourseService courseService, Scanner scanner) {
        System.out.println("Insert course ID");
        String courseID = scanner.next();
        Course course = courseService.getCourse(courseID);
        courseService.showAverage(course);
    }

    // Option 8
    private static void showEnrolledStudents(CourseService courseService, Scanner scanner) {
        System.out.println("Insert course ID");
        String courseID = scanner.next();
        courseService.showEnrolledStudents(courseID);
    }

    // Option 9
    private static void showPassedCourse(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (studentService.isSubscribed(studentId)) {
            studentService.passedCourses(student);
        } else {
            System.out.println("Student record is not found.");
        }
    }

    // Option 10
    public static void checkCourseApproved(StudentService studentService, CourseService courseService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        if (studentService.isSubscribed(studentId)) {
            Student student = studentService.findStudent(studentId);
            System.out.println("Insert course ID");
            String courseID = scanner.next();
            Course course = courseService.getCourse(courseID);
            studentService.isCourseApproved(student, course);
        } else {
            System.out.println("Student record is not found.");
        }
    }

    // Option 11
    public static void checkCoursePassed(StudentService studentService, CourseService courseService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        if (studentService.isSubscribed(studentId)) {
            Student student = studentService.findStudent(studentId);
            System.out.println("Insert course ID");
            String courseID = scanner.next();
            Course course = courseService.getCourse(courseID);
            studentService.passedCourse(student, course);
        } else {
            System.out.println("Student record is not found.");
        }
    }
}
