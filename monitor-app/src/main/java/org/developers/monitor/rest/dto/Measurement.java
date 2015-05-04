package org.developers.monitor.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by sebastian.alberski on 2015-05-04.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Measurement {
    public enum Type {
        CPU,
        MEMORY,
        NETWORK
    }
    
    public Date date;
    public Type type;
    public int value;
  
}
