package com.sapientdemo.school.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapientdemo.school.client.StudentClient;
import com.sapientdemo.school.models.entities.School;
import com.sapientdemo.school.models.entities.dtos.SchoolWithStudentsResponse;
import com.sapientdemo.school.repositories.SchoolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final StudentClient studentClient;

    public void saveSchool(School student) {
        schoolRepository.save(student);
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
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
