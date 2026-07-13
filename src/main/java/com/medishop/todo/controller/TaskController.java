package com.medishop.todo.controller;

import com.medishop.todo.dto.TaskDto;
import com.medishop.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@Valid @RequestBody TaskDto dto) {
        return ResponseEntity.ok(taskService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @Valid @RequestBody TaskDto dto) {
        return ResponseEntity.ok(taskService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}