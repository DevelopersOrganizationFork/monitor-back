package org.developers.monitor.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sebastian.alberski on 2015-06-10.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeasurementInfo {
    public String name;
    public MeasurementDTO.Type type;
    
    public MeasurementInfo(){}
    
    public MeasurementInfo(String name, MeasurementDTO.Type type) {
        this.name = name;
        this.type = type;
    }
}
