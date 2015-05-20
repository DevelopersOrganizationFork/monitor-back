/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.service;

import org.developers.monitor.persistence.DAO.CpuDao;
import org.developers.monitor.persistence.DAO.DiskDao;
import org.developers.monitor.persistence.DAO.HostDao;
import org.developers.monitor.persistence.DAO.MeasurementDao;
import org.developers.monitor.persistence.DAO.MemoryDao;
import org.developers.monitor.persistence.DAO.NetworkDao;
import org.developers.monitor.persistence.DAO.SensorDao;
import org.developers.monitor.persistence.Measurement;

/**
 *
 * @author Tomek
 */
public class UniversalService {
    private static HostDao hostDao;
    private static CpuDao cpuDao;
    private static DiskDao diskDao;
    private static NetworkDao networkDao;
    private static MemoryDao memoryDao;
    private static MeasurementDao measurementDao;
    
    public UniversalService(){
        hostDao = new HostDao();
        cpuDao = new CpuDao();
        diskDao = new DiskDao();
        networkDao = new NetworkDao();
        memoryDao = new MemoryDao();
        measurementDao = new MeasurementDao();
    }
    
    public void insertMeasurementData(MeasurementData measurementData)
    {
        try{
            hostDao.persist(measurementData.host);
            cpuDao.persist(measurementData.cpu);
            diskDao.persist(measurementData.disk);
            memoryDao.persist(measurementData.memory);
            networkDao.persist(measurementData.network);
            
            
            Measurement measurement = new Measurement();
            measurement.setMeasurementDate(null);
        }
        catch(Exception e)
        {
        }
    }
       
    
}
