/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.DAO;

import java.util.List;
import org.developers.monitor.persistence.ComplexMeasurement;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kamciak
 */
@Service
public class ComplexMeasurementDao extends Dao<Integer, ComplexMeasurement>{
    public List<ComplexMeasurement> getAllComplexMeasurements()
    {
        return entityManager.createNamedQuery("ComplexMeasurement.findAll", ComplexMeasurement.class)
            .getResultList();
    }
    
        public List<ComplexMeasurement> getComplexMeasurements(Integer maxRows)
    {
        return entityManager.createNamedQuery("ComplexMeasurement.findAll", ComplexMeasurement.class)
            .setMaxResults(maxRows)
            .getResultList();
    }
}
