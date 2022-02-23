package com.example.biblioteca.repository;

import com.example.biblioteca.model.Book;
import com.example.biblioteca.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly=false)
public interface StudentRepo extends JpaRepository<Student,Long> {

    @Query("select s from Student s where s.email=:email")
    Optional<Student> findStudentByEmail(@Param("email") String eml);

//    @Query("select s from Student s where s.id=:id and :title in(select b.title from Book b where b.student_id=s.id)")
//    Optional<Student> findStudentByIdAndBook(@Param("id") Long id,@Param("book") Book book);

    @Query("select s from Student s where s in(select b.student from Book b where b.title=?1)")
    List<Student> findStudentByIdAndBook(Book book);

    @Query("delete from Book b where b.id=?1")
    void deleteBookByID(Long id);


}
