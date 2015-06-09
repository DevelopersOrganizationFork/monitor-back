/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.DAO;

import java.util.List;
import org.developers.monitor.persistence.Host;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomek
 */
@Service
public class HostDao extends Dao<String, Host> {
 
    public List<Host> getAllHosts()
    {
        return entityManager.createNamedQuery("Host.findAll", Host.class)
            .getResultList();
    }
}
