package com.projectcheetah.taskmanagement.dao;

import com.projectcheetah.taskmanagement.enums.TaskRequestStatus;
import com.projectcheetah.taskmanagement.model.TaskRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRequestDao extends JpaRepository<TaskRequest,Long> {
    List<TaskRequest> findTaskRequestByTaskerIdAndTaskRequestStatus(Long id, TaskRequestStatus status);
}
