package com.projectcheetah.taskmanagement.dao;

import com.projectcheetah.taskmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface  UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findByRoles_Id(Long id);


}
