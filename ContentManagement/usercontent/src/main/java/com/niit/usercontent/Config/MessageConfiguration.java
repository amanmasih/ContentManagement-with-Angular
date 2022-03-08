package com.niit.usercontent.Config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

 private String exchangeName="user_exchange";
 private String firstRegisterQueue="user_queue";


    @Bean
    public DirectExchange directExchange(){
     return new DirectExchange(exchangeName);
 }

    @Bean
    public Queue firstRegisterQueue(){
        return new Queue(firstRegisterQueue);//it takes two parameter string and boolean durable
    }//end of function


    //to convert the object data to binary format so that RabbitMQ will accept it, so we are using the library Jackson2JsonMessageConvertor
    @Bean
    Jackson2JsonMessageConverter producerJacksonConvertor(){return new Jackson2JsonMessageConverter();}

    @Bean //import from connection factory from amqp.core
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //the message that comes to RabbitTemplate is to be converted
        rabbitTemplate.setMessageConverter(producerJacksonConvertor());
        return rabbitTemplate;
    }//end of function

    //create a Binding Bean
    @Bean
    public Binding bindingUser(Queue firstRegisterQueue, DirectExchange exchange){
        //binding builder from amqp.core
        //with() is for defining routing key whereas the user_routing is the routing key and during consuming we need to use this routing key
        //binding Queue with exchange
        return BindingBuilder.bind(firstRegisterQueue()).to(exchange).with("user_routing");
    }//end of function
}//end of the class
