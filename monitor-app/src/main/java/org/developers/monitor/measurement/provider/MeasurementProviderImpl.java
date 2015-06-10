package org.developers.monitor.measurement.provider;

import org.developers.monitor.persistence.ComplexMeasurement;
import org.developers.monitor.persistence.DAO.ComplexMeasurementDao;
import org.developers.monitor.rest.converter.MeasurementConverter;
import org.developers.monitor.persistence.DAO.MeasurementDao;
import org.developers.monitor.persistence.Measurement;
import org.developers.monitor.rest.dto.MeasurementDTO;
import org.developers.monitor.rest.dto.MeasurementInfo;
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
    
    @Autowired
    private ComplexMeasurementDao complexMeasurementDao;

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

    public List<MeasurementInfo> getListOfMeasurements(String hostId) {
        List<MeasurementInfo> result = new ArrayList<>();
        result.add(new MeasurementInfo("CPU", MeasurementDTO.Type.CPU));
        result.add(new MeasurementInfo("MEMORY", MeasurementDTO.Type.MEMORY));
        result.add(new MeasurementInfo("NETWORKUP", MeasurementDTO.Type.NETWORKUP));
        result.add(new MeasurementInfo("NETWORKDOWN", MeasurementDTO.Type.NETWORKDOWN));
        List<ComplexMeasurement> measurements = complexMeasurementDao.getAllComplexMeasurementsByHost(hostId);
        measurements.stream().forEach(m -> result.add(new MeasurementInfo(m.getName(), MeasurementDTO.Type.valueOf(m.getMeasurementType()))));
        return result;
    }

}
