package com.projectcheetah.taskmanagement.dao;

import com.projectcheetah.taskmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskDao extends JpaRepository<Task,Long> {
    Optional<List<Task>> findAllByTaskerUser(Long taskId);
}
