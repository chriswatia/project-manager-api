package com.warriors.projectmanagerapi.Controllers;

import com.warriors.projectmanagerapi.Models.Task;
import com.warriors.projectmanagerapi.Models.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.warriors.projectmanagerapi.Models.Project;
import com.warriors.projectmanagerapi.Models.TaskSummary;
import com.warriors.projectmanagerapi.Services.ProjectService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")

public class ProjectController {
    private final ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //POST Create Project
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    //GET All Projects a
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    //GET Project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);

        return ResponseEntity.ok(project);
    }

    //PUT Update a project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        Optional<Project> updatedProject = projectService.updateProject(id, project);
        return updatedProject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a project (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project Deleted Successfully!");
    }

    // Add a new task to a specific project
    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Task> addProjectTask(
            @PathVariable Long projectId,
            @RequestBody Task task
    ) {
        Task createdTask = projectService.addProjectTask(projectId, task);
        return ResponseEntity.ok(createdTask);
    }

    // Retrieve Project Tasks with optional filters and pagination
    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<Page<Task>> getTasksForProject(
            @PathVariable Long projectId,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) Date dueDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Task> tasks = projectService.getProjectTasks(projectId, status, dueDate, page, size);
        return ResponseEntity.ok(tasks);
    }


    // Get Project Tasks Summary
    @GetMapping("/summary")
    public ResponseEntity<List<TaskSummary>> getAllProjectTaskSummaries() {
        List<TaskSummary> summary = projectService.getProjectTaskSummary();
        return ResponseEntity.ok(summary);
    }
}
