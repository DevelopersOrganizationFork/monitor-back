package org.developers.monitor.rest.api;

import org.developers.monitor.persistence.Users;
import org.developers.monitor.persistence.service.UniversalService;
import org.developers.monitor.persistence.service.exceptions.UserAlreadyExistException;
import org.developers.monitor.persistence.service.exceptions.UserNotFoundException;
import org.developers.monitor.rest.authorization.Authorization;
import org.developers.monitor.rest.dto.UserDto;
import org.developers.monitor.rest.support.RestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * Created by sebastian.alberski on 2015-05-04.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    
    public enum StatusInfo {
        REGISTATION_OK,
        REGISTRATION_FAILED,
        LOGIN_OK,
        LOGIN_FAILED;
    }
    
    @Autowired
    private UniversalService us;
    
    @RequestMapping(value = "/register", 
            method = RequestMethod.POST, 
            produces = {MediaType.APPLICATION_JSON_VALUE}, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StatusInfo register(@RequestBody UserDto user) {
        Users users = new Users();
        users.setUserName(user.login);
        users.setUserPassword(user.password);
        Integer newUserId = -1;
        try {
             newUserId = us.insertUser(users);
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
        }
        return newUserId > 0 
                ? StatusInfo.REGISTATION_OK 
                : StatusInfo.REGISTRATION_FAILED;
    }
    
    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StatusInfo login(@RequestBody UserDto user) {
        Users users = null;
        try {
            users = us.getUserByUserName(user.login);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        
        return users != null && new Authorization(user, users).verify() 
                ? StatusInfo.LOGIN_OK 
                : StatusInfo.LOGIN_FAILED;
    }
    
    
}
