package org.developers.monitor.rest.support;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.developers.monitor.persistence.ComplexMeasurement;
import org.developers.monitor.persistence.Cpu;
import org.developers.monitor.persistence.Disk;
import org.developers.monitor.persistence.Host;
import org.developers.monitor.persistence.Memory;
import org.developers.monitor.persistence.Network;
import org.developers.monitor.persistence.Users;
import org.developers.monitor.persistence.service.MeasurementData;
import org.developers.monitor.persistence.service.UniversalService;
import org.developers.monitor.persistence.service.exceptions.UserAlreadyExistException;
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
        Users users = new Users();
        users.setUserName("admin");
        users.setUserPassword("admin");
        try {
            us.insertUser(users);
        } catch (UserAlreadyExistException e) {
            throw new RuntimeException(e);
        }
        
        /*
            --> Do usunięcia - wrzucone w celu pokazania, że działa i jak tego używac
        */
        ComplexMeasurement cm = new ComplexMeasurement();
        cm.setMeasurementInterval(2);
        cm.setMeasurementType("CPU");
        cm.setTimeRange(16);
        cm.setUnitType("SECOND");
        cm.setUsersidUser(us.getAllUsers().get(0).getIdUser());
        
        Integer cmid = us.insertComplexMeasurement(cm);

        return new HelloMessage("Hello! Monitor is running! Dodalem ComplexMeasurement pod id: " + cmid);
    }
    
    
}
