package com.learning.crud.service;

import com.learning.crud.dto.requestCreateStudentDto;
import com.learning.crud.dto.requestUpdateStudentDto;
import com.learning.crud.dto.responseCreateStudentDto;
import com.learning.crud.entity.Student;
import com.learning.crud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public responseCreateStudentDto createAccount(requestCreateStudentDto studentReq){
        Student createdStudent = mapToCreateDto(studentReq);

        return mapToCreateResponseDto(studentRepository.save(createdStudent));
    }

    private responseCreateStudentDto mapToCreateResponseDto(Student studentResp){
        responseCreateStudentDto createdResponse = new responseCreateStudentDto();

        createdResponse.setId(studentResp.getId());
        createdResponse.setCreatedAt(studentResp.getCreatedAt());
        createdResponse.setAge(studentResp.getAge());
        createdResponse.setEmail(studentResp.getEmail());
        createdResponse.setRollNo(studentResp.getRollNo());
        createdResponse.setUpdatedAt(studentResp.getUpdatedAt());
        createdResponse.setName(studentResp.getName());
        createdResponse.setSubject(studentResp.getSubject());

        return createdResponse;
    }

    private Student mapToCreateDto(requestCreateStudentDto studentReq) {

        Student createdStudent = new Student();

        createdStudent.setAge(studentReq.getAge());
        createdStudent.setName(studentReq.getName());
        createdStudent.setSubject(studentReq.getSubject());
        createdStudent.setRollNo(studentReq.getRollNo());
        createdStudent.setEmail(studentReq.getEmail());
        createdStudent.setCreatedAt(LocalDateTime.now());
        createdStudent.setUpdatedAt(LocalDateTime.now());
        createdStudent.setDeleted(false);
        return createdStudent;
    }

    public responseCreateStudentDto getStudent(Long id) {
        Optional<Student> student = studentRepository.findByIdAndDeletedIsFalse(id);
        if (student.isEmpty())
            return null;
        Student studentResp = student.get();
        return mapToCreateResponseDto(studentResp);

    }

    public List<responseCreateStudentDto> getAllStudents() {

        List<Student> studentsList = studentRepository.findByDeletedIsFalse();

        if(studentsList.isEmpty())
            return null;

        List<responseCreateStudentDto> studentList = new ArrayList<>();

        for(Student student:studentsList )
            studentList.add(mapToCreateResponseDto(student));
        return studentList;
    }

    public responseCreateStudentDto updateStudent(Long id, requestUpdateStudentDto student) {

        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()) {
            return null;
        }

        Student studentUpdate = existingStudent.get();

        studentUpdate.setRollNo(student.getRollNo());
        studentUpdate.setSubject(student.getSubject());
        studentUpdate.setAge(student.getAge());
        studentUpdate.setName(student.getName());
        studentUpdate.setDeleted(false);
        studentUpdate.setUpdatedAt(LocalDateTime.now());
        return mapToCreateResponseDto(studentRepository.save(studentUpdate));
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
