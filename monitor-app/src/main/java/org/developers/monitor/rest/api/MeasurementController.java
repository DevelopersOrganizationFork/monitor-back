package org.developers.monitor.rest.api;

import org.developers.monitor.rest.dto.ComplexMeasurement;
import org.developers.monitor.rest.dto.Measurement;
import org.developers.monitor.rest.support.RestConfig;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by sebastian.alberski on 2015-05-04.
 */
@RestController
@RequestMapping(RestConfig.HOSTS_ID_MEASUREMENTS_PATH)
public class MeasurementController {
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Measurement> getMeasurements(){
        return new ArrayList<Measurement>() {{
            addAll(fakeMeasurementsMappedByType.get(Measurement.Type.CPU));
            addAll(fakeMeasurementsMappedByType.get(Measurement.Type.MEMORY));
            addAll(fakeMeasurementsMappedByType.get(Measurement.Type.NETWORK));
        }};
    }
    
    @RequestMapping(value = "/{type}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Measurement> getMeasurements(@PathVariable(value = "type") Measurement.Type type) {
        return fakeMeasurementsMappedByType.get(type);
    }
    
    @RequestMapping(value = "/{type}/{measurementid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Measurement getHost(@PathVariable("measurementid") int measurementId,
                               @PathVariable(value = "type") Measurement.Type type){
        return fakeMeasurementsMappedByType.get(type).get(measurementId);
    }
    
    
    private List<Measurement> fakeCPUMeasurements = Arrays.asList(
            new Measurement() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new ComplexMeasurement() {{date=new Date(); type=Type.CPU; description="complex memory"; value=(int)(Math.random()*100);}},
            new ComplexMeasurement() {{date=new Date(); type=Type.CPU; description="complex cpu"; value=(int)(Math.random()*100);}},
            new ComplexMeasurement() {{date=new Date(); type=Type.CPU; description="complex network"; value=(int)(Math.random()*100);}}
    );
    
    private List<Measurement> fakeMEMMeasurements = Arrays.asList(
            new Measurement() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new ComplexMeasurement() {{date=new Date(); type=Type.MEMORY; description="complex memory"; value=(int)(Math.random()*100);}},
            new ComplexMeasurement() {{date=new Date(); type=Type.MEMORY; description="complex cpu"; value=(int)(Math.random()*100);}},
            new ComplexMeasurement() {{date=new Date(); type=Type.MEMORY; description="complex network"; value=(int)(Math.random()*100);}}
    );
    
    private List<Measurement> fakeNETMeasurements = Arrays.asList(
            new Measurement() {{date=new Date(); type=Type.NETWORK; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.NETWORK; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.NETWORK; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.NETWORK; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.NETWORK; value=(int)(Math.random()*100);}},
            new Measurement() {{date=new Date(); type=Type.NETWORK; value=(int)(Math.random()*100);}},
            new ComplexMeasurement() {{date=new Date(); type=Type.NETWORK; description="complex memory"; value=(int)(Math.random()*100);}},
            new ComplexMeasurement() {{date=new Date(); type=Type.NETWORK; description="complex cpu"; value=(int)(Math.random()*100);}},
            new ComplexMeasurement() {{date=new Date(); type=Type.NETWORK; description="complex network"; value=(int)(Math.random()*100);}}
    );
    
    private Map<Measurement.Type, List<Measurement>> fakeMeasurementsMappedByType = new HashMap<Measurement.Type, List<Measurement>>() {{
        put(Measurement.Type.CPU, fakeCPUMeasurements);
        put(Measurement.Type.MEMORY, fakeMEMMeasurements);
        put(Measurement.Type.NETWORK, fakeNETMeasurements);
    }};
}
