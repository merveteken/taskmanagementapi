package com.projectcheetah.taskmanagement.model;

import com.projectcheetah.taskmanagement.enums.TaskRequestStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "public")
public class TaskRequest {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private TaskRequestStatus taskRequestStatus;

    @Column
    private Long taskId;

    @Column
    private Long taskerId;

    @Column
    private Long customerId;

    @Column
    private int quote;


}
