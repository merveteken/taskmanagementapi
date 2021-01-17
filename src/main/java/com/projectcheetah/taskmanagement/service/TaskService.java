package com.projectcheetah.taskmanagement.service;

import com.projectcheetah.taskmanagement.dao.TaskDao;
import com.projectcheetah.taskmanagement.dao.TaskRequestDao;
import com.projectcheetah.taskmanagement.dao.UserDao;
import com.projectcheetah.taskmanagement.dto.TaskAndRequest;
import com.projectcheetah.taskmanagement.dto.TaskDto;
import com.projectcheetah.taskmanagement.dto.TaskRequestDto;
import com.projectcheetah.taskmanagement.enums.TaskRequestStatus;
import com.projectcheetah.taskmanagement.model.Task;
import com.projectcheetah.taskmanagement.model.TaskRequest;
import com.projectcheetah.taskmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TaskRequestDao taskRequestDao;



    public Task saveTask(TaskDto taskDto) throws ParseException {
        Task task = taskDto.getTaskFromTaskDto();
        User customer = userDao.findById(taskDto.getCustomerId()).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        User tasker = userDao.findById(taskDto.getTaskerId()).orElseThrow(() -> new IllegalArgumentException("Tasker not found"));
        return taskDao.save(task);
    }

    public List<Task> getTasksById(Long taskerId) throws Exception {
        List<Task> tasks  = taskDao.findAllByTaskerUser(taskerId).orElseThrow(() -> new Exception("task not found"));
       return tasks;
    }

    public List<Task> getTasks()  {
        List<Task> tasks  = taskDao.findAll();
        return tasks;
    }


    public TaskRequest sendRequest(TaskRequestDto taskRequestDto) {
        TaskRequest t = taskRequestDto.convertTaskRequestDto();
        return taskRequestDao.save(t);
    }

    public List<TaskRequest> getAllTaskRequest()  {
        List<TaskRequest> tasks  = taskRequestDao.findAll();
        return tasks;
    }

    public List<TaskAndRequest> getTaskerRequests(Long taskerId) {
        TaskAndRequest trd = new TaskAndRequest();
        List<TaskAndRequest> taskAndRequestList = new ArrayList<>();
        List<TaskRequest> tr = taskRequestDao.findTaskRequestByTaskerIdAndTaskRequestStatus(taskerId, TaskRequestStatus.PENDING);
        for(TaskRequest t : tr){
            trd.setTaskRequest(t);
            trd.setTask(taskDao.findById(t.getTaskId()).get());
            taskAndRequestList.add(trd);
        }
       return taskAndRequestList;
    }
}
