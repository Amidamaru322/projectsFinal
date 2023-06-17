package com.example.projects.repository;

import com.example.projects.model.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskAssignmentRepository extends JpaRepository <TaskAssignment, Long>{
    List<TaskAssignment> findByTaskId(Long taskId);
}
