package com.warriors.projectmanagerapi.repository;

import com.warriors.projectmanagerapi.entity.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Date;

import static com.warriors.projectmanagerapi.entity.TaskStatus.TO_DO;

@DataJpaTest
public class TaskRepositoryTests {
    @Autowired
    private TaskRepository taskRepository;

    Task task;

    @BeforeEach
    public void setUp() {
        //Add Task
        Task task = new Task(1L, "Task 1", "Task 1 Description", TO_DO, null, null);
        taskRepository.save(task);
    }

    // Test case SUCCESS
    @Test
    public void testFindTaskById_Found() {
        Task task = taskRepository.findById(1L).get();
        assert(task.getTitle().equals("Task 1"));
    }
    @AfterEach
    public void tearDown() {
        task = null;
        taskRepository.deleteAll();
    }
}
