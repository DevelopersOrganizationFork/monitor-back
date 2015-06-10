/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.service;

import java.util.Date;
import org.developers.monitor.persistence.Cpu;
import org.developers.monitor.persistence.Disk;
import org.developers.monitor.persistence.Host;
import org.developers.monitor.persistence.Memory;
import org.developers.monitor.persistence.Network;


/**
 *
 * @author Tomek
 */
public class MeasurementData {
    public Host host;
    public Cpu cpu;
    public Disk disk;
    public Memory memory;
    public Network network;
    public Date date;
   
    public MeasurementData(Host host, Cpu cpu, Disk disk, Memory memory, Network network, Date date)
    {
        this.host = host;
        this.cpu = cpu;
        this.disk = disk;
        this.memory = memory;
        this.network = network;
        this.date = date;
    }
}
