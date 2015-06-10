package org.developers.monitor.measurement.provider;

import org.developers.monitor.rest.dto.MeasurementDTO;
import org.developers.monitor.rest.dto.MeasurementInfo;

import java.util.Collection;
import java.util.List;

/**
 * Created by reynev on 5/4/15.
 */
public interface MeasurementProvider {

    List<MeasurementDTO> getAllMeasurements();
    List<MeasurementDTO> getAllMeasurements(String hostId);
    List<MeasurementDTO> getMeasurementsByType(String hostId, MeasurementDTO.Type type);
//    MeasurementDTO getMeasurementByTypeAndId(MeasurementDTO.Type measuremenType, int measurementId);
    List<MeasurementInfo> getListOfMeasurements(String hostId);

    List<MeasurementDTO> getComplexMeasurements(String hostId, String measurementName);
}
