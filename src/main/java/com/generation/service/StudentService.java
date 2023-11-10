package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService {

    private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent(Student student) {
        students.put(student.getId(), student);
    }

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

    public void showSummary() {
        //TODO implement
        // Show students summary
        if (!students.isEmpty()) {
            System.out.println("Student records in the system:");
            for (Student studentE : students.values()) {
                Student student = students.get(studentE);
                System.out.println(studentE);

                if (!studentE.getApprovedCourses().isEmpty() && !studentE.courseGrade.isEmpty()) {
                    for (Map.Entry<Course, Integer> entry : studentE.courseGrade.entrySet()) {
                        Course course = entry.getKey();
                        int grade = entry.getValue();
                        System.out.println("Course code: "+course.getCode() + " - grade: " + grade);
                    }
                }
            }
        } else {
            System.out.println("There is 0 student record in the system.");
        }

    }

    public void enrollToCourse(String studentId, Course course) {
        if (students.containsKey(studentId)) {
            students.get(studentId).enrollToCourse(course);
        }
    }

    public void gradeStudent(Student student, Course course, int grade) {
        student.gradeCourse(course.getCode(), grade);
    }


}
