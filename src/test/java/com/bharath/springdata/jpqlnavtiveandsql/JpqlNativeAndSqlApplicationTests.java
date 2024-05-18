package com.bharath.springdata.jpqlnavtiveandsql;

import com.bharath.springdata.jpqlnavtiveandsql.entities.Student;
import com.bharath.springdata.jpqlnavtiveandsql.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class JpqlNativeAndSqlApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testStudentCreate() {
        Student student = Student.builder().firstName("John").lastName("Ferguson").score(88).build();
        Student student1 = Student.builder().firstName("Bill").lastName("Gates").score(75).build();
        studentRepository.saveAll(List.of(student, student1));
    }

    @Test
    void testFindByJpqlQuery() {
        studentRepository.findAllStudents().forEach(System.out::println);
    }


    @Test
    void testFindAllStudentsPartial() {
        studentRepository.findAllStudentsPartialData().forEach(x -> System.out.println(Arrays.toString(x)));
    }

    @Test
    void testFindAllStudentsByFirstName() {
        studentRepository.findAllStudentsForFirstName("john").forEach(System.out::println);
    }

    @Test
    void testFindAllStudentsBetweenFromAndToValues() {
        studentRepository.findAllStudentsBetweenGivenValues(75, 90).forEach(System.out::println);
    }

    @Test
    @Transactional
    @Rollback(value = false)
        // this is only be used in junit as spring automatically rolls back transaction
    void deleteAllStudentsForTheGivenFirstName() {
        studentRepository.deleteStudentsByGivenFirstName("Bill");
    }

}
