package com.generation.model;

import com.generation.service.StudentService;

import java.util.*;

public class Student
        extends Person
        implements Evaluation {
    private double average;

    // Define minimum passing grade
    public static final int min_grade = 50;

    // Create a Hashmap object called courseGrade, with key-value pairing, course code and course grade (1-100)
    public Map<Course, Integer> courseGrade = new HashMap<Course, Integer>();

    private final List<Course> courses = new ArrayList<>();

    private final Map<String, Course> approvedCourses = new HashMap<>();

    public Student(String id, String name, String email, Date birthDate) {
        super(id, name, email, birthDate);
    }

    // Option 4
    public void enrollToCourse(Course course) {
        //TODO implement this method
        //TODO 1. 'put' the course to student's approvedCourses aka HashMap<code, course> via registerApprovedCourse
        //TODO 2. ALSO, 'add' the course in the students' courses aka List<course>
        if (!approvedCourses.containsKey(course.getCode())) {
            registerApprovedCourse(course);
        }
    }

    public void registerApprovedCourse(Course course) {
        approvedCourses.put(course.getCode(), course);
    }

    public boolean isCourseApproved(String courseCode) {
        //TODO implement this method
        //TODO 1. HINT: does the students approvedCourses aka HashMap<code, course> 'contains the key' to courseCode?
        //TODO if so, what do you return? Otherwise, what else do you return?
        return approvedCourses.containsKey(courseCode);
    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve.
    //TODO implement this method - Challenge yourself
    // Too ambiguous so I've split this challenge into 2 methods - findPassedCourses and findPassedCoursesList
    //
    // Option 11
    // Take in 1 course to check if student has obtained and passed with grade of at least 50
    public List<Course> findPassedCourses(Course course) {
        // If student's approvedCourses (courses student enrolled) is not empty
        if (approvedCourses.isEmpty()) {
            System.out.println("The student is not enrolled into any course.");
            courses.clear();
        } else {
            // If student is enrolled into course and course is found with courseGrade, i.e student has been graded for course
            if (isCourseApproved(course.getCode()) && courseGrade.containsKey(course)) {
                // Find course (parameter taken in) in "courseGrade" (hashmap to contain enrolled course + grade)
                if (courseGrade.get(course) >= min_grade) {
                    // Add course to courses, if grade is at least 50 (min_grade)
                    courses.add(course);
                }
//                // Grade is lower than min_grade
//                else {
//                    courses.clear();
//                }
            } else if (isCourseApproved(course.getCode()) && !courseGrade.containsKey(course)) {
                System.out.println("Student is enrolled but has not been graded.");
            } else {
                System.out.println("Student is not enrolled into course");
            }
        }
        return courses;
    }

    // Option 9
    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve.
    // public List<Course> findPassedCourses(Course course) {
    //TODO implement this method - Challenge yourself
    // Return a list of courses that student has obtained and passed with grade of at least 50
    public List<Course> findPassedCoursesList() {
        // If student's approvedCourses (courses student enrolled) is not empty
        if (approvedCourses.isEmpty()) {
            System.out.println("The student has not enrolled into any course.");
        }
        // Student has enrolled into course(s)
        else {
            courses.clear();
            // Iterate through "courseGrade" (hashmap to contain enrolled course + grade)
            for (Course courseE : courseGrade.keySet()) {
                // Compare against min_grade (value of 50), and if >=50, add to courses list.
                if (courseGrade.get(courseE) >= min_grade) {
                    courses.add(courseE);
                }
            }
            if (courses.isEmpty()) {
                System.out.println("Student did not pass any course.");
            }
        }
        return courses;
    }

    public boolean isAttendingCourse(String courseCode) {
        //TODO implement this method
        //TODO 1. HINT: does the students approvedCourses aka HashMap<code, course> 'contains the key' to courseCode?
        //TODO if so, what do you return? Otherwise, what else do you return?
        return approvedCourses.containsKey(courseCode);
    }

    // Grade student (1-100) for the enrolled course
    public void gradeCourse(String courseCode, int grade) {
        // If student is attending course
        if (isAttendingCourse(courseCode)) {
            Course course = approvedCourses.get(courseCode);
            courseGrade.put(course, grade);
            System.out.println(course + " graded with " + grade + "/100.");
        } else {
            System.out.println("Student is not enrolled into course.");
        }
    }

    @Override
    public double getAverage() {
        return average;
    }

    @Override
    public List<Course> getApprovedCourses() {
        //TODO implement this method
        //TODO 1. Hint, where do the list of courses come from?
        if (approvedCourses.isEmpty()) {
            System.out.println("Student is not enrolled into any course.");
            return List.of();
        } else {
            return new ArrayList<Course>(approvedCourses.values());
        }
    }

    @Override
    public String toString() {
        return "Student {" + super.toString() + "}";
    }
}
