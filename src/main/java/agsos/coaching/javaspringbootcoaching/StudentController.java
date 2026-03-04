package agsos.coaching.javaspringbootcoaching;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final Map<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public StudentController() {
        System.out.println("Creating Student Controller");

        students.put(1L, new Student(1L, "John Doe", 20));
        students.put(2L, new Student(2L, "Jane Smith", 22));

        idGenerator.set(3L);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Long id = idGenerator.getAndIncrement();
        Student newStudent = new Student(id, student.name(), student.age());
        students.put(id, newStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(new ArrayList<>(students.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = students.get(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        if (!students.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        Student updatedStudent = new Student(id, student.name(), student.age());
        students.put(id, updatedStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!students.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        students.remove(id);
        return ResponseEntity.noContent().build();
    }
}
