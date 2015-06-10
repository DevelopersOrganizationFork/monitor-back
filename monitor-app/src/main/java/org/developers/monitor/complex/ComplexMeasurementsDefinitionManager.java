package org.developers.monitor.complex;

import org.developers.monitor.rest.dto.ComplexMeasurementDTO;
import org.developers.monitor.rest.dto.ComplexMeasurementDefinitionDTO;

import java.util.List;

/**
 * Created by reynev on 6/10/15.
 */
public interface ComplexMeasurementsDefinitionManager {

    public List<ComplexMeasurementDefinitionDTO> getComplexMeasurementsByHost(String hostId);
    public void removeComplexMeasurement(String name);
    public int createComplexMeasurement(ComplexMeasurementDefinitionDTO complexMeasurementDTO);
}
