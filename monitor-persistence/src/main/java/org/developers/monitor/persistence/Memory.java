/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kamciak
 */
@Entity
@Table(name = "Memory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memory.findAll", query = "SELECT m FROM Memory m"),
    @NamedQuery(name = "Memory.findByMemoryId", query = "SELECT m FROM Memory m WHERE m.memoryId = :memoryId"),
    @NamedQuery(name = "Memory.findByMemoryTotal", query = "SELECT m FROM Memory m WHERE m.memoryTotal = :memoryTotal"),
    @NamedQuery(name = "Memory.findByMemoryRAM", query = "SELECT m FROM Memory m WHERE m.memoryRAM = :memoryRAM"),
    @NamedQuery(name = "Memory.findByMemoryUsed", query = "SELECT m FROM Memory m WHERE m.memoryUsed = :memoryUsed"),
    @NamedQuery(name = "Memory.findByMemoryActualUsed", query = "SELECT m FROM Memory m WHERE m.memoryActualUsed = :memoryActualUsed")})
public class Memory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "memoryId")
    private Integer memoryId;
    @Column(name = "memoryTotal")
    private BigInteger memoryTotal;
    @Column(name = "memoryRAM")
    private Integer memoryRAM;
    @Column(name = "memoryUsed")
    private BigInteger memoryUsed;
    @Column(name = "memoryActualUsed")
    private BigInteger memoryActualUsed;

    public Memory() {
    }

    public Memory(Integer memoryId) {
        this.memoryId = memoryId;
    }

    public Integer getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(Integer memoryId) {
        this.memoryId = memoryId;
    }

    public BigInteger getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(BigInteger memoryTotal) {
        this.memoryTotal = memoryTotal;
    }

    public Integer getMemoryRAM() {
        return memoryRAM;
    }

    public void setMemoryRAM(Integer memoryRAM) {
        this.memoryRAM = memoryRAM;
    }

    public BigInteger getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(BigInteger memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public BigInteger getMemoryActualUsed() {
        return memoryActualUsed;
    }

    public void setMemoryActualUsed(BigInteger memoryActualUsed) {
        this.memoryActualUsed = memoryActualUsed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memoryId != null ? memoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memory)) {
            return false;
        }
        Memory other = (Memory) object;
        if ((this.memoryId == null && other.memoryId != null) || (this.memoryId != null && !this.memoryId.equals(other.memoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.developers.monitor.persistence.Memory[ memoryId=" + memoryId + " ]";
    }
    
}
