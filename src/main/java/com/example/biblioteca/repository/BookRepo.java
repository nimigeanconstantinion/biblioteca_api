package com.example.biblioteca.repository;

import com.example.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {

    @Query("select b from Book b where b.title=?1 and b.author=?2 and b.student.id=?3")
    Optional<Book> findBookByTitleId(String title,String author,Long id);
}
