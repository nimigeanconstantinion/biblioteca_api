package com.example.biblioteca.model;

import com.example.biblioteca.repository.StudentRepo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Student")
@Table(name = "student")

@ToString

public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "Name required")
    String name;

    @Column(
            name = "cnp",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "CNP Required")
    String cnp;
    @Column(
            name = "address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "address required")
    String address;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    @NotBlank(message = "Email can not be null")
    String email;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "Password cat not be null")
    String password;

    public Student(String name, String cnp, String address,String email,String password) {
        this.name = name;
        this.cnp = cnp;
        this.address = address;
        this.email=email;
        this.password=password;
    }

    @OneToMany(
            mappedBy = "student",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();



    public void addBook(Book newBook){
        books.add(newBook);
        newBook.setStudent(this);
    }


    public void removeBook(Book book){
        books.remove(book);
    }

    public String toString(){
        return "Numele este :"+this.name+", Email: "+this.getEmail();

    }
}
