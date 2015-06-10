package org.developers.monitor.measurement.provider;

import org.developers.monitor.rest.converter.MeasurementConverter;
import org.developers.monitor.persistence.DAO.MeasurementDao;
import org.developers.monitor.persistence.Measurement;
import org.developers.monitor.rest.dto.MeasurementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reynev on 5/4/15.
 */
@Component
public class MeasurementProviderImpl implements MeasurementProvider {
   
    @Autowired
    private MeasurementConverter measurementConverter;
    
    @Autowired
    private MeasurementDao measurementDao;

    @Override
    public List<MeasurementDTO> getAllMeasurements(String hostId) {
        List<Measurement> measurementsByHost = measurementDao.findByHostId(hostId);

        List<MeasurementDTO> measurementsDTO = new ArrayList<>();

        if(measurementsByHost != null) {
            for (Measurement measurement : measurementsByHost) {
                List<MeasurementDTO> convertedSingleMeasurement = measurementConverter.convertToDTO(measurement);
                measurementsDTO.addAll(convertedSingleMeasurement);
            }
        }

        return measurementsDTO;
    }

    @Override
    public List<MeasurementDTO> getMeasurementsByType(String hostId, MeasurementDTO.Type type) {
        List<Measurement> measurementsByHost = measurementDao.findByHostId(hostId);

        List<MeasurementDTO> measurementsDTO = new ArrayList<>();

        if(measurementsByHost != null) {
            for (Measurement measurement : measurementsByHost) {
                MeasurementDTO convertedSingleMeasurementType = measurementConverter.convertToDTO(measurement, type);
                measurementsDTO.add(convertedSingleMeasurementType);
            }
        }

        return measurementsDTO;
    }

    @Override
    public MeasurementDTO getMeasurementByTypeAndId(MeasurementDTO.Type measuremenType, int measurementId) {
        Measurement measurement = measurementDao.findById(measurementId);

        MeasurementDTO measurementDTO = null;

        if(measurement != null) {
            measurementDTO = measurementConverter.convertToDTO(measurement, measuremenType);
        }

        return measurementDTO;
    }


}
