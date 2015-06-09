/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.DAO;

import org.developers.monitor.persistence.Measurement;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Tomek
 */
@Service
public class MeasurementDao extends Dao<Integer, Measurement> {


    public List<Measurement> findByHostId(Integer id) {
        return null;
    }
    
    public List<Measurement> getAllMeasurements()
    {
        return entityManager.createNamedQuery("Measurement.findAll", Measurement.class)
            .getResultList();
    }


}
