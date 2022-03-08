package com.niit.usercontent.Contoller;

import com.niit.usercontent.Domain.Content;
import com.niit.usercontent.Domain.User;
import com.niit.usercontent.Exception.UserAlreadyExist;
import com.niit.usercontent.Exception.UserNotFound;
import com.niit.usercontent.Service.UserContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserContentController {
UserContentServiceImpl userContentService;

    @Autowired
    public UserContentController(UserContentServiceImpl userContentService) {
        this.userContentService = userContentService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExist {
        try {
            return new ResponseEntity<>(userContentService.registerUser(user), HttpStatus.CREATED);
        }
        catch (UserAlreadyExist e) {
            throw new UserAlreadyExist();
        }
        catch (Exception e){
            return new ResponseEntity<>("retry after sometime",HttpStatus.CONFLICT);
        }
    }


    @PostMapping("userdata/saveUserContent/{userId}")
    public ResponseEntity<?> saveContent(@RequestBody Content content, @PathVariable String userId) throws UserNotFound {
        try {
            return new ResponseEntity<>(userContentService.saveContent(userId,content),HttpStatus.OK);
        }
        catch (UserNotFound e){
            throw new UserNotFound();
        }
        catch (Exception ex){
            return new ResponseEntity<>("retry after sometime",HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("userdata/getAllContents/{userId}")
    public ResponseEntity<?> getAllContents(@PathVariable String userId) throws UserNotFound {
        try {
            return new ResponseEntity<>(userContentService.getAllUserContent(userId),HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        catch (Exception e){
            return new ResponseEntity<>("retry after sometime",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("userdata/deleteContent/{userId}/{trackId}")
    public ResponseEntity<?> deleteUserContent(@PathVariable String userId,@PathVariable int contentId) throws UserNotFound {
        try {
            return new ResponseEntity<>(userContentService.deleteUserContent(userId,contentId),HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        catch (Exception e){
            return new ResponseEntity<>("retry after some time",HttpStatus.NOT_FOUND);
        }
    }
}//end of class
