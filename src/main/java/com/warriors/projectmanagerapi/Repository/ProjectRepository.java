package com.warriors.projectmanagerapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warriors.projectmanagerapi.Models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
