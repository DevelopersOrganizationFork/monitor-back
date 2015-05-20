package org.developers.monitor.rest;

import java.util.ArrayList;
import java.util.List;
import javax.jms.TextMessage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.developers.sensor.jms.consumer.JMSConnection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Silwest
 */
@RestController
@RequestMapping("/rest/sensordata")
public class SensorDataController {

    private JMSConnection jmsConnnection = new JMSConnection();

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JsonNode> getSensorData() throws Exception {
        List<JsonNode> result = new ArrayList<>();
        for (int i=0; i<10; i++) {
            TextMessage message = jmsConnnection.getMessage();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(message.getText());
            System.out.println(actualObj.toString());
            result.add(actualObj);
        }
        return result;
    }
}
