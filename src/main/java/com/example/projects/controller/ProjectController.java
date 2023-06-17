package com.example.projects.controller;

import com.example.projects.Exception.NotFoundException;
import com.example.projects.model.Project;
import com.example.projects.repository.ProjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return projectRepository.findAll()
    ;}

    @PostMapping
    public Project createProject(@RequestBody Project project){
        return projectRepository.save(project);
    }

    @GetMapping("/{projectId}")
    public Project getProjectId(@PathVariable Long projectId){
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project not found with id: " + projectId));
    }

    @PutMapping("/{projectId}")
    public Project updateProject(@PathVariable Long projectId, @RequestBody Project projectDetails){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new NotFoundException("Project not found with id: " + projectId));

        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());

        return projectRepository.save(project);
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project not found with id: " + projectId));

        projectRepository.delete(project);

        return ResponseEntity.ok().build();
    }
}
