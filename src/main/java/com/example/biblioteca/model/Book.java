package com.example.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="Book")
@Table(name="book")
@ToString
public class Book {
    @Id
    @SequenceGenerator(
            name="book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    Long id;
    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message="Title is required")
    String title;

    @Column(
            name="author",
            nullable = false,
            columnDefinition = "TEXT"

    )
    @NotBlank(message="Author is required")
    String author;

    @NotBlank(message="Genre is required")
    @Column(
            name="genre",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String genre;

    @Column(
            name="year",
            nullable = false,
            columnDefinition = "INTEGER"

    )
    int year;

    public Book(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }



    @ManyToOne
    @JoinColumn(
            name="student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="student_book_fk"
            )
    )
    @JsonBackReference
    private Student student;
    public void  addBook(Book newBook){

        this.addBook(newBook);



    }

}
