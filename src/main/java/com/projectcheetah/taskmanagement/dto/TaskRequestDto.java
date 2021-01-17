package com.projectcheetah.taskmanagement.dto;

import com.projectcheetah.taskmanagement.enums.TaskRequestStatus;
import com.projectcheetah.taskmanagement.model.Task;
import com.projectcheetah.taskmanagement.model.TaskRequest;
import lombok.Data;

@Data
public class TaskRequestDto {
    private Long taskId;
    private Long customerId;
    private Long taskerId;
    private int quote;
    private TaskRequestStatus taskRequestStatus;
    private Task task;

    public TaskRequest convertTaskRequestDto(){
        TaskRequest tr= new TaskRequest();
        tr.setCustomerId(customerId);
        tr.setTaskRequestStatus(taskRequestStatus);
        tr.setTaskerId(taskerId);
        tr.setTaskId(taskId);
        tr.setQuote(quote);
        return tr;
    }


}
