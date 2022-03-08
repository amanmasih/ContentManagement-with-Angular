package com.niit.authenticationservice.Config;

import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserNotFoundException;
import com.niit.authenticationservice.rabbitmq.UserDTO;
import com.niit.authenticationservice.service.UserServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer{

    //calling the method from userAuthentication serviceImpl for we will use the DTO class for Service
    @Autowired
    private UserServiceImpl userService;

    //queue name what is defined in configuration of movieService
    @RabbitListener(queues="user_queue")
    public void getUserDtoFromRabbitMq(UserDTO userDTO)throws UserNotFoundException {

        User user =new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        userService.saveUserDetails(user);
    }//end of the function

}//end of the class
