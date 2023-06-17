package com.example.projects.controller;


import com.example.projects.Exception.NotFoundException;
import com.example.projects.model.TaskAssignment;
import com.example.projects.repository.TaskAssignmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectId}/tasks/{taskId}/assignments")
public class AssignmentController {

    private final TaskAssignmentRepository assignmentRepository;

    public AssignmentController(TaskAssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;}

    @PostMapping
    public TaskAssignment createAssignment(@PathVariable Long taskId, @RequestBody TaskAssignment assignment) {
        assignment.setTaskId(taskId);
        return assignmentRepository.<TaskAssignment>save(assignment);
    }

    @GetMapping("/{assignmentId}")
    public TaskAssignment getAssignmentById(@PathVariable Long assignmentId) {
        return assignmentRepository.findById(assignmentId)
                .map(assignment -> (TaskAssignment) assignment)
                .orElseThrow(() -> new NotFoundException("Assignment not found with id: " + assignmentId));
    }

    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long assignmentId) {
        assignmentRepository.deleteById(assignmentId);
        return ResponseEntity.noContent().build();
    }

}