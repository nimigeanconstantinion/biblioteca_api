package com.example.biblioteca.model;

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
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "Email can not be null")

    String email;
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "Password can not be blank")
    String password;

    public Student(String name, String cnp, String address) {
        this.name = name;
        this.cnp = cnp;
        this.address = address;
    }

    @JsonManagedReference
    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Book> books = new ArrayList<>();
}
