package com.sapientdemo.school.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapientdemo.school.client.StudentClient;
import com.sapientdemo.school.models.entities.School;
import com.sapientdemo.school.models.entities.dtos.SchoolWithStudentsResponse;
import com.sapientdemo.school.repositories.SchoolRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final StudentClient studentClient;

    public void saveSchool(School school) {
        schoolRepository.save(school);
        log.info("School added: {}", school);
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolById(Integer id) {
        var school = schoolRepository.findById(id).orElse(null);
        return school;
    }

    public boolean deleteSchool(Integer schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElse(null);

        if (school == null) {
            return false;
        }

        // Check if the school has any associated students
        var students = studentClient.findAllStudentsBySchool(schoolId);
        if (!students.isEmpty()) {
            return false;
        }

        // Delete the school if no associated students
        schoolRepository.delete(school);
        log.info("School deleted: {}", school);

        return true;
    }

    public SchoolWithStudentsResponse findSchoolsWithStudents(Integer schoolId) {
        var school = schoolRepository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build());

        var students = studentClient.findAllStudentsBySchool(schoolId);

        return SchoolWithStudentsResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .address(school.getAddress())
                .description(school.getDescription())
                .students(students)
                .build();
    }
}
