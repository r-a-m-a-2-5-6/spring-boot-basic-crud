package com.learning.crud.controller;

import com.learning.crud.dto.requestCreateStudentDto;
import com.learning.crud.dto.requestUpdateStudentDto;
import com.learning.crud.dto.responseCreateStudentDto;
import com.learning.crud.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<responseCreateStudentDto> createStudent(@Validated @RequestBody requestCreateStudentDto studentReq){
        responseCreateStudentDto createdStudent = studentService.createAccount(studentReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<responseCreateStudentDto> getStudent(@PathVariable Long id){
        responseCreateStudentDto studentOptional =studentService.getStudent(id);

        if(studentOptional == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(studentOptional);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<responseCreateStudentDto>> getAllStudent(){
        List<responseCreateStudentDto> students = studentService.getAllStudents();

        if(students.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(students);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<responseCreateStudentDto> updateStudent(@PathVariable Long id,@Validated @RequestBody requestUpdateStudentDto student){
        responseCreateStudentDto updatedStudent = studentService.updateStudent(id,student);

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

        return ResponseEntity.ok("Deleted student successfully");
    }


}
