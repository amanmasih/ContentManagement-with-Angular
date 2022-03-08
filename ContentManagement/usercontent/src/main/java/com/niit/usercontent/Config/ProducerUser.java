package com.niit.usercontent.Config;

import com.niit.usercontent.rabbitmq.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerUser {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @Autowired
    public ProducerUser(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendMessage(UserDTO userDTO){
        //covert the (exchange,routing key,object)
        rabbitTemplate.convertAndSend(exchange.getName(),"user_routing",userDTO);
    }
}//end of class
