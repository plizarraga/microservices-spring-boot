package com.sapientdemo.student.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sapientdemo.student.graphql.InputStudent;
import com.sapientdemo.student.models.entities.Student;
import com.sapientdemo.student.services.StudentService;
import com.sapientdemo.student.utils.DeleteResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // REST API endpoints
    // Create student
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody Student student) {
        studentService.saveStudent(student);
    }

    // Get all students
    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    // Get student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete student by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        DeleteResponse response = studentService.deleteStudent(id);
        if (response.isSuccess()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.unprocessableEntity().body(response.getMessage());
        }
    }

    // Get all students by school id
    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<Student>> findAllStudents(
            @PathVariable("school-id") Integer schoolId) {
        return ResponseEntity.ok(studentService.findAllStudentsBySchool(schoolId));
    }

    // GraphQL Queries and Mutations
    // Find student by id
    @QueryMapping(name = "findStudentById")
    public Student findById(@Argument(name = "studentId") String id) {
        Integer studentId = Integer.parseInt(id);

        return studentService.getStudentById(studentId);
    }

    // Find all students
    @QueryMapping(name = "findAllStudents")
    public List<Student> findAll() {
        return studentService.findAllStudents();
    }

    // Create student
    @MutationMapping
    public Student createStudent(@Argument InputStudent inputStudent) {
        Student student = new Student();
        student.setFirstName(inputStudent.getFirstName());
        student.setLastName(inputStudent.getLastName());
        student.setEmail(inputStudent.getEmail());
        student.setSchoolId(Integer.parseInt(inputStudent.getSchoolId()));

        studentService.saveStudent(student);

        return student;
    }

    // Delete student by id
    @MutationMapping
    public String deleteStudent(@Argument(name = "studentId") String id) {
        Integer studentId = Integer.parseInt(id);
        return studentService.deleteStudent(studentId).getMessage();
    }
}