package org.developers.monitor.complex;

import org.developers.monitor.persistence.ComplexMeasurement;
import org.developers.monitor.persistence.DAO.ComplexMeasurementDao;
import org.developers.monitor.rest.dto.ComplexMeasurementDefinitionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reynev on 6/10/15.
 */
@Component
public class ComplexMeasurementsDefinitionManagerImpl implements ComplexMeasurementsDefinitionManager {

    @Autowired
    ComplexMeasurementDao complexMeasurementDao;
    @Override
    public List<ComplexMeasurementDefinitionDTO> getComplexMeasurementsByHost(String hostId) {
        List<ComplexMeasurement> complexMeasurements = complexMeasurementDao.getAllComplexMeasurementsByHost(hostId);

        List<ComplexMeasurementDefinitionDTO> definitionDTOs = new ArrayList<>();
        for(ComplexMeasurement complexMeasurement : complexMeasurements){
            ComplexMeasurementDefinitionDTO definitionDTO = new ComplexMeasurementDefinitionDTO();
            definitionDTO.id = complexMeasurement.getComplexMeasurementId();
            definitionDTO.interval = complexMeasurement.getMeasurementInterval();
            definitionDTO.unitType = complexMeasurement.getUnitType();
            definitionDTO.timeRange = complexMeasurement.getTimeRange();
            definitionDTO.userId = complexMeasurement.getUsersidUser();
            definitionDTO.measurementType = complexMeasurement.getMeasurementType();
            definitionDTO.hostId = hostId;
            definitionDTO.name = complexMeasurement.getName();

            definitionDTOs.add(definitionDTO);
        }
        return definitionDTOs;
    }

    @Override
    public void removeComplexMeasurement(String name) {
        ComplexMeasurement complexMeasurement = complexMeasurementDao.getComplexMeasurementByName(name);
        complexMeasurementDao.remove(complexMeasurement);
    }

    @Override
    public int createComplexMeasurement(ComplexMeasurementDefinitionDTO complexMeasurementDTO, String hostId) {
        ComplexMeasurement complexMeasurement = new ComplexMeasurement();

        complexMeasurement.setHosthostId(hostId);
        complexMeasurement.setMeasurementInterval(complexMeasurementDTO.interval);
        complexMeasurement.setTimeRange(complexMeasurementDTO.timeRange);
        complexMeasurement.setMeasurementType(complexMeasurementDTO.measurementType);
        complexMeasurement.setUnitType(complexMeasurementDTO.unitType);
        //complexMeasurement.setUsersidUser(complexMeasurementDTO.userId);
        complexMeasurement.setName(complexMeasurementDTO.name);

        complexMeasurement = complexMeasurementDao.persist(complexMeasurement);

        return complexMeasurement.getComplexMeasurementId();
    }
}
