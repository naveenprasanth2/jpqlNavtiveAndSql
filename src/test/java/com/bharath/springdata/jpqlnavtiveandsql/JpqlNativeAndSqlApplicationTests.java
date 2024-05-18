package com.bharath.springdata.jpqlnavtiveandsql;

import com.bharath.springdata.jpqlnavtiveandsql.entities.Student;
import com.bharath.springdata.jpqlnavtiveandsql.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class JpqlNativeAndSqlApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @RepeatedTest(value = 10)
    void testStudentCreate() {
        Student student = Student.builder().firstName("John").lastName("Ferguson").score(88).build();
        Student student1 = Student.builder().firstName("Bill").lastName("Gates").score(75).build();
        studentRepository.saveAll(List.of(student, student1));
    }

    @Test
    void testFindByJpqlQuery() {
        PageRequest pageRequest = PageRequest.of(0, 5)
                .withSort(Sort.by(Sort.Order.asc("lastName"), Sort.Order.desc("firstName")));
        studentRepository.findAllStudents(pageRequest).forEach(System.out::println);
    }

    @Test
    void findByInTheListUsingJpql(){
        Pageable pageable = PageRequest.of(0, 2).withSort(Sort.by(Sort.Order.desc("lastName")));
        studentRepository.findAllStudentsIn(List.of(1, 52, 202), pageable).forEach(System.out::println);
    }


    @Test
    void findByInTheListUsingJpqlWithSort(){
        Pageable pageable = PageRequest.of(0, 5).withSort(Sort.by(Sort.Order.desc("lastName")));
        studentRepository.findAllStudentsIn(List.of(1, 52, 202), pageable).forEach(System.out::println);
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
