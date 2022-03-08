package com.niit.apigateway.config;

import com.niit.apigateway.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    //RouteLocator is used to obtain route Information, it takes a parameter of RouteLocatorBuilder
    //RouteLocatorBuilder is used to create routing routes
    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder routeLocatorBuilder) {

        //after assigning all the routes of the microservices the build function is to executed
        //route must have the path and uri
        //refer the controller 1- userservice and route for authenticationservice

        //Move you Bean and filter for authentication from Usercontent to here
        //api gateway available at 9000
        //v1/->usercontent(8090), v2->authenticationservice(9090)
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/api/v1/**").uri("lb://user-content-service"))
                .route(p -> p.path("/api/v2/**").uri("lb://authentication-service")
                )
                .build();

    }//end of the function

    //Bean for Jwt Filter
    @Bean
    FilterRegistrationBean jwtFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/v1/userdata/**");
        //filterRegistrationBean.addUrlPatterns("api/v3/track/**");
        return filterRegistrationBean;
    }

}//end of class