package com.example.biblioteca.web;

import com.example.biblioteca.model.Book;
import com.example.biblioteca.model.Student;
import com.example.biblioteca.repository.StudentRepo;
import com.example.biblioteca.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin
public class StudentController {


    StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @ResponseStatus
    @GetMapping("/{id}")
    public Student getStud(@PathVariable Long id){
          return this.studentService.getStudentById(id);
    }

    @ResponseStatus
    @GetMapping
    public List<Student> getAllStud(){
        return this.studentService.getAllStudents();
    }



    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/create")
    public void addStudent(@RequestBody Student student){
        this.studentService.addStudent(student);

    }
    @ResponseStatus
    @PutMapping
    public void updateStudent(@RequestBody Student student){
        this.studentService.updateStudent(student);
    }

    @ResponseStatus
    @PostMapping("/books/{id}")
    public Book addBook(@RequestBody Book book,@PathVariable Long id){
        return null;
    }



}
