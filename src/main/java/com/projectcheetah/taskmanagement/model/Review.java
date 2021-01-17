package com.projectcheetah.taskmanagement.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Review {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="task_id")
    private Long taskId;

    @Column
    private String description;

    @Column
    private int rate;
}
