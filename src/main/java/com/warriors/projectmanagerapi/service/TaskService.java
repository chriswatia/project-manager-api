package com.warriors.projectmanagerapi.Services;

import com.warriors.projectmanagerapi.Configuration.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warriors.projectmanagerapi.Models.Task;
import com.warriors.projectmanagerapi.Repository.TaskRepository;


@Service
public class TaskService {
    private final TaskRepository taskRepository;
    @Autowired

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Update Task
    public Task updateTask(Long taskId, Task task) {
        Task oldTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

        // Update old task details
        oldTask.setTitle(task.getTitle());
        oldTask.setDescription(task.getDescription());
        oldTask.setStatus(task.getStatus());
        oldTask.setDueDate(task.getDueDate());

        return taskRepository.save(oldTask);
    }

    // Delete a task
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

        taskRepository.delete(task);
    }
}

