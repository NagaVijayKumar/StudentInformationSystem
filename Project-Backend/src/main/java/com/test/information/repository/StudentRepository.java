package com.test.information.repository;

import com.test.information.model.Classroom;
import com.test.information.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByRollNumber(String rollNumber);

    Optional<Student> deleteByRollNumber(String rollNumber);

    List<Student> findByClassRoom(Classroom classroom);
}
