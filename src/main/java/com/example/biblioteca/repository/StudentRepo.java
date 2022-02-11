package com.example.biblioteca.repository;

import com.example.biblioteca.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional(readOnly=false)
public interface StudentRepo extends JpaRepository<Student,Long> {
    @Query("select s from Student s where s.email=?1")
    Optional<Student> findStudentByEmail(String email);
}
