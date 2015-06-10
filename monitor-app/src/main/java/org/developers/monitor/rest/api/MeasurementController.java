package org.developers.monitor.rest.api;

import org.developers.monitor.measurement.provider.MeasurementProvider;
import org.developers.monitor.rest.dto.ComplexMeasurementDTO;
import org.developers.monitor.rest.dto.MeasurementDTO;
import org.developers.monitor.rest.support.RestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
                                                @PathVariable(value = "type") MeasurementDTO.Type measuremenType) {
        return measurementProvider.getMeasurementsByType(hostId, measuremenType);
    }
    
    @RequestMapping(value = "/{type}/{measurementid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public MeasurementDTO getHost(@PathVariable("measurementid") int measurementId,
                               @PathVariable(value = "type") MeasurementDTO.Type measuremenType){
        return measurementProvider.getMeasurementByTypeAndId(measuremenType, measurementId);
    }  

}
