package org.developers.monitor.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jms.JMSException;
import org.developers.monitor.persistence.Cpu;
import org.developers.monitor.persistence.Disk;
import org.developers.monitor.persistence.Host;
import org.developers.monitor.persistence.Memory;
import org.developers.monitor.persistence.Network;
import org.developers.monitor.persistence.service.MeasurementData;
import org.developers.monitor.persistence.service.UniversalService;
import org.developers.sensor.configuration.ConfigLoader;
import org.developers.sensor.jms.consumer.JMSConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Silwest
 */
@Component
public class SensorDataController {
    private static final String  NUMBER_OF_ITEMS = "NumberOfItems";
    @Autowired
    private UniversalService universalService;
    private static final JMSConnection jmsConnnection = new JMSConnection();
    final int numberOfItems = Integer.parseInt(ConfigLoader.properties.getProperty(NUMBER_OF_ITEMS));

    
//    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<JsonNode> getSensorData() throws Exception {
//        List<JsonNode> result = new ArrayList<>();
//        for (int i=0; i<10; i++) {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode actualObj = mapper.readTree(jmsConnnection.getMessage());
//            System.out.println(actualObj.toString());
//            result.add(actualObj);
//        }
//        return result;
//    }

    @Scheduled(fixedRate = 15000) // 15s
    public void getDataFromMq() throws IOException, JMSException{
        
        for (int i = 0; i < numberOfItems; i++) {
            Host host = new Host();
            Memory memory = new Memory();
            Cpu cpu = new Cpu();
            Disk disk = new Disk();
            Network network = new Network();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jmsConnnection.getMessage());
            if(!root.has("host"))
            {
                System.out.println("There is no data in activeMQ!");
                break;
            }
            JsonNode hostNode = root.path("host");
            String hostname = hostNode.path("hostname").asText();
            host.setHostName(hostname);
            host.setHostIP(hostNode.path("ip").asText());
            host.setHostId(hostname); // Co powinno byc wrzucane jako ID ?
            
            JsonNode measurNode = root.path("measurement").path("mem");
            memory.setMemoryRAM(measurNode.path("ram").asInt());
            memory.setMemoryTotal(measurNode.path("total").bigIntegerValue());
            memory.setMemoryUsed(measurNode.path("used").bigIntegerValue());
            memory.setMemoryActualUsed(measurNode.path("actualUsed").bigIntegerValue());
            
            JsonNode cpuNode = root.path("measurement").path("cpu");
            cpu.setCPUIdle(cpuNode.path("idle").bigIntegerValue());
            cpu.setCPUIrq(cpuNode.path("irq").bigIntegerValue());
            cpu.setCPUNice(cpuNode.path("nice").bigIntegerValue());
            cpu.setCPUSoftIrq(cpuNode.path("softIrq").bigIntegerValue());
            cpu.setCPUStolen(cpuNode.path("stolen").bigIntegerValue());
            cpu.setCPUSys(cpuNode.path("sys").bigIntegerValue());
            cpu.setCPUTotal(cpuNode.path("total").bigIntegerValue());
            cpu.setCPUUser(cpuNode.path("user").bigIntegerValue());
            cpu.setCPUWait(cpuNode.path("wait").bigIntegerValue());
            
            JsonNode diskNode = root.path("measurement").path("disk");
            disk.setDiskRead(diskNode.path("read").bigIntegerValue());
            disk.setDiskWrite(diskNode.path("write").bigIntegerValue());
            
            JsonNode networkNode = root.path("measurement").path("network");
            
            network.setNetworkIP(networkNode.path("ip").asText());
            network.setNetworkMAC(networkNode.path("mac").asText());
            
            JsonNode newtorkStatNode = root.path("measurement").path("network").path("stat");
            network.setNetworkDownload(newtorkStatNode.path("download").bigIntegerValue());
            network.setNetworkUpload(newtorkStatNode.path("upload").bigIntegerValue());
            
            long miliSec = root.path("date").asLong();
            Date date = new Date(miliSec);
            
            MeasurementData ms = new MeasurementData(host, cpu, disk, memory, network, date);

            Integer id = universalService.insertMeasurementData(ms);
            System.out.println("Id measurementu = " + id);
        }
    }
}
