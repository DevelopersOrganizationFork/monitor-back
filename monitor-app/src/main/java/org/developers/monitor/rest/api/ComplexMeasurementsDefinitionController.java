package org.developers.monitor.rest.api;

import org.developers.monitor.complex.ComplexMeasurementsDefinitionManager;
import org.developers.monitor.rest.dto.ComplexMeasurementDefinitionDTO;
import org.developers.monitor.rest.support.RestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by reynev on 6/10/15.
 */
@RestController
@RequestMapping(RestConfig.HOSTS_ID_MEASUREMENTS_PATH)
public class ComplexMeasurementsDefinitionController {

    @Autowired
    private ComplexMeasurementsDefinitionManager complexMeasurementsDefinitionManager;

    /*@RequestMapping(value = "/{measurementid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ComplexMeasurementDefinitionDTO getComplexMeasurement(@PathVariable(value = "hostid") String hostId,
                                                                 @PathVariable(value = "measurementid") Integer measurementId) {
        return complexMeasurementsDefinitionManager.getComplexMeasurementById(measurementId);
    }*/

    @RequestMapping(value = "/complex", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ComplexMeasurementDefinitionDTO> getComplexMeasurements(@PathVariable(value = "hostid") String hostId) {
        return complexMeasurementsDefinitionManager.getComplexMeasurementsByHost(hostId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createComplexMeasurement(@PathVariable(value = "hostid") String hostId, @RequestBody ComplexMeasurementDefinitionDTO complexMeasurementDefinitionDTO) {
        String defId = complexMeasurementsDefinitionManager.createComplexMeasurement(complexMeasurementDefinitionDTO, hostId) + "";
        return new ResponseEntity<String>(defId, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{measurementid}", method = RequestMethod.DELETE, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> removeComplexMeasurement(@PathVariable(value = "hostid") String hostId,
                                                                 @PathVariable(value = "measurementName") String measurementName){
        complexMeasurementsDefinitionManager.removeComplexMeasurement(measurementName);
        return new ResponseEntity<String>( HttpStatus.NO_CONTENT);
    }
}
