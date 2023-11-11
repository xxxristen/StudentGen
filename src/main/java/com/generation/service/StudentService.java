package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    private final Map<String, Student> students = new HashMap<>();

    // Option 1 - Register Student
    public void subscribeStudent(Student student) {
        students.put(student.getId(), student);
    }

    // Option 2 - Find Student
    public Student findStudent(String studentId) {
        if (students.containsKey(studentId)) {
            return students.get(studentId);
        }
        return null;
    }

    public boolean isSubscribed(String studentId) {
        //TODO implement this method
        return (students.containsKey(studentId));
    }

    // Option 3 - Grade Student
    public void gradeStudent(Student student, Course course, int grade) {
        student.gradeCourse(course.getCode(), grade);
    }

    // Option 4 - Enroll Student to Course
    public void enrollToCourse(String studentId, Course course) {
        if (students.containsKey(studentId)) {
            students.get(studentId).enrollToCourse(course);
        }
    }

    // Option 5 - Show Students Summary
    public void showSummary() {
        //TODO implement
        // Show students summary
        if (!students.isEmpty()) {
            System.out.println("Student records in the system: ");
            System.out.println("----------------------------------------------");
            // Iterating through the students hashmap
            for (Student studentE : students.values()) {
                Student student = students.get(studentE);
                System.out.println("Student ID: " + studentE.getId());
                System.out.println("Student name " + studentE.getName());
                System.out.println("Student's DOB: " + studentE.getBirthDate() + "\n");
                // If student is enrolled in course(s) - print course details
                if (!studentE.getApprovedCourses().isEmpty()) {
                    System.out.println("Courses enrolled: ");
                    // Iterate through student's list of approved courses
                    for (Course i : studentE.getApprovedCourses()) {
                        // If the course has been graded, print course code + name + grade
                        if (!studentE.courseGrade.isEmpty() && studentE.courseGrade.containsKey(i)) {
                            System.out.println("Course: " + i.getCode() + " - " + i.getName() + " (Grade: " + studentE.courseGrade.get(i) + ")");
                        }
                        // If the course has not been graded, just print course code + name
                        else {
                            System.out.println("Course: " + i.getCode() + " - " + i.getName());
                        }
                    }
                }
                System.out.println("------------------------------------------------------");
            }
        } else {
            System.out.println("There is 0 student record in the system.\n");
        }

    }

    // Option 9 - Show Passed Courses
    public void passedCourses(Student student) {
        List<Course> courses = student.findPassedCoursesList();
        if (!courses.isEmpty()) {
            System.out.println(student.getName() + " (Student ID: " + student.getId() + ") has passed: ");
            for (Course course : courses) {
                System.out.println("- " + course.getCode() + " - " + course.getName());
            }
        }
    }

    // Option 10 - Check Student Course Enrolment
    public void isCourseApproved(Student student, Course course) {
        if (student.isCourseApproved(course.getCode())) {
            System.out.println(student.getName() + "(Student ID: " + student.getId() + ") application to \"" + course.getCode() + " - " + course.getName() + "\" has been approved.\n");
        } else {
            System.out.println("Course is not within the student's approved courses.\n");
        }
    }

    // Option 11 - Check if Student Passed Course
    public void passedCourse(Student student, Course course) {
        List<Course> courses = student.findPassedCourses(course);
        if (courses.contains(course)) {
            System.out.println(student.getName() + " (Student ID: " + student.getId() + ") has passed " + course.getCode() + ".\n");
        } else {
            System.out.println(student.getName() + " (Student ID: " + student.getId() + ") did not pass " + course.getCode() + ".\n");
        }
    }
}
