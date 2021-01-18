package com.projectcheetah.taskmanagement.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "public")
public class Role {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private String name;


}
