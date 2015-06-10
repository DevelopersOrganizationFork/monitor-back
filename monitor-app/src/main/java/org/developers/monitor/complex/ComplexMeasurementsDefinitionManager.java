package org.developers.monitor.complex;

import org.developers.monitor.rest.dto.ComplexMeasurementDTO;
import org.developers.monitor.rest.dto.ComplexMeasurementDefinitionDTO;

/**
 * Created by reynev on 6/10/15.
 */
public interface ComplexMeasurementsDefinitionManager {

    public ComplexMeasurementDefinitionDTO getComplexMeasurementByHost(String hostId);
    public ComplexMeasurementDefinitionDTO getComplexMeasurementById(int definitionId);
    public void removeComplexMeasurement(int id);
    public int createComplexMeasurement(ComplexMeasurementDefinitionDTO complexMeasurementDTO);
}
