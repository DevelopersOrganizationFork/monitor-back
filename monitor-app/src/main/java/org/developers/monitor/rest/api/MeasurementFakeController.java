package org.developers.monitor.rest.api;

import org.developers.monitor.measurement.provider.MeasurementProvider;
import org.developers.monitor.rest.dto.ComplexMeasurementDTO;
import org.developers.monitor.rest.dto.MeasurementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by sebastian.alberski on 2015-05-04.
 */
@RestController
@RequestMapping("/fake/hosts/{hostid}/measurements")
public class MeasurementFakeController {
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<MeasurementDTO> getMeasurements(@PathVariable(value = "hostid") int hostId){
        return new ArrayList<MeasurementDTO>() {{
            addAll(fakeMeasurementsMappedByType.get(MeasurementDTO.Type.CPU));
            addAll(fakeMeasurementsMappedByType.get(MeasurementDTO.Type.MEMORY));
            addAll(fakeMeasurementsMappedByType.get(MeasurementDTO.Type.NETWORKUP));
            addAll(fakeMeasurementsMappedByType.get(MeasurementDTO.Type.NETWORKDOWN));
        }};
    }
    
    @RequestMapping(value = "/{type}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<MeasurementDTO> getMeasurements(@PathVariable(value = "hostid") int hostId,
                                                @PathVariable(value = "type") MeasurementDTO.Type measuremenType) {
        return fakeMeasurementsMappedByType.get(measuremenType);
    }
    
    @RequestMapping(value = "/{type}/{measurementid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public MeasurementDTO getHost(@PathVariable("measurementid") int measurementId,
                               @PathVariable(value = "type") MeasurementDTO.Type measuremenType){
        return fakeMeasurementsMappedByType.get(measuremenType).get(measurementId);
    }
    
    
    private List<MeasurementDTO> fakeCPUMeasurements = Arrays.asList(
            new MeasurementDTO() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.CPU; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.CPU; description="complex memory"; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.CPU; description="complex cpu"; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.CPU; description="complex network"; value=(int)(Math.random()*100);}}
    );
    
    private List<MeasurementDTO> fakeMEMMeasurements = Arrays.asList(
            new MeasurementDTO() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.MEMORY; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.MEMORY; description="complex memory"; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.MEMORY; description="complex cpu"; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.MEMORY; description="complex network"; value=(int)(Math.random()*100);}}
    );
    
    private List<MeasurementDTO> fakeNETUpMeasurements = Arrays.asList(
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKUP; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKUP; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKUP; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKUP; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKUP; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKUP; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.NETWORKUP; description="complex memory"; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.NETWORKUP; description="complex cpu"; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.NETWORKUP; description="complex network"; value=(int)(Math.random()*100);}}
    );

    private List<MeasurementDTO> fakeNETDownMeasurements = Arrays.asList(
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKDOWN; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKDOWN; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKDOWN; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKDOWN; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKDOWN; value=(int)(Math.random()*100);}},
            new MeasurementDTO() {{date=new Date(); type=Type.NETWORKDOWN; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.NETWORKDOWN; description="complex memory"; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.NETWORKDOWN; description="complex cpu"; value=(int)(Math.random()*100);}},
            new ComplexMeasurementDTO() {{date=new Date(); type=Type.NETWORKDOWN; description="complex network"; value=(int)(Math.random()*100);}}
    );
    
    private Map<MeasurementDTO.Type, List<MeasurementDTO>> fakeMeasurementsMappedByType = new HashMap<MeasurementDTO.Type, List<MeasurementDTO>>() {{
        put(MeasurementDTO.Type.CPU, fakeCPUMeasurements);
        put(MeasurementDTO.Type.MEMORY, fakeMEMMeasurements);
        put(MeasurementDTO.Type.NETWORKUP, fakeNETUpMeasurements);
        put(MeasurementDTO.Type.NETWORKDOWN, fakeNETDownMeasurements);
    }};
}
