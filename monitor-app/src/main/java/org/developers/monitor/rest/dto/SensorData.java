/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.UUID;

/**
 *
 * @author Silwest
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensorData {

    public String id;
    public String message;

    public SensorData(String message) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
    }
}
