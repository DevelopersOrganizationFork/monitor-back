package org.developers.monitor.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sebastian.alberski on 2015-06-10.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplexMeasurementInfo {
    public enum UnitType {
        SECOND
    }
    
    public String userName;
    public UnitType unitType = UnitType.SECOND;
    public int interval;
    public int timeRange;
    public MeasurementDTO.Type measurementType;
}
