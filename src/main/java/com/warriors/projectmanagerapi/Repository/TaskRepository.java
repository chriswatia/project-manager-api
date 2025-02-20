package com.warriors.projectmanagerapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warriors.projectmanagerapi.entity.Task;
import com.warriors.projectmanagerapi.entity.TaskStatus;

import java.util.Date;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findProjectById(Long projectId, Pageable pageable);
    Page<Task> findProjectByIdAndStatus(Long projectId, TaskStatus status, Pageable pageable);
    Page<Task> findProjectByIdAndDueDate(Long projectId, Date dueDate, Pageable pageable);
    Page<Task> findProjectByIdAndStatusAndDueDate(Long projectId, TaskStatus status, Date dueDate, Pageable pageable);
    long countProjectByIdAndStatus(Long projectId, TaskStatus status);
}
