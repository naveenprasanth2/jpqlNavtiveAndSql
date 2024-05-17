package com.bharath.springdata.jpqlnavtiveandsql.repository;

import com.bharath.springdata.jpqlnavtiveandsql.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
