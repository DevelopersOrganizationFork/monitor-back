package org.developers.monitor.measurement.provider;

import org.developers.monitor.rest.dto.MeasurementDTO;

import java.util.List;

/**
 * Created by reynev on 5/4/15.
 */
public interface MeasurementProvider {

    List<MeasurementDTO> getAllMeasurements(int hostId);
    List<MeasurementDTO> getMeasurementsByType(int hostId, MeasurementDTO.Type type);
    MeasurementDTO getMeasurementByTypeAndId(MeasurementDTO.Type measuremenType, int measurementId);
}
