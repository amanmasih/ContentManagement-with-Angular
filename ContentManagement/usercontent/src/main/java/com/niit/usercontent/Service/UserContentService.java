package com.niit.usercontent.Service;

import com.niit.usercontent.Domain.Content;
import com.niit.usercontent.Domain.User;
import com.niit.usercontent.Exception.UserAlreadyExist;
import com.niit.usercontent.Exception.UserNotFound;

public interface UserContentService {
    User registerUser(User user) throws UserAlreadyExist;
    User saveContent(String userId, Content contents) throws UserNotFound;
    User deleteUserContent(String userId,int contentId) throws Exception;
    User getAllUserContent(String userId) throws UserNotFound;
}
