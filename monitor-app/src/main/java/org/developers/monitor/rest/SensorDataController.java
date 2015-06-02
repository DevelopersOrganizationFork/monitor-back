package org.developers.monitor.rest;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.jms.JMSException;
import org.developers.sensor.configuration.ConfigLoader;
import org.developers.sensor.jms.consumer.JMSConnection;
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
        List<JsonNode> result = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(jmsConnnection.getMessage());
            result.add(actualObj);
        }
        System.out.println(result.toString());
    }
}
