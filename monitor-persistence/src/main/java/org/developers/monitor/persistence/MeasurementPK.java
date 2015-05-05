/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kamciak
 */
@Embeddable
public class MeasurementPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "measurementId")
    private int measurementId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Memory_memoryId")
    private int memorymemoryId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Network_networkId")
    private int networknetworkId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Disk_diskID")
    private int diskdiskID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CPU_CPUId")
    private int cPUCPUId;

    public MeasurementPK() {
    }

    public MeasurementPK(int measurementId, int memorymemoryId, int networknetworkId, int diskdiskID, int cPUCPUId) {
        this.measurementId = measurementId;
        this.memorymemoryId = memorymemoryId;
        this.networknetworkId = networknetworkId;
        this.diskdiskID = diskdiskID;
        this.cPUCPUId = cPUCPUId;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public int getMemorymemoryId() {
        return memorymemoryId;
    }

    public void setMemorymemoryId(int memorymemoryId) {
        this.memorymemoryId = memorymemoryId;
    }

    public int getNetworknetworkId() {
        return networknetworkId;
    }

    public void setNetworknetworkId(int networknetworkId) {
        this.networknetworkId = networknetworkId;
    }

    public int getDiskdiskID() {
        return diskdiskID;
    }

    public void setDiskdiskID(int diskdiskID) {
        this.diskdiskID = diskdiskID;
    }

    public int getCPUCPUId() {
        return cPUCPUId;
    }

    public void setCPUCPUId(int cPUCPUId) {
        this.cPUCPUId = cPUCPUId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) measurementId;
        hash += (int) memorymemoryId;
        hash += (int) networknetworkId;
        hash += (int) diskdiskID;
        hash += (int) cPUCPUId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeasurementPK)) {
            return false;
        }
        MeasurementPK other = (MeasurementPK) object;
        if (this.measurementId != other.measurementId) {
            return false;
        }
        if (this.memorymemoryId != other.memorymemoryId) {
            return false;
        }
        if (this.networknetworkId != other.networknetworkId) {
            return false;
        }
        if (this.diskdiskID != other.diskdiskID) {
            return false;
        }
        if (this.cPUCPUId != other.cPUCPUId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.developers.monitor.persistence.MeasurementPK[ measurementId=" + measurementId + ", memorymemoryId=" + memorymemoryId + ", networknetworkId=" + networknetworkId + ", diskdiskID=" + diskdiskID + ", cPUCPUId=" + cPUCPUId + " ]";
    }
    
}
