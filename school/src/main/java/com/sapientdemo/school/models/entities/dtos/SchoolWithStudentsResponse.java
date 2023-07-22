package com.sapientdemo.school.models.entities.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolWithStudentsResponse {

    private Long id;
    private String name;
    private String email;
    private String address;
    private String description;
    List<StudentResponse> students;
}