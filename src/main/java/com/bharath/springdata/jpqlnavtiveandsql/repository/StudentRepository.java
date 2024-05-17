package com.bharath.springdata.jpqlnavtiveandsql.repository;

import com.bharath.springdata.jpqlnavtiveandsql.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("from Student")
    List<Student> findAllStudents();

    @Query("select firstName, lastName from Student")
    List<Object[]> findAllStudentsPartialData();
}
