package com.sapientdemo.student.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapientdemo.student.models.entities.Student;
import com.sapientdemo.student.repositories.StudentRepository;
import com.sapientdemo.student.utils.DeleteResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        var student = studentRepository.findById(id).orElse(null);
        return student;
    }

    public DeleteResponse deleteStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElse(null);

        if (student == null) {
            return new DeleteResponse(false, "Student not found");
        }

        studentRepository.delete(student);
        log.info("Student deleted: {}", student);
        return new DeleteResponse(true, "Student deleted successfully");
    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        return studentRepository.findAllBySchoolId(schoolId);
    }
}
