package org.developers.monitor.rest.api;

import org.developers.monitor.measurement.provider.MeasurementProvider;
import org.developers.monitor.rest.converter.DateConverter;
import org.developers.monitor.rest.dto.ComplexMeasurementDTO;
import org.developers.monitor.rest.dto.MeasurementDTO;
import org.developers.monitor.rest.support.RestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastian.alberski on 2015-05-04.
 */
@RestController
@RequestMapping(RestConfig.HOSTS_ID_MEASUREMENTS_PATH)
public class MeasurementController {

    @Autowired
    MeasurementProvider measurementProvider;
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<MeasurementDTO> getMeasurements(@PathVariable(value = "hostid") String hostId){
        return measurementProvider.getAllMeasurements(hostId);
    }
    
    @RequestMapping(value = "/{type}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<MeasurementDTO> getMeasurements(@PathVariable(value = "hostid") String hostId,
                                                @PathVariable(value = "type") MeasurementDTO.Type measuremenType,
                                                @RequestParam(value = "lastCount", required = false) Integer lastCount,
                                                @RequestParam(value = "fromDate", required = false) String fromDateValue,
                                                @RequestParam(value = "toDate", required = false) String toDateValue
                                                ) {
        
        return provideFilteredMeasurements(hostId, measuremenType, lastCount, fromDateValue, toDateValue);
    }
    
    @RequestMapping(value = "/{type}/{measurementid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public MeasurementDTO getHost(@PathVariable("measurementid") int measurementId,
                               @PathVariable(value = "type") MeasurementDTO.Type measuremenType){
        return measurementProvider.getMeasurementByTypeAndId(measuremenType, measurementId);
    }
    
    private List<MeasurementDTO> provideFilteredMeasurements(
            String hostId, 
            MeasurementDTO.Type measuremenType, 
            Integer lastCount, 
            String fromDateValue, 
            String toDateValue) {
        
        List <MeasurementDTO> measurements = measurementProvider.getMeasurementsByType(hostId, measuremenType);
        
        boolean isLastCoundActive = false;
        boolean isFromDateActive = false;
        boolean isToDateActive = false;
        
        if (lastCount != null) isLastCoundActive = true;
        if (!StringUtils.isEmpty(fromDateValue)) {
            isFromDateActive = true;
            Date fromDate = DateConverter.convertStringToDate(fromDateValue);
        }
        if (!StringUtils.isEmpty(fromDateValue)) {
            isToDateActive = true;
            Date toDate = DateConverter.convertStringToDate(toDateValue);
        }
    
        return measurements
                .stream()
                .sorted((m1, m2) -> Long.signum(m2.date.getTime() - m1.date.getTime()))
                .limit(isLastCoundActive ? lastCount : 10)
        .collect(Collectors.toList());
    }

}
