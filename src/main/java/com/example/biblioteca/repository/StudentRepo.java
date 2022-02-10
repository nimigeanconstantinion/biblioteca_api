package com.example.biblioteca.repository;

import com.example.biblioteca.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
