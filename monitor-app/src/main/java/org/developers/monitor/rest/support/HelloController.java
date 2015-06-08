package org.developers.monitor.rest.support;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        /*
            --> Do usunięcia - wrzucone w celu pokazania, że działa i jak tego używac
        */
        Host host = new Host();
        host.setHostIP("83.80.12.1");
        host.setHostName("Temporary");
        host.setHostId("MAC:12:202:214:23");
        
        Network network = new Network();
        network.setNetworkDownload(BigInteger.valueOf(500));
        network.setNetworkIP("83.80.12.12");
        network.setNetworkMAC("MAC:12:202:214:23");
        network.setNetworkUpload(BigInteger.ZERO);
        
        Cpu cpu = new Cpu();
        cpu.setCPUIdle(BigInteger.ZERO);
        cpu.setCPUIrq(BigInteger.ZERO);
        cpu.setCPUNice(BigInteger.ONE);
        cpu.setCPUSoftIrq(BigInteger.ZERO);
        cpu.setCPUStolen(BigInteger.ZERO);
        cpu.setCPUSys(BigInteger.ZERO);
        cpu.setCPUTotal(BigInteger.TEN);
        cpu.setCPUUser(BigInteger.ZERO);
        cpu.setCPUWait(BigInteger.ZERO);
        
        Disk disk = new Disk();
        disk.setDiskRead(BigInteger.TEN);
        disk.setDiskWrite(BigInteger.TEN);
        
        Memory memory = new Memory();
        memory.setMemoryActualUsed(BigInteger.ZERO);
        memory.setMemoryRAM(2048);
        memory.setMemoryTotal(BigInteger.TEN);
        memory.setMemoryUsed(BigInteger.ZERO);
        
        MeasurementData ms = new MeasurementData(host,cpu,disk,memory,network);
        
        Integer msid = us.insertMeasurementData(ms);
        
        
        
        
        
        Users user = new Users("testUser", "testUser");
        Integer id = -1;
        try {
            id = us.insertUser(user);
        } catch (UserAlreadyExistException ex) {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new HelloMessage("Hello! Monitor is running! Dodalem measurement pod id: " + msid);
    }
    
    
}
