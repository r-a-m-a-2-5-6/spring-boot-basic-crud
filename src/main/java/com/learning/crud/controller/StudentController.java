package com.learning.crud.controller;

import com.learning.crud.entity.Student;
import com.learning.crud.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student studentReq){
         Student createdStudent = studentService.createAccount(studentReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student studentOptional =studentService.getStudent(id);

        if(studentOptional == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(studentOptional);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students = studentService.getAllStudents();

        if(students.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(students);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student student){
        Student updatedStudent = studentService.updateStudent(id,student);

        if(updatedStudent == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        boolean deleted = studentService.deleteStudent(id);

        if(!deleted)
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @PatchMapping("soft-delete/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id){
        boolean softDeleted = studentService.softDeleteStudent(id);

        if(!softDeleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok("Deleted student sucessfully");
    }


}
