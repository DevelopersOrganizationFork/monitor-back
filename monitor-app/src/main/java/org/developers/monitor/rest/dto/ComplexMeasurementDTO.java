package org.developers.monitor.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sebastian.alberski on 2015-05-04.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplexMeasurementDTO extends MeasurementDTO {
    
    public String description;
    
}
