package com.sapientdemo.school.graphql;

import lombok.Data;

@Data
public class InputSchool {
    private String name;
    private String email;
    private String address;
    private String description;
}
