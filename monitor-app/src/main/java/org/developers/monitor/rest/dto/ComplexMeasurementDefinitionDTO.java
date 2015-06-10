package org.developers.monitor.rest.dto;

/**
 * Created by reynev on 6/10/15.
 */
public class ComplexMeasurementDefinitionDTO {

    public String measurementType;
    public Integer userId;
    public Integer timeRange;
    public Integer interval;
    public String unitType; // [SECOND, MINUTE, HOUR]
    public Integer id;
    public String hostId;
}
