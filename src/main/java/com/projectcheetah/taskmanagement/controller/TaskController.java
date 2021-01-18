package com.projectcheetah.taskmanagement.controller;

import com.projectcheetah.taskmanagement.dto.TaskAndRequest;
import com.projectcheetah.taskmanagement.dto.TaskDto;
import com.projectcheetah.taskmanagement.dto.TaskRequestDto;
import com.projectcheetah.taskmanagement.model.Task;
import com.projectcheetah.taskmanagement.model.TaskRequest;
import com.projectcheetah.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller@CrossOrigin(origins = {"http://localhost:4200","https://cheetahtaskmanagement.herokuapp.com"})

@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskDto) throws ParseException {
       Task task =  taskService.saveTask(taskDto);
        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/sendRequest")
    public ResponseEntity<TaskRequest> sendTaskRequest(@RequestBody TaskRequestDto taskRequestDto) throws ParseException {
        TaskRequest task =  taskService.sendRequest(taskRequestDto);
        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('TASKER')")
    public ResponseEntity<List<Task>> getallTasksById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(taskService.getTasksById(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getallTasks() throws Exception {
        return new ResponseEntity<>(taskService.getTasks(),HttpStatus.OK);
    }


    @GetMapping("/allrequest")
    public ResponseEntity<List<TaskRequest>> getallTaskReq() throws Exception {
        return new ResponseEntity<>(taskService.getAllTaskRequest(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TASKER')")
    @GetMapping("/tasker")
    public ResponseEntity<List<TaskAndRequest>> getTaskerTaskReq(@RequestParam Long taskerId) throws Exception {
        return new ResponseEntity<>(taskService.getTaskerRequests(taskerId),HttpStatus.OK);
    }



}
