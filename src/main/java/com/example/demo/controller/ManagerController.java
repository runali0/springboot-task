package com.example.demo.controller;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final UserService userService;
    private final TaskRepository taskRepository;

    public ManagerController(UserService userService, TaskRepository taskRepository) {
        this.userService = userService;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

   @PostMapping("/assign-task")
public Task assignTask(@RequestParam Long userId,
                       @RequestBody Task task) {

    User user = userService.getUserById(userId);

    task.setUser(user);

    return taskRepository.save(task);
}
}