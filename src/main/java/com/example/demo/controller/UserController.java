package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import com.example.demo.entity.Task;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashSet;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final TaskRepository taskRepository;

    public UserController(UserService userService,
                          RoleRepository roleRepository,
                          TaskRepository taskRepository) {

        this.userService = userService;
        this.roleRepository = roleRepository;
        this.taskRepository = taskRepository;
    }

    // Create Role
    @PostMapping("/role")
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    // Create User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Get All Users
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    // Assign Role
    @PostMapping("/assign-role")
    public User assignRole(@RequestParam Long userId,
                           @RequestParam Long roleId) {

        User user = userService.getUserById(userId);

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }

        user.getRoles().add(role);

        return userService.saveUser(user);
    }

    // Get User Profile
    @GetMapping("/profile")
    public User getProfile(@RequestParam Long userId) {
        return userService.getUserById(userId);
    }

  @GetMapping("/tasks")
public List<Task> getUserTasks(@RequestParam Long userId) {

    User user = userService.getUserById(userId);

    if(user == null){
        throw new RuntimeException("User not found");
    }

    return taskRepository.findByUser(user);
}
}