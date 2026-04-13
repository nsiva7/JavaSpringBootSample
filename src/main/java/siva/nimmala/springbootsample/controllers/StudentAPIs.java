package siva.nimmala.springbootsample.controllers;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import siva.nimmala.springbootsample.models.Student;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class StudentAPIs {

    static @NonNull ResponseEntity<Student> createStudentAPI(
            Student student,
            AtomicLong idGenerator,
            Map<Long, Student> students
    ) {

        Long id = idGenerator.getAndIncrement();
        Student newStudent = new Student(id, student.name(), student.age());
        students.put(id, newStudent);

        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }
}
