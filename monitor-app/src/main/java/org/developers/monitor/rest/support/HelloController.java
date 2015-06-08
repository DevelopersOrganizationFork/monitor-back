package org.developers.monitor.rest.support;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.developers.monitor.persistence.Users;
import org.developers.monitor.persistence.service.UniversalService;
import org.developers.monitor.persistence.service.exceptions.UserAlreadyExistException;
import org.developers.monitor.persistence.service.exceptions.UserNotFoundException;
import org.developers.monitor.rest.dto.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * First testing component says hello.
 * If it works properly it means application starts without errors.
 */



@RestController
@RequestMapping("/rest/hello")
public class HelloController {
//    @Autowired
//    private UserDao userDao;
    
    @Autowired
    private UniversalService us;
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HelloMessage hello() {
        
        /*
            --> Do usunięcia - wrzucone w celu pokazania, że działa i jak tego używac
        */
        Users user = new Users("testUser", "testUser");
        Integer id = -1;
        try {
            id = us.insertUser(user);
        } catch (UserAlreadyExistException ex) {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new HelloMessage("Hello! Monitor is running! Addes user with id: " + id + " and username: " + user.getUserName());
    }
    
    
}
