package com.projectcheetah.taskmanagement.controller;

import com.projectcheetah.taskmanagement.config.TokenProvider;
import com.projectcheetah.taskmanagement.dto.UserDto;
import com.projectcheetah.taskmanagement.model.AuthToken;
import com.projectcheetah.taskmanagement.model.LoginUser;
import com.projectcheetah.taskmanagement.model.Task;
import com.projectcheetah.taskmanagement.model.User;
import com.projectcheetah.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","https://cheetahtaskmanagement.herokuapp.com"})
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException, IllegalAccessException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        HashMap<String,Object> usr = new HashMap<>();
        usr.put("token",token);
        usr.put("user",userService.me(loginUser.getUsername()));

        return ResponseEntity.ok(usr);
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user) throws IllegalAccessException {
        return userService.save(user);
    }


    @GetMapping("/taskers")
    public ResponseEntity<List<User>> getallTaskers() {
        return new ResponseEntity<>(userService.getTaskers(),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('TASKER')")
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User Can Read This";
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<User> getCustomer(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getCustomer(id),HttpStatus.OK);
    }

}
