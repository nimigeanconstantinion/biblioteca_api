package com.example.biblioteca;

import com.example.biblioteca.model.Book;
import com.example.biblioteca.model.Student;
import com.example.biblioteca.repository.BookRepo;
import com.example.biblioteca.repository.StudentRepo;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BibliotecaApplication {

    public static void main(String[] args) {

        SpringApplication.run(BibliotecaApplication.class, args);

    }

//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepo studentRepo,BookRepo bookRepo) {
//        return args -> {
//           Faker fk=new Faker();
////            for(int i=1;i<=30;i++){
////                String name=fk.name().fullName();
////                String cnp=String.valueOf(fk.number().randomNumber(13,true));
////                String address=fk.address().fullAddress();
////                String email=fk.internet().emailAddress();
////                String password="123";
////                Student st=new Student(name,cnp,address,email,password);
////
////                studentRepo.save(st);
////
////        }
//
//            Student student=studentRepo.findById(26L).get();
//
//
//            System.out.println(student);
//            System.out.println(student.getBooks());
//
////            student.getBooks().stream().forEach(System.out::println);
////            for(int i=0;i<30;i++){
////                String title=fk.book().title();
////                String author=fk.book().author();
////                String genre=fk.book().genre();
////                int year=fk.number().numberBetween(1900,2022);
////                Book book=new Book(title,author,genre,year);
////                student.addBook(book);
////            }
////            studentRepo.save(student);
//
//
//
//    };
//}


}
