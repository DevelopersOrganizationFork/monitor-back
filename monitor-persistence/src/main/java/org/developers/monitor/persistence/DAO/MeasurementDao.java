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


    public List<Measurement> findByHostId(String id) {
        return entityManager.createNamedQuery("Measurement.findByHosthostId", Measurement.class)
                .setParameter("hosthostId", id)
                .getResultList();
    }
    
    public List<Measurement> getAllMeasurements()
    {
        return entityManager.createNamedQuery("Measurement.findAll", Measurement.class)
            .getResultList();
    }

    public List<Measurement> getMeasurements(Integer maxRows)
    {
        return entityManager.createNamedQuery("Measurement.findAll", Measurement.class)
            .setMaxResults(maxRows)
            .getResultList();
    }

}
