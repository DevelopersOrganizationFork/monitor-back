/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.monitor.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kamciak
 */
@Entity
@Table(name = "Host")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Host.findAll", query = "SELECT h FROM Host h"),
    @NamedQuery(name = "Host.findByHostId", query = "SELECT h FROM Host h WHERE h.hostId = :hostId"),
    @NamedQuery(name = "Host.findBySensorsensorId", query = "SELECT h FROM Host h WHERE h.sensorsensorId = :sensorsensorId"),
    @NamedQuery(name = "Host.findByHostName", query = "SELECT h FROM Host h WHERE h.hostName = :hostName"),
    @NamedQuery(name = "Host.findByHostIP", query = "SELECT h FROM Host h WHERE h.hostIP = :hostIP")})
public class Host implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hostId")
    private Integer hostId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sensor_sensorId")
    private int sensorsensorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "hostName")
    private String hostName;
    @Size(max = 20)
    @Column(name = "hostIP")
    private String hostIP;

    public Host() {
    }

    public Host(Integer hostId) {
        this.hostId = hostId;
    }

    public Host(Integer hostId, int sensorsensorId, String hostName) {
        this.hostId = hostId;
        this.sensorsensorId = sensorsensorId;
        this.hostName = hostName;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public int getSensorsensorId() {
        return sensorsensorId;
    }

    public void setSensorsensorId(int sensorsensorId) {
        this.sensorsensorId = sensorsensorId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hostId != null ? hostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Host)) {
            return false;
        }
        Host other = (Host) object;
        if ((this.hostId == null && other.hostId != null) || (this.hostId != null && !this.hostId.equals(other.hostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.developers.monitor.monitor.persistence.Host[ hostId=" + hostId + " ]";
    }
    
}
