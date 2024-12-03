package com.warriors.projectmanagerapi.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warriors.projectmanagerapi.Models.Task;
import com.warriors.projectmanagerapi.Models.TaskStatus;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findProjectById(Long projectId, Pageable pageable);
    Page<Task> findProjectByIdAndStatus(Long projectId, TaskStatus status, Pageable pageable);
    Page<Task> findProjectByIdAndDueDate(Long projectId, Date dueDate, Pageable pageable);
    Page<Task> findProjectByIdAndStatusAndDueDate(Long projectId, TaskStatus status, Date dueDate, Pageable pageable);
    long countProjectByIdAndStatus(Long projectId, TaskStatus status);
}
