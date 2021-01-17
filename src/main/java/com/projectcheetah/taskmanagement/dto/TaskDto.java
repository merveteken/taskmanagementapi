package com.projectcheetah.taskmanagement.dto;

import com.projectcheetah.taskmanagement.model.Review;
import com.projectcheetah.taskmanagement.model.Task;
import com.projectcheetah.taskmanagement.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class TaskDto {
    private Long customerId;
    private Long taskerId;
    private String description;
    private String taskDate;
    private Double price;
    private List<Review> review;

    public Task getTaskFromTaskDto() throws ParseException {
        Task task = new Task();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = formatter.parse(taskDate.substring(0,10));
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        task.setDate(sqlDate);
        task.setTaskerUser(taskerId);
        task.setCustomerUser(customerId);
        task.setDescription(description);
        task.setPrice(price);
        return task;
    }


}
