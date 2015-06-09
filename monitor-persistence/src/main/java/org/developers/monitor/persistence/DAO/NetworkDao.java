/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.DAO;

import java.util.List;
import org.developers.monitor.persistence.Network;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomek
 */
@Service
public class NetworkDao extends Dao<Integer, Network> {
    public List<Network> getAllNetworks()
    {
        return entityManager.createNamedQuery("Network.findAll", Network.class)
            .getResultList();
    }
}
