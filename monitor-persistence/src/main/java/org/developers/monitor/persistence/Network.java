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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kamciak
 */
@Entity
@Table(name = "Network")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Network.findAll", query = "SELECT n FROM Network n"),
    @NamedQuery(name = "Network.findByNetworkId", query = "SELECT n FROM Network n WHERE n.networkId = :networkId"),
    @NamedQuery(name = "Network.findByNetworkMAC", query = "SELECT n FROM Network n WHERE n.networkMAC = :networkMAC"),
    @NamedQuery(name = "Network.findByNetworkIP", query = "SELECT n FROM Network n WHERE n.networkIP = :networkIP"),
    @NamedQuery(name = "Network.findByNetworkDownload", query = "SELECT n FROM Network n WHERE n.networkDownload = :networkDownload"),
    @NamedQuery(name = "Network.findByNetworkUpload", query = "SELECT n FROM Network n WHERE n.networkUpload = :networkUpload")})
public class Network implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "networkId")
    private Integer networkId;
    @Size(max = 25)
    @Column(name = "networkMAC")
    private String networkMAC;
    @Size(max = 405)
    @Column(name = "networkIP")
    private String networkIP;
    @Column(name = "networkDownload")
    private BigInteger networkDownload;
    @Column(name = "networkUpload")
    private BigInteger networkUpload;

    public Network() {
    }

    public Network(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public String getNetworkMAC() {
        return networkMAC;
    }

    public void setNetworkMAC(String networkMAC) {
        this.networkMAC = networkMAC;
    }

    public String getNetworkIP() {
        return networkIP;
    }

    public void setNetworkIP(String networkIP) {
        this.networkIP = networkIP;
    }

    public BigInteger getNetworkDownload() {
        return networkDownload;
    }

    public void setNetworkDownload(BigInteger networkDownload) {
        this.networkDownload = networkDownload;
    }

    public BigInteger getNetworkUpload() {
        return networkUpload;
    }

    public void setNetworkUpload(BigInteger networkUpload) {
        this.networkUpload = networkUpload;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (networkId != null ? networkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Network)) {
            return false;
        }
        Network other = (Network) object;
        if ((this.networkId == null && other.networkId != null) || (this.networkId != null && !this.networkId.equals(other.networkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.developers.monitor.persistence.Network[ networkId=" + networkId + " ]";
    }
    
}
