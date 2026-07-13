package com.medishop.todo.service;

import com.medishop.todo.dto.TaskDto;
import com.medishop.todo.model.Task;
import com.medishop.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TaskDto getById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche introuvable avec id " + id));
        return toDto(task);
    }

    public TaskDto create(TaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDone(dto.isDone());
        return toDto(taskRepository.save(task));
    }

    public TaskDto update(Long id, TaskDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche introuvable avec id " + id));
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDone(dto.isDone());
        return toDto(taskRepository.save(task));
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Tâche introuvable avec id " + id);
        }
        taskRepository.deleteById(id);
    }

    private TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isDone(),
                task.getCreatedAt()
        );
    }
}