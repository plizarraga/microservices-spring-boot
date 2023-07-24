package com.sapientdemo.school.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sapientdemo.school.models.entities.School;
import com.sapientdemo.school.repositories.SchoolRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final SchoolRepository schoolRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading schools data...");
        // Check if the school repository is empty
        if (schoolRepository.count() == 0) {
            List<School> sampleSchools = createSampleSchools();

            schoolRepository.saveAll(sampleSchools);
        }
        log.info("Schools data loaded.");
    }

    private List<School> createSampleSchools() {
        List<School> schools = new ArrayList<>();

        schools.add(createSchool("Bright Beginnings Preschool", "contact@brightbeginningspreschool.com",
                "987 First Avenue, Suburbia", "A creative preschool fostering early childhood development."));
        schools.add(createSchool("Sunshine Elementary School", "info@sunshineelementary.com",
                "123 Main Street, Cityville", "A nurturing environment for young learners."));
        schools.add(createSchool("Greenwood Middle School", "hello@greenwoodmiddle.com",
                "456 Oak Road, Townsville", "Empowering students for a bright future."));
        schools.add(createSchool("Highland High School", "highlandhigh@example.com",
                "789 Pine Lane, Highland Town", "Preparing students for excellence in academics and beyond."));
        schools.add(createSchool("Riverfront Academy", "contact@riverfrontacademy.com",
                "101 River View, Riverside", "A place for holistic growth and learning."));
        schools.add(createSchool("Pinecrest School", "info@pinecrestschool.edu",
                "555 Elm Street, Pineville", "Fostering a passion for knowledge and discovery."));
        schools.add(createSchool("Liberty Middle School", "admin@libertyms.org",
                "222 Freedom Road, Libertyville", "Where knowledge meets freedom."));
        schools.add(createSchool("Harmony Elementary", "harmony.admin@example.com",
                "444 Harmony Lane, Serenity City", "Creating harmony through education."));
        schools.add(createSchool("Oasis High School", "info@oasishigh.edu",
                "777 Oasis Avenue, Oasis City", "A vibrant oasis of education and innovation."));
        schools.add(createSchool("Golden Grove Academy", "info@goldengroveacademy.com",
                "888 Golden Grove, Greenfields", "Nurturing young minds to blossom like golden flowers."));

        return schools;
    }

    private School createSchool(String name, String email, String address, String description) {
        return School.builder()
                .name(name)
                .email(email)
                .address(address)
                .description(description)
                .build();
    }
}
