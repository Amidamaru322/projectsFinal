package com.example.projects.controller;

import com.example.projects.Exception.NotFoundException;
import com.example.projects.model.Task;
import com.example.projects.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getTasksForProject(@PathVariable Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    @PostMapping
    public Task createTask(@PathVariable Long projectId, @RequestBody Task task) {
        task.setProjectId(projectId);
        return taskRepository.save(task);}

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long projectId, @PathVariable Long taskId) {
        return taskRepository.findByIdAndProjectId(taskId, projectId)
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + taskId));
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Task taskDetails) {
        Task task = taskRepository.findByIdAndProjectId(taskId, projectId)
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + taskId));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());

        return taskRepository.save(task);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        Task task = taskRepository.findByIdAndProjectId(taskId, projectId)
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + taskId));

        taskRepository.delete(task);

        return ResponseEntity.ok().build();
    }
}
