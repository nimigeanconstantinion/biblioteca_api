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
    public Student getStudId(@PathVariable Long id){
          return this.studentService.getStudentById(id);
    }

    @ResponseStatus
    @GetMapping("/email/{email}")
    public Student getStudEmail(@PathVariable String email) {
        return this.studentService.getStudentByEmail(email);
    }


    @ResponseStatus
    @GetMapping
    public List<Student> getAllStud(){
        return this.studentService.getAllStudents();
    }

////test
//@ResponseStatus
//@GetMapping("/bks")
//public Student bookExists(@RequestBody Book book){
//
//    return this.studentRepo.findStudentByIdAndBook(book).get();
//}

@ResponseStatus
@GetMapping("/bks")
public List<Student> bookExists(@RequestBody Book book){

    return this.studentService.studentsWithBook(book);
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
    @PostMapping("/books/add/{id}")
    public void addBook(@RequestBody Book book,@PathVariable Long id){
        this.studentService.addBook(id,book);
    }

    @ResponseStatus
    @PutMapping("/books/upd/{idS}/{idB}")
    public void updBook(@RequestBody Book book,@PathVariable Long idS,@PathVariable Long idB){
        this.studentService.updBook(idS,idB,book);
    }

    @ResponseStatus
    @DeleteMapping("/books/del/{idS}/{idB}")
    public void delBook(@PathVariable Long idS,@PathVariable Long idB){
        this.studentService.delBook(idS,idB);
    }

}
