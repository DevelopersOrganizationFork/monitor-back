package org.developers.monitor.rest.api;

import org.developers.monitor.measurement.provider.MeasurementProvider;
import org.developers.monitor.rest.converter.DateConverter;
import org.developers.monitor.rest.dto.ComplexMeasurementDTO;
import org.developers.monitor.rest.dto.MeasurementDTO;
import org.developers.monitor.rest.dto.MeasurementInfo;
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
    public List<MeasurementInfo> getMeasurements(@PathVariable(value = "hostid") String hostId){
        return measurementProvider.getListOfMeasurements(hostId);
    }
    
    @RequestMapping(value = "/{measurementName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<MeasurementDTO> getMeasurements(@PathVariable(value = "hostid") String hostId,
                                                @PathVariable(value = "measurementName") String measurementName,
                                                @RequestParam(value = "lastCount", required = false) Integer lastCount,
                                                @RequestParam(value = "fromDate", required = false) String fromDateValue,
                                                @RequestParam(value = "toDate", required = false) String toDateValue
                                                ) {
        
        List<MeasurementDTO> result = new ArrayList<>();
        
        if ("CPU".equals(measurementName)
                || "MEMORY".equals(measurementName)
                || "NETWORKUP".equals(measurementName)
                || "NETWORKDOWN".equals(measurementName)
                ) {
                result = provideFilteredMeasurements(hostId, measurementName, lastCount, fromDateValue, toDateValue);
        } else {
            //try to get complex measurement with given {measurementName}
        }
        
        return result;
    }
    
    @RequestMapping(value = "/{type}/{measurementid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public MeasurementDTO getHost(@PathVariable("measurementid") int measurementId,
                               @PathVariable(value = "type") MeasurementDTO.Type measuremenType){
        return measurementProvider.getMeasurementByTypeAndId(measuremenType, measurementId);
    }
    
    private List<MeasurementDTO> provideFilteredMeasurements(
            String hostId, 
            String measuremenName, 
            Integer lastCount, 
            String fromDateValue, 
            String toDateValue) {
        
        List <MeasurementDTO> measurements = measurementProvider.getMeasurementsByType(hostId, MeasurementDTO.Type.valueOf(measuremenName));
        
        boolean isLastCountActive = false;
        
        if (lastCount != null) {
            isLastCountActive = true;
        }
    
    
        if (!StringUtils.isEmpty(fromDateValue)) {
            Date fromDate = DateConverter.convertStringToDate(fromDateValue);
            measurements = measurements
                   .stream()
                   .filter(m -> m.date.after(fromDate))
                    .collect(Collectors.toList());
        }
        if (!StringUtils.isEmpty(fromDateValue)) {
            Date toDate = DateConverter.convertStringToDate(toDateValue);
            measurements = measurements
                    .stream()
                    .filter(m -> m.date.before(toDate))
                    .collect(Collectors.toList());
        }
    
        measurements =  measurements
                .stream()
                .sorted((m1, m2) -> Long.signum(m2.date.getTime() - m1.date.getTime()))
                .limit(isLastCountActive ? lastCount : 10)
        .collect(Collectors.toList());
        
         return measurements;
    }

}
