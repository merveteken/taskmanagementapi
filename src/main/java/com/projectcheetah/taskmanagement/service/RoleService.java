package com.projectcheetah.taskmanagement.service;

import com.projectcheetah.taskmanagement.dao.RoleDao;
import com.projectcheetah.taskmanagement.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role findRoleByName(String name){
        return roleDao.findRoleByName(name);
    }
}
