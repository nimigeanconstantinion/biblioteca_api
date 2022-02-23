package com.example.biblioteca.services;

import com.example.biblioteca.exceptions.BadRequest;
import com.example.biblioteca.model.Book;
import com.example.biblioteca.model.Student;
import com.example.biblioteca.repository.BookRepo;
import com.example.biblioteca.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final BookRepo bookRepo;

    public StudentService(StudentRepo studentRepo,BookRepo bookRepo) {
        this.studentRepo = studentRepo;
        this.bookRepo=bookRepo;
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

    public Student getStudentByEmail(String email){
        if(studentRepo.findStudentByEmail(email).isPresent()){
            return studentRepo.findStudentByEmail(email).get();
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

//de test NU FUNCTIONEAZA
//    public boolean isBook(Long id,Book book){
//       return studentRepo.findStudentByIdAndBook(id,book).isPresent();
//    }

    public List<Student> studentsWithBook(Book book){
       return studentRepo.findStudentByIdAndBook(book);
    }


    public void addBook(Long id,Book book) {
        Student student = studentRepo.findById(id).get();
        if(bookRepo.findBookByTitleId(book.getTitle(),book.getAuthor(),id).isPresent()){
            throw new BadRequest("Book exists in your biblio");
        }else{
            student.addBook(book);
            studentRepo.save(student);

        }
    }
    public void updBook(Long idS,Long idB,Book updBook) {
        Student student=studentRepo.findById(idS).get();
        if(bookRepo.findById(idB).isPresent()){
            Book book = bookRepo.findById(idB).get();
            book.setAuthor(updBook.getAuthor());
            book.setTitle(updBook.getTitle());
            book.setGenre(updBook.getGenre());
            book.setYear(updBook.getYear());
            book.setStudent(student);

            bookRepo.save(book);
            studentRepo.save(student);
        }else{
            throw new BadRequest("Book not exists in your biblio");

        }
    }

    public void delBook(Long idStudent,Long idBook){
        Student st=this.studentRepo.findById(idStudent).get();
        Book bk=this.bookRepo.findById(idBook).get();
        System.out.println(st);
        System.out.println(bk);
        st.removeBook(bk);
        System.out.println(st.getBooks().size());
        studentRepo.save(st);
        bookRepo.deleteById(idBook);
        System.out.println(bookRepo.findAll().size());
    }

}
