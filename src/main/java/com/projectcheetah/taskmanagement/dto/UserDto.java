package com.projectcheetah.taskmanagement.dto;

import com.projectcheetah.taskmanagement.model.User;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;

    private String password;
    private String email;
    private String phone;
    private String role;
    public User getUserFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        return user;
    }

}
