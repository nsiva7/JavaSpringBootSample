package agsos.coaching.javaspringbootcoaching.controllers;

import agsos.coaching.javaspringbootcoaching.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private Map<String, User> users;

    UserController() {
        System.out.println("UserController");

        users = new HashMap<>();

        User user1 = new User("siva", "Siva");

        users.put(user1.getId(), user1);
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(users.values().stream().toList());
    }

    @GetMapping("/users/{id}")
    ResponseEntity<User> getUser(@PathVariable String id) {
        if (users.containsKey(id)) {
            return ResponseEntity.ok(users.get(id));
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping("/create_user")
    ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(users.put(user.getId(), user));
    }
}
