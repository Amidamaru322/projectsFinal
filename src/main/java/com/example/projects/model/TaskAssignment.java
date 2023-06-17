package com.example.projects.model;

import jakarta.persistence.*;
import org.hibernate.sql.ast.tree.expression.Expression;
import org.hibernate.sql.ast.tree.update.Assignable;
import org.hibernate.sql.ast.tree.update.Assignment;

import java.time.LocalDate;


@Entity
@Table(name = "taskassignment")
public class TaskAssignment extends Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String assignedTo;
    private LocalDate dueDate;


    public TaskAssignment(Assignable assignable, Expression assignedValue, Long taskId, String assignedTo, LocalDate dueDate) {
        super(assignable, assignedValue);
        this.taskId = taskId;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}

