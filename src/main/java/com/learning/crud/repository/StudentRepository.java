package com.learning.crud.repository;

import com.learning.crud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    List<Student> findByDeletedIsFalse();

//    public Student saveToDB(Student studentData){
////        //
////        System.out.println("Data saved into db successfully");
////        Student s1 = new Student();
////        s1.setAge(studentData.getAge());
////        s1.setName(studentData.getName());
////        s1.setEmail(studentData.getEmail());
////        //s1.setId(studentData.getId());
////        s1.setSubject(studentData.getSubject());
////        s1.setRollNo(studentData.getRollNo());
////        //System.out.println(s);
//        return null;
    //}
}
