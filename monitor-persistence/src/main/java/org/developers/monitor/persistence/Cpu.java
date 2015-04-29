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
@Table(name = "CPU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cpu.findAll", query = "SELECT c FROM Cpu c"),
    @NamedQuery(name = "Cpu.findByCPUId", query = "SELECT c FROM Cpu c WHERE c.cPUId = :cPUId"),
    @NamedQuery(name = "Cpu.findByCPUUser", query = "SELECT c FROM Cpu c WHERE c.cPUUser = :cPUUser"),
    @NamedQuery(name = "Cpu.findByCPUSys", query = "SELECT c FROM Cpu c WHERE c.cPUSys = :cPUSys"),
    @NamedQuery(name = "Cpu.findByCPUNice", query = "SELECT c FROM Cpu c WHERE c.cPUNice = :cPUNice"),
    @NamedQuery(name = "Cpu.findByCPUIdle", query = "SELECT c FROM Cpu c WHERE c.cPUIdle = :cPUIdle"),
    @NamedQuery(name = "Cpu.findByCPUWait", query = "SELECT c FROM Cpu c WHERE c.cPUWait = :cPUWait"),
    @NamedQuery(name = "Cpu.findByCPUIrq", query = "SELECT c FROM Cpu c WHERE c.cPUIrq = :cPUIrq"),
    @NamedQuery(name = "Cpu.findByCPUSoftIrq", query = "SELECT c FROM Cpu c WHERE c.cPUSoftIrq = :cPUSoftIrq"),
    @NamedQuery(name = "Cpu.findByCPUStolen", query = "SELECT c FROM Cpu c WHERE c.cPUStolen = :cPUStolen"),
    @NamedQuery(name = "Cpu.findByCPUTotal", query = "SELECT c FROM Cpu c WHERE c.cPUTotal = :cPUTotal")})
public class Cpu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CPUId")
    private Integer cPUId;
    @Column(name = "CPUUser")
    private BigInteger cPUUser;
    @Column(name = "CPUSys")
    private BigInteger cPUSys;
    @Column(name = "CPUNice")
    private BigInteger cPUNice;
    @Column(name = "CPUIdle")
    private BigInteger cPUIdle;
    @Column(name = "CPUWait")
    private BigInteger cPUWait;
    @Column(name = "CPUIrq")
    private BigInteger cPUIrq;
    @Column(name = "CPUSoftIrq")
    private BigInteger cPUSoftIrq;
    @Column(name = "CPUStolen")
    private BigInteger cPUStolen;
    @Column(name = "CPUTotal")
    private BigInteger cPUTotal;

    public Cpu() {
    }

    public Cpu(Integer cPUId) {
        this.cPUId = cPUId;
    }

    public Integer getCPUId() {
        return cPUId;
    }

    public void setCPUId(Integer cPUId) {
        this.cPUId = cPUId;
    }

    public BigInteger getCPUUser() {
        return cPUUser;
    }

    public void setCPUUser(BigInteger cPUUser) {
        this.cPUUser = cPUUser;
    }

    public BigInteger getCPUSys() {
        return cPUSys;
    }

    public void setCPUSys(BigInteger cPUSys) {
        this.cPUSys = cPUSys;
    }

    public BigInteger getCPUNice() {
        return cPUNice;
    }

    public void setCPUNice(BigInteger cPUNice) {
        this.cPUNice = cPUNice;
    }

    public BigInteger getCPUIdle() {
        return cPUIdle;
    }

    public void setCPUIdle(BigInteger cPUIdle) {
        this.cPUIdle = cPUIdle;
    }

    public BigInteger getCPUWait() {
        return cPUWait;
    }

    public void setCPUWait(BigInteger cPUWait) {
        this.cPUWait = cPUWait;
    }

    public BigInteger getCPUIrq() {
        return cPUIrq;
    }

    public void setCPUIrq(BigInteger cPUIrq) {
        this.cPUIrq = cPUIrq;
    }

    public BigInteger getCPUSoftIrq() {
        return cPUSoftIrq;
    }

    public void setCPUSoftIrq(BigInteger cPUSoftIrq) {
        this.cPUSoftIrq = cPUSoftIrq;
    }

    public BigInteger getCPUStolen() {
        return cPUStolen;
    }

    public void setCPUStolen(BigInteger cPUStolen) {
        this.cPUStolen = cPUStolen;
    }

    public BigInteger getCPUTotal() {
        return cPUTotal;
    }

    public void setCPUTotal(BigInteger cPUTotal) {
        this.cPUTotal = cPUTotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cPUId != null ? cPUId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cpu)) {
            return false;
        }
        Cpu other = (Cpu) object;
        if ((this.cPUId == null && other.cPUId != null) || (this.cPUId != null && !this.cPUId.equals(other.cPUId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.developers.monitor.persistence.Cpu[ cPUId=" + cPUId + " ]";
    }
    
}
