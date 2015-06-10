/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.DAO;

import java.util.List;
import org.developers.monitor.persistence.Disk;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomek
 */
@Service
public class DiskDao extends Dao<Integer, Disk> {
    public List<Disk> getAllDisks()
    {
        return entityManager.createNamedQuery("Disk.findAll", Disk.class)
            .getResultList();
    }
    
    public List<Disk> getDisks(Integer maxRows)
    {
        return entityManager.createNamedQuery("Disk.findAll", Disk.class)
            .setMaxResults(maxRows)
            .getResultList();
    }
}
