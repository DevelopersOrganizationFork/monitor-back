package org.developers.monitor.measurement.provider;

import org.developers.monitor.persistence.ComplexMeasurement;
import org.developers.monitor.persistence.DAO.ComplexMeasurementDao;
import org.developers.monitor.rest.converter.MeasurementConverter;
import org.developers.monitor.persistence.DAO.MeasurementDao;
import org.developers.monitor.persistence.Measurement;
import org.developers.monitor.rest.dto.ComplexMeasurementDTO;
import org.developers.monitor.rest.dto.MeasurementDTO;
import org.developers.monitor.rest.dto.MeasurementInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<MeasurementDTO> getAllMeasurements() {
        List<Measurement> measurementsByHost = measurementDao.getAllMeasurements();

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

        //measurementsDTO.addAll(complexMeasurementProvider.provideAllComplexMeasurementsByType(hostId, type, measurementsDTO));

        return measurementsDTO;
    }

    /*@Override
    public List<MeasurementDTO> getMeasurementsByTypeAndId(String hostId, int measurementId) {
        List<Measurement> measurementsByHost = measurementDao.findByHostId(hostId);

        List<MeasurementDTO> measurementsDTO = new ArrayList<>();

        if(measurementsByHost != null) {
            for (Measurement measurement : measurementsByHost) {
                List<MeasurementDTO> convertedSingleMeasurement = measurementConverter.convertToDTO(measurement);
                measurementsDTO.addAll(convertedSingleMeasurement);
            }
        }

        return measurementsDTO;//complexMeasurementProvider.provideAllComplexMeasurementsById(measurementId, measurementsDTO);
    }*/

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

    @Override
    public List<MeasurementDTO> getComplexMeasurements(String hostId, String measurementName) {
        ComplexMeasurement complexMeasurement = complexMeasurementDao.getComplexMeasurementByName(measurementName);

        List<MeasurementDTO> measurementsDTO = new ArrayList<>();

        if(complexMeasurement != null){
            String measurementType = complexMeasurement.getMeasurementType();
            List<MeasurementDTO> simpleMeasurementsDTO = getMeasurementsByType(hostId,MeasurementDTO.Type.valueOf(measurementType));

            long interval = complexMeasurement.getMeasurementInterval();
            long timeFrame = complexMeasurement.getTimeRange();
            String unitType = complexMeasurement.getUnitType();

            int unitMultiplier = 1;

            if(unitType.equalsIgnoreCase("MINUTE")){
                unitMultiplier = 60;
            }else if(unitType.equalsIgnoreCase("HOUR")){
                unitMultiplier = 3600;
            }

            interval *= unitMultiplier;
            timeFrame *= unitMultiplier;

            interval *= 1000;
            timeFrame *= 1000;

            simpleMeasurementsDTO = simpleMeasurementsDTO
                    .stream()
                    .sorted((m1, m2) -> Long.signum(m2.date.getTime() - m1.date.getTime()))
                    .collect(Collectors.toList());

            long startTimeFrame = simpleMeasurementsDTO.get(0).date.getTime();
            long endTimeFrame = startTimeFrame+timeFrame;
            double sum = 0;
            int amount = 0;
            for(MeasurementDTO measurementDTO : simpleMeasurementsDTO){
                long measurementTime = measurementDTO.date.getTime();
                if(endTimeFrame < measurementTime){
                    startTimeFrame = measurementTime;
                    endTimeFrame = startTimeFrame+timeFrame;

                    ComplexMeasurementDTO complexMeasurementDTO = new ComplexMeasurementDTO();
                    complexMeasurementDTO.value = sum/amount;
                    complexMeasurementDTO.type = MeasurementDTO.Type.valueOf(measurementType);
                    complexMeasurementDTO.date = new Date(startTimeFrame);
                    complexMeasurementDTO.isComplex = true;

                    measurementsDTO.add(complexMeasurementDTO);
                    sum = 0;
                }
                ++amount;
            }

        }
        return measurementsDTO;
    }

}
