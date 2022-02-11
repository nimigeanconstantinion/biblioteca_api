package com.example.biblioteca.services;

import com.example.biblioteca.exceptions.BadRequest;
import com.example.biblioteca.model.Book;
import com.example.biblioteca.model.Student;
import com.example.biblioteca.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents(){
        return  this.studentRepo.findAll();
    }

    public Student getStudentById(Long id){
       if(studentRepo.findById(id).isPresent()){
           return studentRepo.findById(id).get();
       }else{
           throw new BadRequest("Student id does not exists!");
       }
    }

    //addStudent
    public void addStudent(Student s){

           if(studentRepo.findStudentByEmail(s.getEmail()).isPresent()==false){
               this.studentRepo.save(s);
           }else{
               throw new BadRequest("Emailul exista in baza de date");
           }
    }

    public void deleteStudent(Long id){
        studentRepo.deleteById(id);
    }


    public void updateStudent(Student newStud){
        if(this.getStudentById(newStud.getId())!=null){
            Student student=this.studentRepo.findById(newStud.getId()).get();
            student.setName(newStud.getName());
            student.setAddress(newStud.getAddress());
            student.setEmail(newStud.getEmail());
            student.setCnp(newStud.getCnp());
            student.setPassword(newStud.getPassword());
            studentRepo.save(student);

        }else{
            throw new BadRequest("Student does not exists!");
        }

    }

    public Book addBook(Long id,Book book){
        Student student=this.studentRepo.findById().get();
        student.getBooks().add(book);
        studentRepo.save(student)
    }
}
