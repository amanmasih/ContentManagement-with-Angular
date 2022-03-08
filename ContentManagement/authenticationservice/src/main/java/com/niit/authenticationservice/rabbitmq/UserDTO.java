package com.niit.authenticationservice.rabbitmq;
import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String userName;
    private String password;
}
