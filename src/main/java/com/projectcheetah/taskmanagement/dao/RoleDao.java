package com.projectcheetah.taskmanagement.dao;

import com.projectcheetah.taskmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDao extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
}
