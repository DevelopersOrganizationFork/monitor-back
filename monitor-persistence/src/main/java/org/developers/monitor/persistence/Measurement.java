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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Measurement.findByMeasurementId", query = "SELECT m FROM Measurement m WHERE m.measurementId = :measurementId"),
    @NamedQuery(name = "Measurement.findBySensorsensorId", query = "SELECT m FROM Measurement m WHERE m.sensorsensorId = :sensorsensorId"),
    @NamedQuery(name = "Measurement.findByMeasurementValue", query = "SELECT m FROM Measurement m WHERE m.measurementValue = :measurementValue"),
    @NamedQuery(name = "Measurement.findByMeasurementDate", query = "SELECT m FROM Measurement m WHERE m.measurementDate = :measurementDate"),
    @NamedQuery(name = "Measurement.findByMeasurementType", query = "SELECT m FROM Measurement m WHERE m.measurementType = :measurementType")})
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "measurementId")
    private Integer measurementId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sensor_sensorId")
    private int sensorsensorId;
    @Column(name = "measurementValue")
    private Integer measurementValue;
    @Column(name = "measurementDate")
    @Temporal(TemporalType.DATE)
    private Date measurementDate;
    @Size(max = 50)
    @Column(name = "measurementType")
    private String measurementType;

    public Measurement() {
    }

    public Measurement(Integer measurementId) {
        this.measurementId = measurementId;
    }

    public Measurement(Integer measurementId, int sensorsensorId) {
        this.measurementId = measurementId;
        this.sensorsensorId = sensorsensorId;
    }

    public Integer getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Integer measurementId) {
        this.measurementId = measurementId;
    }

    public int getSensorsensorId() {
        return sensorsensorId;
    }

    public void setSensorsensorId(int sensorsensorId) {
        this.sensorsensorId = sensorsensorId;
    }

    public Integer getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(Integer measurementValue) {
        this.measurementValue = measurementValue;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (measurementId != null ? measurementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measurement)) {
            return false;
        }
        Measurement other = (Measurement) object;
        if ((this.measurementId == null && other.measurementId != null) || (this.measurementId != null && !this.measurementId.equals(other.measurementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.developers.monitor.monitor.persistence.Measurement[ measurementId=" + measurementId + " ]";
    }
    
}
