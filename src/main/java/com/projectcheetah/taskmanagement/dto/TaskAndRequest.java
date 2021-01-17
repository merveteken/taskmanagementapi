package com.projectcheetah.taskmanagement.dto;

import com.projectcheetah.taskmanagement.model.Task;
import com.projectcheetah.taskmanagement.model.TaskRequest;
import lombok.Data;

@Data
public class TaskAndRequest {
    private TaskRequest taskRequest;
    private Task task;
}
