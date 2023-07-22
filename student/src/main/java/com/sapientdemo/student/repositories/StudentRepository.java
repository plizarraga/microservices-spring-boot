package com.sapientdemo.student.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapientdemo.student.models.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllBySchoolId(Integer schoolId);
}
