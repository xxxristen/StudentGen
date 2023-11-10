package test;

import com.generation.model.Student;
import com.generation.service.StudentService;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
    @Test
    void findStudent() throws ParseException {
        StudentService studentService = new StudentService();
        DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        Student avril = new Student("1", "Avril", "avril@gmail.com", formatter.parse("11/25/2002"));
        Student leia = new Student("2", "Leia", "leia@gmail.com", formatter.parse("9/8/2000"));
        Student sam = new Student("3", "Sam", "sam@gmail.com", formatter.parse("2/7/2001"));
        studentService.subscribeStudent(avril);
        studentService.subscribeStudent(leia);
        studentService.subscribeStudent(sam);
        assertAll(
                () -> {
                    assertEquals(avril, studentService.findStudent("1"));
                },
                () -> {
                    assertEquals(leia, studentService.findStudent("2"));
                },
                () -> {
                    assertEquals(sam, studentService.findStudent("3"));
                }
        );
    }

    @Test
    void isSubscribed() throws ParseException {
        Map<String, Student> students = new HashMap<>();
        StudentService studentService = new StudentService();
        DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        Student leia = new Student("2", "Leia", "leia@gmail.com", formatter.parse("9/8/2000"));
        studentService.subscribeStudent(leia);
        assertTrue(studentService.isSubscribed(leia.getId()));
    }
}
