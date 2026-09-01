package com.learning.crud.service;

import com.learning.crud.entity.Student;
import com.learning.crud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createAccount(Student studentReq){
        studentReq.setDeleted(false);
        return studentRepository.save(studentReq);
    }

    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findByIdAndDeletedIsFalse(id);

        return student.orElse(null);

    }

    public List<Student> getAllStudents() {

        List<Student> students = studentRepository.findByDeletedIsFalse();

        if(students.isEmpty())
            return null;

        return students;
    }

    public Student updateStudent(Long id, Student student) {

        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()) {
            return null;
        }

        Student studentUpdate = existingStudent.get();

        studentUpdate.setRollNo(student.getRollNo());
        studentUpdate.setSubject(student.getSubject());
        studentUpdate.setEmail(student.getEmail());
        studentUpdate.setAge(student.getAge());
        studentUpdate.setName(student.getName());
        studentUpdate.setDeleted(false);
        return studentRepository.save(studentUpdate);
    }

    public boolean deleteStudent(Long id) {

        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isEmpty())
            return false;

        studentRepository.deleteById(id);

        return true;
    }

    public boolean softDeleteStudent(Long id) {

        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty())
            return false;

        Student saveToDb = existingStudent.get();

        saveToDb.setDeleted(true);

        studentRepository.save(saveToDb);

        return true;
    }
}
