/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.DAO;

import java.util.List;
import org.developers.monitor.persistence.Memory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomek
 */
@Service
public class MemoryDao extends Dao<Integer, Memory> {
    public List<Memory> getAllMemory()
    {
        return entityManager.createNamedQuery("Memory.findAll", Memory.class)
            .getResultList();
    }
}
