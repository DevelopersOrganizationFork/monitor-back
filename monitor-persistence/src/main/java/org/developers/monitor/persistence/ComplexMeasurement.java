/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence;

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
@Table(name = "ComplexMeasurement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComplexMeasurement.findAll", query = "SELECT c FROM ComplexMeasurement c"),
    @NamedQuery(name = "ComplexMeasurement.findByComplexMeasurementId", query = "SELECT c FROM ComplexMeasurement c WHERE c.complexMeasurementId = :complexMeasurementId"),
    @NamedQuery(name = "ComplexMeasurement.findByUsersidUser", query = "SELECT c FROM ComplexMeasurement c WHERE c.usersidUser = :usersidUser"),
    @NamedQuery(name = "ComplexMeasurement.findByTimeRange", query = "SELECT c FROM ComplexMeasurement c WHERE c.timeRange = :timeRange"),
    @NamedQuery(name = "ComplexMeasurement.findByMeasurementInterval", query = "SELECT c FROM ComplexMeasurement c WHERE c.measurementInterval = :measurementInterval"),
    @NamedQuery(name = "ComplexMeasurement.findByUnitType", query = "SELECT c FROM ComplexMeasurement c WHERE c.unitType = :unitType"),
    @NamedQuery(name = "ComplexMeasurement.findByMeasurementType", query = "SELECT c FROM ComplexMeasurement c WHERE c.measurementType = :measurementType"),
    @NamedQuery(name = "ComplexMeasurement.findByHosthostId", query = "SELECT c FROM ComplexMeasurement c WHERE c.hosthostId = :hosthostId")})
public class ComplexMeasurement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "complexMeasurementId")
    private Integer complexMeasurementId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Users_idUser")
    private int usersidUser;
    @Column(name = "timeRange")
    private Integer timeRange;
    @Column(name = "measurementInterval")
    private Integer measurementInterval;
    @Size(max = 20)
    @Column(name = "unitType")
    private String unitType;
    @Size(max = 20)
    @Column(name = "measurementType")
    private String measurementType;
    @Size(max = 50)
    @Column(name = "Host_hostId")
    private String hosthostId;

    public ComplexMeasurement() {
    }

    public ComplexMeasurement(Integer complexMeasurementId) {
        this.complexMeasurementId = complexMeasurementId;
    }

    public ComplexMeasurement(Integer complexMeasurementId, int usersidUser) {
        this.complexMeasurementId = complexMeasurementId;
        this.usersidUser = usersidUser;
    }

    public Integer getComplexMeasurementId() {
        return complexMeasurementId;
    }

    public void setComplexMeasurementId(Integer complexMeasurementId) {
        this.complexMeasurementId = complexMeasurementId;
    }

    public int getUsersidUser() {
        return usersidUser;
    }

    public void setUsersidUser(int usersidUser) {
        this.usersidUser = usersidUser;
    }

    public Integer getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Integer timeRange) {
        this.timeRange = timeRange;
    }

    public Integer getMeasurementInterval() {
        return measurementInterval;
    }

    public void setMeasurementInterval(Integer measurementInterval) {
        this.measurementInterval = measurementInterval;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getHosthostId() {
        return hosthostId;
    }

    public void setHosthostId(String hosthostId) {
        this.hosthostId = hosthostId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (complexMeasurementId != null ? complexMeasurementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComplexMeasurement)) {
            return false;
        }
        ComplexMeasurement other = (ComplexMeasurement) object;
        if ((this.complexMeasurementId == null && other.complexMeasurementId != null) || (this.complexMeasurementId != null && !this.complexMeasurementId.equals(other.complexMeasurementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.developers.monitor.persistence.ComplexMeasurement[ complexMeasurementId=" + complexMeasurementId + " ]";
    }
    
}
