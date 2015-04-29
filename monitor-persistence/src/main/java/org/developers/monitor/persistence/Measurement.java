/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kamciak
 */
@Entity
@Table(name = "Measurement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measurement.findAll", query = "SELECT m FROM Measurement m"),
    @NamedQuery(name = "Measurement.findByMeasurementId", query = "SELECT m FROM Measurement m WHERE m.measurementPK.measurementId = :measurementId"),
    @NamedQuery(name = "Measurement.findByMemorymemoryId", query = "SELECT m FROM Measurement m WHERE m.measurementPK.memorymemoryId = :memorymemoryId"),
    @NamedQuery(name = "Measurement.findByNetworknetworkId", query = "SELECT m FROM Measurement m WHERE m.measurementPK.networknetworkId = :networknetworkId"),
    @NamedQuery(name = "Measurement.findByDiskdiskID", query = "SELECT m FROM Measurement m WHERE m.measurementPK.diskdiskID = :diskdiskID"),
    @NamedQuery(name = "Measurement.findByCPUCPUId", query = "SELECT m FROM Measurement m WHERE m.measurementPK.cPUCPUId = :cPUCPUId"),
    @NamedQuery(name = "Measurement.findBySensorsensorId", query = "SELECT m FROM Measurement m WHERE m.sensorsensorId = :sensorsensorId"),
    @NamedQuery(name = "Measurement.findByMeasurementDate", query = "SELECT m FROM Measurement m WHERE m.measurementDate = :measurementDate")})
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected org.developers.monitor.persistence.MeasurementPK measurementPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sensor_sensorId")
    private int sensorsensorId;
    @Column(name = "measurementDate")
    @Temporal(TemporalType.DATE)
    private Date measurementDate;

    public Measurement() {
    }

    public Measurement(org.developers.monitor.persistence.MeasurementPK measurementPK) {
        this.measurementPK = measurementPK;
    }

    public Measurement(org.developers.monitor.persistence.MeasurementPK measurementPK, int sensorsensorId) {
        this.measurementPK = measurementPK;
        this.sensorsensorId = sensorsensorId;
    }

    public Measurement(int measurementId, int memorymemoryId, int networknetworkId, int diskdiskID, int cPUCPUId) {
        this.measurementPK = new MeasurementPK(measurementId, memorymemoryId, networknetworkId, diskdiskID, cPUCPUId);
    }

    public org.developers.monitor.persistence.MeasurementPK getMeasurementPK() {
        return measurementPK;
    }

    public void setMeasurementPK(org.developers.monitor.persistence.MeasurementPK measurementPK) {
        this.measurementPK = measurementPK;
    }

    public int getSensorsensorId() {
        return sensorsensorId;
    }

    public void setSensorsensorId(int sensorsensorId) {
        this.sensorsensorId = sensorsensorId;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (measurementPK != null ? measurementPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measurement)) {
            return false;
        }
        Measurement other = (Measurement) object;
        if ((this.measurementPK == null && other.measurementPK != null) || (this.measurementPK != null && !this.measurementPK.equals(other.measurementPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.developers.monitor.persistence.Measurement[ measurementPK=" + measurementPK + " ]";
    }
    
}
