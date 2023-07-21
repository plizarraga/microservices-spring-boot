package com.sapientdemo.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapientdemo.school.models.entities.School;


public interface SchoolRepository extends JpaRepository<School, Integer> {

}
