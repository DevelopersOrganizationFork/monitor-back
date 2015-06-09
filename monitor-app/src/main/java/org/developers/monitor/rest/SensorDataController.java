package org.developers.monitor.rest;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
@RestController
@RequestMapping("/rest/sensordata")
public class SensorDataController {
    private static final String  NUMBER_OF_ITEMS = "NumberOfItems";
    private MeasurementData measurementData = null;
    private Host host = new Host();
    private Memory memory = new Memory();
    private Cpu cpu = new Cpu();
    private Disk disk = new Disk();
    private Network network = new Network();
    @Autowired
    private UniversalService universalService;
    private final JMSConnection jmsConnnection = new JMSConnection();
    final int numberOfItems = Integer.parseInt(ConfigLoader.properties.getProperty(NUMBER_OF_ITEMS));

    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JsonNode> getSensorData() throws Exception {
        List<JsonNode> result = new ArrayList<>();
        for (int i=0; i<10; i++) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(jmsConnnection.getMessage());
            System.out.println(actualObj.toString());
            result.add(actualObj);
        }
        return result;
    }

    @Scheduled(fixedRate = 15000) // 15s
    public void getDataFromMq() throws IOException, JMSException{
        for (int i = 0; i < 1; i++) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jmsConnnection.getMessage());
            
            JsonNode hostNode = root.path("host");
            host.setHostName(hostNode.path("hostname").asText());
            host.setHostIP(hostNode.path("ip").asText());
            
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
            
//            network.setNetworkDownload(BigInteger.valueOf(500));
            network.setNetworkIP(networkNode.path("ip").asText());
            network.setNetworkMAC(networkNode.path("mac").asText());
//            network.setNetworkUpload(networkNode.path("write").bigIntegerValue());
            
            MeasurementData ms = new MeasurementData(host, cpu, disk, memory, network);
            Integer id = universalService.insertMeasurementData(ms);
            System.out.println("Id measurementu = " + id);
        }
    }
}
