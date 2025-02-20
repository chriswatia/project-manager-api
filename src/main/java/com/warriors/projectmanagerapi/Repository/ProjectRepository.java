package com.warriors.projectmanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warriors.projectmanagerapi.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
