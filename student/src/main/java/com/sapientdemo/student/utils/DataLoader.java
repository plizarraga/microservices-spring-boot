package com.sapientdemo.student.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sapientdemo.student.models.entities.Student;
import com.sapientdemo.student.repositories.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

        private final StudentRepository studentRepository;

        @Override
        public void run(String... args) throws Exception {
                log.info("Loading students data...");
                // Check if the school repository is empty
                if (studentRepository.count() == 0) {
                        List<Student> sampleSchools = createSampleStudents();

                        studentRepository.saveAll(sampleSchools);
                }
                log.info("Students data loaded.");
        }

        private List<Student> createSampleStudents() {
                List<Student> schools = new ArrayList<>();

                schools.add(createStudent("Wally", "West", "wally.west@example.com", 0));
                schools.add(createStudent("Barry", "Allen", "barry.allen@example.com", 0));
                schools.add(createStudent("Eric", "Clapton", "eric.clapton@example.com", 0));
                schools.add(createStudent("Bruce", "Wayne", "bruce.wayne@example.com", 0));

                return schools;
        }

        private Student createStudent(String firstName, String lastName, String email, Integer schoolId) {
                return Student.builder()
                                .firstName(firstName)
                                .lastName(lastName)
                                .email(email)
                                .schoolId(schoolId)
                                .build();
        }
}
