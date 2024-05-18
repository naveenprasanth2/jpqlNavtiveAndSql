package com.bharath.springdata.jpqlnavtiveandsql.repository;

import com.bharath.springdata.jpqlnavtiveandsql.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("from Student")
    List<Student> findAllStudents();

    @Query("select firstName, lastName from Student")
    List<Object[]> findAllStudentsPartialData();

    @Query("from Student where firstName = :firstName")
    List<Student> findAllStudentsForFirstName(@Param("firstName") String firstName);

    @Query("from Student where score>=:min and score<=:max")
    List<Student> findAllStudentsBetweenGivenValues(@Param("min") int min, @Param("max") int max);

    @Modifying
    @Query("delete from Student where firstName = :firstName")
    void deleteStudentsByGivenFirstName(@Param("firstName") String firstName);
}
