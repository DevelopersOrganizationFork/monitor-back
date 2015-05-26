/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.developers.monitor.persistence.DAO.CpuDao;
import org.developers.monitor.persistence.DAO.DiskDao;
import org.developers.monitor.persistence.DAO.HostDao;
import org.developers.monitor.persistence.DAO.MeasurementDao;
import org.developers.monitor.persistence.DAO.MemoryDao;
import org.developers.monitor.persistence.DAO.NetworkDao;
import org.developers.monitor.persistence.Measurement;
import org.developers.monitor.persistence.MeasurementPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tomek
 */
@Component
public class UniversalService {
    
    @Autowired
    private HostDao hostDao;
    
    @Autowired
    private CpuDao cpuDao;
    
    @Autowired
    private DiskDao diskDao;
    
    @Autowired
    private NetworkDao networkDao;
    
    @Autowired
    private MemoryDao memoryDao;
    
    @Autowired
    private MeasurementDao measurementDao;
    
    
    public void insertMeasurementData(MeasurementData measurementData)
    {
        try{
            /*
            Integer hostId = hostDao.persist(measurementData.host).getHostId();
            Integer cpuId = cpuDao.persist(measurementData.cpu).getCPUId();
            Integer diskId = diskDao.persist(measurementData.disk).getDiskID();
            Integer memoryId = memoryDao.persist(measurementData.memory).getMemoryId();
            Integer networkId = networkDao.persist(measurementData.network).getNetworkId();
            
            MeasurementPK measurementPK = new MeasurementPK();
            measurementPK.setCPUCPUId(cpuId);
            measurementPK.setDiskdiskID(diskId);
            measurementPK.setMemorymemoryId(memoryId);
            measurementPK.setNetworknetworkId(networkId);
            
            Measurement measurement = new Measurement();
            measurement.setMeasurementDate(new Date());
            measurement.setMeasurementPK(measurementPK);
            
            measurementDao.persist(measurement);
            */
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
       
    
}
