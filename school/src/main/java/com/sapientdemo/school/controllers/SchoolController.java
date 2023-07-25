package com.sapientdemo.school.controllers;

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

import com.sapientdemo.school.graphql.InputSchool;
import com.sapientdemo.school.models.entities.School;
import com.sapientdemo.school.models.entities.dtos.SchoolWithStudentsResponse;
import com.sapientdemo.school.services.SchoolService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    // REST API Endpoints
    // Create school
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody School school) {
        schoolService.saveSchool(school);
    }

    // Get all schools
    @GetMapping
    public ResponseEntity<List<School>> findAllSchools() {
        return ResponseEntity.ok(schoolService.findAllSchools());
    }

    // Get schools by id
    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Integer id) {
        School school = schoolService.getSchoolById(id);
        if (school != null) {
            return ResponseEntity.ok(school);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete school by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Integer id) {
        boolean isDeleted = schoolService.deleteSchool(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all schools with students
    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<SchoolWithStudentsResponse> findAllSchools(
            @PathVariable("school-id") Integer schoolId) {
        return ResponseEntity.ok(schoolService.findSchoolsWithStudents(schoolId));
    }

    // Graphql Queries and Mutations
    // Find school by id
    @QueryMapping(name = "findSchoolById")
    public School findById(@Argument(name = "schoolId") String id) {
        Integer schoolId = Integer.parseInt(id);

        return schoolService.getSchoolById(schoolId);
    }

    // Find all schools
    @QueryMapping(name = "findAllSchools")
    public List<School> findAll() {
        return schoolService.findAllSchools();
    }

    // Create school
    @MutationMapping
    public School createSchool(@Argument InputSchool inputSchool) {
        School school = new School();
        school.setName(inputSchool.getName());
        school.setEmail(inputSchool.getEmail());
        school.setAddress(inputSchool.getAddress());
        school.setDescription(inputSchool.getDescription());

        schoolService.saveSchool(school);

        return school;
    }

    // Delete school
    @MutationMapping
    public String deleteSchool(@Argument(name = "schoolId") String id) {
        Integer schoolId = Integer.parseInt(id);

        if (schoolService.deleteSchool(schoolId)) {
            return "School deleted";
        } else {
            return "There was an error deleting the school";
        }
    }
}