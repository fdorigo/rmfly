/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fdorigo
 */
@Entity
@Table(name = "acftref")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acftref.findAll", query = "SELECT a FROM Acftref a"),
    @NamedQuery(name = "Acftref.findByCode", query = "SELECT a FROM Acftref a WHERE a.code = :code"),
    @NamedQuery(name = "Acftref.findByMfrname", query = "SELECT a FROM Acftref a WHERE a.mfrname = :mfrname"),
    @NamedQuery(name = "Acftref.findByModel", query = "SELECT a FROM Acftref a WHERE a.model = :model"),
    @NamedQuery(name = "Acftref.findByTypeacft", query = "SELECT a FROM Acftref a WHERE a.typeacft = :typeacft"),
    @NamedQuery(name = "Acftref.findByTypeeng", query = "SELECT a FROM Acftref a WHERE a.typeeng = :typeeng"),
    @NamedQuery(name = "Acftref.findByAccat", query = "SELECT a FROM Acftref a WHERE a.accat = :accat"),
    @NamedQuery(name = "Acftref.findByBuildcertind", query = "SELECT a FROM Acftref a WHERE a.buildcertind = :buildcertind"),
    @NamedQuery(name = "Acftref.findByNoeng", query = "SELECT a FROM Acftref a WHERE a.noeng = :noeng"),
    @NamedQuery(name = "Acftref.findByNoseats", query = "SELECT a FROM Acftref a WHERE a.noseats = :noseats"),
    @NamedQuery(name = "Acftref.findByAcweight", query = "SELECT a FROM Acftref a WHERE a.acweight = :acweight"),
    @NamedQuery(name = "Acftref.findBySpeed", query = "SELECT a FROM Acftref a WHERE a.speed = :speed"),
    @NamedQuery(name = "Acftref.findByLastcol", query = "SELECT a FROM Acftref a WHERE a.lastcol = :lastcol")})
public class Acftref implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "code")
    private String code;
    @Size(max = 50)
    @Column(name = "mfrname")
    private String mfrname;
    @Size(max = 20)
    @Column(name = "model")
    private String model;
    @Size(max = 20)
    @Column(name = "typeacft")
    private String typeacft;
    @Size(max = 20)
    @Column(name = "typeeng")
    private String typeeng;
    @Size(max = 20)
    @Column(name = "accat")
    private String accat;
    @Size(max = 20)
    @Column(name = "buildcertind")
    private String buildcertind;
    @Size(max = 20)
    @Column(name = "noeng")
    private String noeng;
    @Size(max = 20)
    @Column(name = "noseats")
    private String noseats;
    @Size(max = 20)
    @Column(name = "acweight")
    private String acweight;
    @Size(max = 20)
    @Column(name = "speed")
    private String speed;
    @Column(name = "lastcol")
    private Character lastcol;

    public Acftref() {
    }

    public Acftref(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMfrname() {
        return mfrname;
    }

    public void setMfrname(String mfrname) {
        this.mfrname = mfrname;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTypeacft() {
        return typeacft;
    }

    public void setTypeacft(String typeacft) {
        this.typeacft = typeacft;
    }

    public String getTypeeng() {
        return typeeng;
    }

    public void setTypeeng(String typeeng) {
        this.typeeng = typeeng;
    }

    public String getAccat() {
        return accat;
    }

    public void setAccat(String accat) {
        this.accat = accat;
    }

    public String getBuildcertind() {
        return buildcertind;
    }

    public void setBuildcertind(String buildcertind) {
        this.buildcertind = buildcertind;
    }

    public String getNoeng() {
        return noeng;
    }

    public void setNoeng(String noeng) {
        this.noeng = noeng;
    }

    public String getNoseats() {
        return noseats;
    }

    public void setNoseats(String noseats) {
        this.noseats = noseats;
    }

    public String getAcweight() {
        return acweight;
    }

    public void setAcweight(String acweight) {
        this.acweight = acweight;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Character getLastcol() {
        return lastcol;
    }

    public void setLastcol(Character lastcol) {
        this.lastcol = lastcol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acftref)) {
            return false;
        }
        Acftref other = (Acftref) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fdorigo.rmfly.Acftref[ code=" + code + " ]";
    }
    
}
