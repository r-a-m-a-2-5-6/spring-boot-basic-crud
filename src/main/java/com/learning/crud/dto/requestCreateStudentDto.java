package com.learning.crud.dto;

import jakarta.validation.constraints.*;

public class requestCreateStudentDto {
    @Min(value=18,message="Student age should be greater than 18.")
    private int age;

    @NotBlank(message="Name should not be blank")
    @Size(min=2,max=50,message = "Name size should be more than 2 characters or less than 50 characters")
    private String name;

    @Email(message="Email can not be empty")
    private String email;

    @NotEmpty(message="Roll number can not be empty")
    private Integer rollNo;

    @NotBlank(message ="Subject name can not be empty")
    private String subject;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
