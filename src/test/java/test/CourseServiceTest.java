package test;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.service.CourseService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    @Test
    void getCourse() {
        CourseService courseService = new CourseService();
        Module moduleWebFundamentals = new Module("INTRO-WEB", "Web Development Fundamentals",
                "Introduction to fundamentals of web development");
        Course course = new Course ("INTRO-WEB-34", "Introduction to Web Applications", 9, moduleWebFundamentals);
        courseService.registerCourse(course);
        assertEquals(course, courseService.getCourse(course.getCode()));
    }
    @Test
    void registerCourse() {
        CourseService courseService = new CourseService();
        Module moduleWebFundamentals = new Module("INTRO-WEB", "Web Development Fundamentals",
                "Introduction to fundamentals of web development");
        Course course = new Course ("INTRO-WEB-55", "Introduction to Web API", 12, moduleWebFundamentals);
        courseService.registerCourse(course);
        assertEquals(course, courseService.getCourse(course.getCode()));
    }


}
