package com.niit.usercontent.rabbitmq;
import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
    private String userId;
    private String userName;
    private String password;


    //custom
    public UserDTO(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}

