package com.warriors.projectmanagerapi.service;

import com.warriors.projectmanagerapi.Configuration.ResourceNotFoundException;
import com.warriors.projectmanagerapi.entity.Task;
import com.warriors.projectmanagerapi.entity.TaskStatus;
import com.warriors.projectmanagerapi.entity.TaskSummary;
import com.warriors.projectmanagerapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.warriors.projectmanagerapi.entity.Project;
import com.warriors.projectmanagerapi.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    // Fetch all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    //Get Project By ID
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    //Create Project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Update a project
    public Optional<Project> updateProject(Long projectId, Project projectDetails) {
        Optional<Project> existingProject = projectRepository.findById(projectId);
        if (existingProject.isPresent()) {
            Project project = existingProject.get();
            project.setName(projectDetails.getName());
            project.setDescription(projectDetails.getDescription());
            return Optional.of(projectRepository.save(project));
        }
        return Optional.empty();
    }

    // Delete a project
    public void deleteProject(Long projectId) {
        if (projectRepository.existsById(projectId)) {
            projectRepository.deleteById(projectId);
        }
    }

    // Add a new task to a project
    public Task addProjectTask(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));

        task.setProject(project);
        return taskRepository.save(task);
    }

    // Retrieve all tasks for a project with optional filters and pagination
    public Page<Task> getProjectTasks(Long projectId, TaskStatus status, Date dueDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (status != null && dueDate != null) {
            return taskRepository.findProjectByIdAndStatusAndDueDate(projectId, status, dueDate, pageable);
        } else if (status != null) {
            return taskRepository.findProjectByIdAndStatus(projectId, status, pageable);
        } else if (dueDate != null) {
            return taskRepository.findProjectByIdAndDueDate(projectId, dueDate, pageable);
        } else {
            return taskRepository.findProjectById(projectId, pageable);
        }
    }

    // Get project tasks summary
    public List<TaskSummary> getProjectTaskSummary() {
        List<Project> projects = projectRepository.findAll();
        List<TaskSummary> summaryList = new ArrayList<>();

        for (Project project : projects) {
            long toDoCount = taskRepository.countProjectByIdAndStatus(project.getId(), TaskStatus.TO_DO);
            long inProgressCount = taskRepository.countProjectByIdAndStatus(project.getId(), TaskStatus.IN_PROGRESS);
            long doneCount = taskRepository.countProjectByIdAndStatus(project.getId(), TaskStatus.DONE);

            TaskSummary taskSummary = new TaskSummary(project.getName(), toDoCount, inProgressCount, doneCount);
            summaryList.add(taskSummary);
        }

        return summaryList;
    }
}
