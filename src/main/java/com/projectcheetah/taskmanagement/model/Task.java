package com.projectcheetah.taskmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(schema = "public")
public class Task {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_id")
    private Long customerUser;

    @Column(name="tasker_id")
    private Long taskerUser;

    @Column
    private Date date;

    @Column
    private String description;

    @Column
    private Double price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="task_id")
    private List<Review> review;



}
