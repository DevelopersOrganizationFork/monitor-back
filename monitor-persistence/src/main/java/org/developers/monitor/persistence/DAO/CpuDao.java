/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.DAO;

import java.util.List;
import org.developers.monitor.persistence.Cpu;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomek
 */
@Service
public class CpuDao extends Dao<Integer, Cpu> {
    public List<Cpu> getAllCpus()
    {
        return entityManager.createNamedQuery("Cpu.findAll", Cpu.class)
            .getResultList();
    }
    
    public List<Cpu> getCpus(Integer maxRows)
    {
        return entityManager.createNamedQuery("Cpu.findAll", Cpu.class)
            .setMaxResults(maxRows)
            .getResultList();
    }
}
