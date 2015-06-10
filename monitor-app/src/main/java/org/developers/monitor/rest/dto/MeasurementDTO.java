package org.developers.monitor.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by sebastian.alberski on 2015-05-04.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeasurementDTO {
    public enum Type {
        CPU,
        MEMORY,
        NETWORKUP,
        NETWORKDOWN
    }
    
    public Date date;
    public Type type;
    public double value;
    public int id;
    public boolean isComplex = false;
    public String hostId = "SYSTEM";
  
}
