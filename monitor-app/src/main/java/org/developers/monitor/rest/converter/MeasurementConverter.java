package org.developers.monitor.rest.converter;

import org.developers.monitor.persistence.Measurement;
import org.developers.monitor.rest.dto.MeasurementDTO;

import java.util.List;

/**
 * Created by reynev on 5/4/15.
 */
public interface MeasurementConverter {
    List<MeasurementDTO> convertToDTO(Measurement measurement);
    MeasurementDTO convertToDTO(Measurement measurement, MeasurementDTO.Type measurementType);
}
