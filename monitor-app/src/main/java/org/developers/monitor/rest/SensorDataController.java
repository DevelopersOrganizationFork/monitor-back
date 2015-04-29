/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.rest;

import java.util.Iterator;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.developers.monitor.rest.dto.SensorData;
import org.developers.sensor.jms.consumer.JMSConnection;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
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
    public SensorData getSensorData() throws JMSException, JSONException {
        TextMessage message = jmsConnnection.getMessage();
        JSONObject json = new JSONObject(message.getText());
        System.out.println();
        Iterator<?> keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            System.out.println(key + " = " + json.get(key));
        }
        return new SensorData("It is like it is.");
    }
}
