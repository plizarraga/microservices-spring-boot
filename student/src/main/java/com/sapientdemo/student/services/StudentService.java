package com.sapientdemo.student.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapientdemo.student.models.entities.Student;
import com.sapientdemo.student.repositories.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    // public List<Student> findAllStudentsBySchool(Integer schoolId) {
    // return repository.findAllBySchoolId(schoolId);
    // }
}
