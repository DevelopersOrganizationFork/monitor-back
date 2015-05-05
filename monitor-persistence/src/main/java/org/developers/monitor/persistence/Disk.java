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
@Table(name = "Disk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disk.findAll", query = "SELECT d FROM Disk d"),
    @NamedQuery(name = "Disk.findByDiskID", query = "SELECT d FROM Disk d WHERE d.diskID = :diskID"),
    @NamedQuery(name = "Disk.findByDiskRead", query = "SELECT d FROM Disk d WHERE d.diskRead = :diskRead"),
    @NamedQuery(name = "Disk.findByDiskWrite", query = "SELECT d FROM Disk d WHERE d.diskWrite = :diskWrite")})
public class Disk implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "diskID")
    private Integer diskID;
    @Column(name = "diskRead")
    private BigInteger diskRead;
    @Column(name = "diskWrite")
    private BigInteger diskWrite;

    public Disk() {
    }

    public Disk(Integer diskID) {
        this.diskID = diskID;
    }

    public Integer getDiskID() {
        return diskID;
    }

    public void setDiskID(Integer diskID) {
        this.diskID = diskID;
    }

    public BigInteger getDiskRead() {
        return diskRead;
    }

    public void setDiskRead(BigInteger diskRead) {
        this.diskRead = diskRead;
    }

    public BigInteger getDiskWrite() {
        return diskWrite;
    }

    public void setDiskWrite(BigInteger diskWrite) {
        this.diskWrite = diskWrite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diskID != null ? diskID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disk)) {
            return false;
        }
        Disk other = (Disk) object;
        if ((this.diskID == null && other.diskID != null) || (this.diskID != null && !this.diskID.equals(other.diskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.developers.monitor.persistence.Disk[ diskID=" + diskID + " ]";
    }
    
}
