package com.sapientdemo.school.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapientdemo.school.models.entities.School;
import com.sapientdemo.school.repositories.SchoolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository repository;

    public void saveSchool(School student) {
        repository.save(student);
    }

    public List<School> findAllSchools() {
        return repository.findAll();
    }
}
