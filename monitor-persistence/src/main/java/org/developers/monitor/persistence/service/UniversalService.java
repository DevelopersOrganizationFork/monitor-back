/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.developers.monitor.persistence.ComplexMeasurement;
import org.developers.monitor.persistence.Cpu;
import org.developers.monitor.persistence.DAO.ComplexMeasurementDao;
import org.developers.monitor.persistence.DAO.CpuDao;
import org.developers.monitor.persistence.DAO.DiskDao;
import org.developers.monitor.persistence.DAO.HostDao;
import org.developers.monitor.persistence.DAO.MeasurementDao;
import org.developers.monitor.persistence.DAO.MemoryDao;
import org.developers.monitor.persistence.DAO.NetworkDao;
import org.developers.monitor.persistence.DAO.UserDao;
import org.developers.monitor.persistence.Disk;
import org.developers.monitor.persistence.Measurement;
import org.developers.monitor.persistence.MeasurementPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.developers.monitor.persistence.Host;
import org.developers.monitor.persistence.Memory;
import org.developers.monitor.persistence.Network;
import org.developers.monitor.persistence.Users;
import org.developers.monitor.persistence.service.exceptions.UserAlreadyExistException;
import org.developers.monitor.persistence.service.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomek
 */
@Service
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
    
    @Autowired
    private UserDao userDao;

    @Autowired
    private ComplexMeasurementDao complexMeasurementDao;
    
    public Integer insertComplexMeasurement(ComplexMeasurement complexMeasurement)
    {
        return complexMeasurementDao.persist(complexMeasurement).getComplexMeasurementId();
    }   
    
    public List<Host> getAllHosts()
    {
        return hostDao.getAllHosts();
    }
    
    public List<ComplexMeasurement> getAllComplexMeasurements()
    {
        return complexMeasurementDao.getAllComplexMeasurements();
    }
    
    public List<Cpu> getAllCpus()
    {
        return cpuDao.getAllCpus();
    }
    
    public List<Disk> getAllDisks()
    {
        return diskDao.getAllDisks();
    }
    
    public List<Network> getAllNetworks()
    {
        return networkDao.getAllNetworks();
    }
    
    public List<Memory> getAllMemory()
    {
        return memoryDao.getAllMemory();
    }
    
    public List<Measurement> getAllMeasurements()
    {
        return measurementDao.getAllMeasurements();
    }

    public Integer insertUser(Users user) throws UserAlreadyExistException {
        String userName = user.getUserName();
        if (!userDao.isUserNameAvailable(userName)) {
            throw new UserAlreadyExistException(String.format("Username: [%s] already exist in database.", userName));
        }

        return userDao.persist(user).getIdUser();
    }

    public void removeUser(Users user)
    {
        userDao.remove(user);
    }
    
    public List<Users> getAllUsers(){
        return userDao.getAllUsers();
    }
    
    public Users getUserById(Integer id) throws UserNotFoundException
    {
        return userDao.getUserById(id);
    }
    
    public Users getUserByUserName(String userName) throws UserNotFoundException {
        return userDao.getUserByUserName(userName);
    }
    
    public Integer insertMeasurementData(MeasurementData measurementData)
    {
        try{
            Host host = hostDao.findById(measurementData.host.getHostId());
            
            String hostId;
            if(host != null && host.getHostId() != null && !host.getHostId().isEmpty()){
                hostId = host.getHostId();
            }
            else{
                hostId = hostDao.persist(measurementData.host).getHostId();
            }
            
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
            measurement.setHosthostId(hostId);
            measurement.setMeasurementDate(measurementData.date);
            measurement.setMeasurementPK(measurementPK);
            
            return measurementDao.persist(measurement).getMeasurementPK().getMeasurementId();
            
        } catch(Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
       
    
}
