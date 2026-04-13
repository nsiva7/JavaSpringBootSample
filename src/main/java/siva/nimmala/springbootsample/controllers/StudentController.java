package siva.nimmala.springbootsample.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siva.nimmala.springbootsample.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static siva.nimmala.springbootsample.controllers.StudentAPIs.createStudentAPI;
import static siva.nimmala.springbootsample.data.Constants.*;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "APIs for managing student records")
public class StudentController {

    private final Map<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public StudentController() {
        System.out.println("Creating Student Controller");
    }

    @Operation(summary = CREATE_A_NEW_STUDENT, description = CREATES_A_NEW_STUDENT_RECORD_WITH_AUTO_GENERATED_ID)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = STATUS_200,
                            description = STUDENT_CREATED_SUCCESSFULLY,
                            content = @Content(schema = @Schema(implementation = Student.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input",
                            content = @Content
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return createStudentAPI(student, idGenerator, students);
    }

    @Operation(summary = "Get all students", description = "Retrieves a list of all student records")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
            content = @Content(schema = @Schema(implementation = Student.class)))
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(new ArrayList<>(students.values()));
    }

    @Operation(summary = "Get student by ID", description = "Retrieves a specific student record by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found",
                    content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @Parameter(description = "ID of the student to retrieve", required = true)
            @PathVariable Long id) {
        Student student = students.get(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @Operation(summary = "Update a student", description = "Updates an existing student record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully",
                    content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @Parameter(description = "ID of the student to update", required = true)
            @PathVariable Long id,
            @RequestBody Student student) {
        if (!students.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        Student updatedStudent = new Student(id, student.name(), student.age());
        students.put(id, updatedStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(summary = "Delete a student", description = "Deletes a student record by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "ID of the student to delete", required = true)
            @PathVariable Long id) {
        if (!students.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        students.remove(id);
        return ResponseEntity.noContent().build();
    }
}
