package org.developers.monitor.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HelloMessage {
    public String id;
    public String message;
    public String datetime;
    
    public HelloMessage(String message) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.datetime = LocalDate.now()+" "+LocalTime.now();
    }
}
