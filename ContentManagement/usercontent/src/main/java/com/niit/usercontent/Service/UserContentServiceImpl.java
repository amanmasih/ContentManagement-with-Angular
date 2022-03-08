package com.niit.usercontent.Service;

import com.niit.usercontent.Config.ProducerUser;
import com.niit.usercontent.Domain.Content;
import com.niit.usercontent.Domain.User;
import com.niit.usercontent.Exception.UserAlreadyExist;
import com.niit.usercontent.Exception.UserNotFound;
import com.niit.usercontent.Repository.UserContentRepository;
import com.niit.usercontent.rabbitmq.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserContentServiceImpl implements UserContentService{
    UserContentRepository userContentRepository;
    private ProducerUser producerUser;

    @Autowired
    public UserContentServiceImpl(UserContentRepository userContentRepository, ProducerUser producerUser) {
        this.userContentRepository = userContentRepository;
        this.producerUser = producerUser;
    }


    @Override
    public User registerUser(User user) throws UserAlreadyExist {
        UserDTO userDTO =new UserDTO(user.getUserId(),user.getUserName(),user.getPassword());
        if(userContentRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExist();
        }
        producerUser.sendMessage(userDTO);
        return userContentRepository.save(user);
    }

    @Override
    public User saveContent(String userId, Content contents) throws UserNotFound {
        if(userContentRepository.findById(userId).isEmpty()){
            throw new UserNotFound();
        }
        User user=userContentRepository.findById(userId).get();
        if(user.getContentList()==null){
            user.setContentList(Arrays.asList(contents));
        }
        else{
            List<Content> contentList =user.getContentList();
            contentList.add(contents);
            user.setContentList(contentList);
        }

        return userContentRepository.save(user);
    }

    //delete the user content
    @Override
    public User deleteUserContent(String userId, int contentId) throws Exception {
        if(userContentRepository.findById(userId).isEmpty()) {
            throw new UserNotFound();
        }
        User user=userContentRepository.findById(userId).get();
        List<Content> contentList=user.getContentList();
        if(contentList.removeIf(p->p.getContentId()==contentId)){
            System.out.println(contentList);
            user.setContentList(contentList);
        }
        else {
            throw new Exception();
        }
        return userContentRepository.save(user);
    }

    //get all the user Contents

    @Override
    public User getAllUserContent(String userId) throws UserNotFound {
        if (userContentRepository.findById(userId).isEmpty()){
            throw new UserNotFound();
        }
        return userContentRepository.findById(userId).get();
    }
}
