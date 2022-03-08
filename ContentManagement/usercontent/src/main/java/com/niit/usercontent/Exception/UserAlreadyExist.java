package com.niit.usercontent.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "User already Exists")
public class UserAlreadyExist extends Exception{
}
