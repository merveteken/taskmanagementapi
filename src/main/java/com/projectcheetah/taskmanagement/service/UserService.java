package com.projectcheetah.taskmanagement.service;

import com.projectcheetah.taskmanagement.dao.UserDao;
import com.projectcheetah.taskmanagement.dto.UserDto;
import com.projectcheetah.taskmanagement.model.Role;
import com.projectcheetah.taskmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service(value = "userService")
public class UserService implements UserDetailsService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    public User save(UserDto user) throws IllegalAccessException {
        User savedUser = userDao.findByUsername(user.getUsername());
        if (savedUser == null) {
            savedUser = user.getUserFromDto();
            savedUser.setPassword(bcryptEncoder.encode(user.getPassword()));

            Role role = roleService.findRoleByName(user.getRole());
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            savedUser.setRoles(roleSet);
            return userDao.save(savedUser);
        }else{
            throw new IllegalAccessException();
        }


    }


    public User  me(String username) throws IllegalAccessException {
        User savedUser = userDao.findByUsername(username);
        if (savedUser != null) {

            return savedUser;
        }else{
            throw new IllegalAccessException();
        }

    }

    public List<User> getTaskers(){
        return userDao.findByRoles_Id(1L);
    }

    public User getCustomer(Long id) {
        return userDao.findById(id).get();
    }
}