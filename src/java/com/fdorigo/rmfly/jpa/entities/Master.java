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
@Table(name = "master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Master.findAll", query = "SELECT m FROM Master m"),
    @NamedQuery(name = "Master.findByNnumber", query = "SELECT m FROM Master m WHERE m.nnumber = :nnumber"),
    @NamedQuery(name = "Master.findBySerialnum", query = "SELECT m FROM Master m WHERE m.serialnum = :serialnum"),
    @NamedQuery(name = "Master.findByMfrmdlcode", query = "SELECT m FROM Master m WHERE m.mfrmdlcode = :mfrmdlcode"),
    @NamedQuery(name = "Master.findByEngmfrmdl", query = "SELECT m FROM Master m WHERE m.engmfrmdl = :engmfrmdl"),
    @NamedQuery(name = "Master.findByYearmfr", query = "SELECT m FROM Master m WHERE m.yearmfr = :yearmfr"),
    @NamedQuery(name = "Master.findByTypereg", query = "SELECT m FROM Master m WHERE m.typereg = :typereg"),
    @NamedQuery(name = "Master.findByName", query = "SELECT m FROM Master m WHERE m.name = :name"),
    @NamedQuery(name = "Master.findByStreet", query = "SELECT m FROM Master m WHERE m.street = :street"),
    @NamedQuery(name = "Master.findByStreet2", query = "SELECT m FROM Master m WHERE m.street2 = :street2"),
    @NamedQuery(name = "Master.findByCity", query = "SELECT m FROM Master m WHERE m.city = :city"),
    @NamedQuery(name = "Master.findByState", query = "SELECT m FROM Master m WHERE m.state = :state"),
    @NamedQuery(name = "Master.findByZip", query = "SELECT m FROM Master m WHERE m.zip = :zip"),
    @NamedQuery(name = "Master.findByRegion", query = "SELECT m FROM Master m WHERE m.region = :region"),
    @NamedQuery(name = "Master.findByCounty", query = "SELECT m FROM Master m WHERE m.county = :county"),
    @NamedQuery(name = "Master.findByCountry", query = "SELECT m FROM Master m WHERE m.country = :country"),
    @NamedQuery(name = "Master.findByLastactiondate", query = "SELECT m FROM Master m WHERE m.lastactiondate = :lastactiondate"),
    @NamedQuery(name = "Master.findByCertissuedate", query = "SELECT m FROM Master m WHERE m.certissuedate = :certissuedate"),
    @NamedQuery(name = "Master.findByCertification", query = "SELECT m FROM Master m WHERE m.certification = :certification"),
    @NamedQuery(name = "Master.findByTypeaircraft", query = "SELECT m FROM Master m WHERE m.typeaircraft = :typeaircraft"),
    @NamedQuery(name = "Master.findByTypeengine", query = "SELECT m FROM Master m WHERE m.typeengine = :typeengine"),
    @NamedQuery(name = "Master.findByStatuscode", query = "SELECT m FROM Master m WHERE m.statuscode = :statuscode"),
    @NamedQuery(name = "Master.findByModescode", query = "SELECT m FROM Master m WHERE m.modescode = :modescode"),
    @NamedQuery(name = "Master.findByFractowner", query = "SELECT m FROM Master m WHERE m.fractowner = :fractowner"),
    @NamedQuery(name = "Master.findByAirworthdate", query = "SELECT m FROM Master m WHERE m.airworthdate = :airworthdate"),
    @NamedQuery(name = "Master.findByOther1", query = "SELECT m FROM Master m WHERE m.other1 = :other1"),
    @NamedQuery(name = "Master.findByOther2", query = "SELECT m FROM Master m WHERE m.other2 = :other2"),
    @NamedQuery(name = "Master.findByOther3", query = "SELECT m FROM Master m WHERE m.other3 = :other3"),
    @NamedQuery(name = "Master.findByOther4", query = "SELECT m FROM Master m WHERE m.other4 = :other4"),
    @NamedQuery(name = "Master.findByOther5", query = "SELECT m FROM Master m WHERE m.other5 = :other5"),
    @NamedQuery(name = "Master.findByExpirationdate", query = "SELECT m FROM Master m WHERE m.expirationdate = :expirationdate"),
    @NamedQuery(name = "Master.findByUniqueid", query = "SELECT m FROM Master m WHERE m.uniqueid = :uniqueid"),
    @NamedQuery(name = "Master.findByKitmfr", query = "SELECT m FROM Master m WHERE m.kitmfr = :kitmfr"),
    @NamedQuery(name = "Master.findByKitmodel", query = "SELECT m FROM Master m WHERE m.kitmodel = :kitmodel"),
    @NamedQuery(name = "Master.findByModescodehex", query = "SELECT m FROM Master m WHERE m.modescodehex = :modescodehex"),
    @NamedQuery(name = "Master.findByLastcol", query = "SELECT m FROM Master m WHERE m.lastcol = :lastcol")})
public class Master implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nnumber")
    private String nnumber;
    @Size(max = 30)
    @Column(name = "serialnum")
    private String serialnum;
    @Size(max = 20)
    @Column(name = "mfrmdlcode")
    private String mfrmdlcode;
    @Size(max = 20)
    @Column(name = "engmfrmdl")
    private String engmfrmdl;
    @Size(max = 20)
    @Column(name = "yearmfr")
    private String yearmfr;
    @Size(max = 20)
    @Column(name = "typereg")
    private String typereg;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "street")
    private String street;
    @Size(max = 50)
    @Column(name = "street2")
    private String street2;
    @Size(max = 20)
    @Column(name = "city")
    private String city;
    @Size(max = 20)
    @Column(name = "state")
    private String state;
    @Size(max = 20)
    @Column(name = "zip")
    private String zip;
    @Size(max = 20)
    @Column(name = "region")
    private String region;
    @Size(max = 20)
    @Column(name = "county")
    private String county;
    @Size(max = 20)
    @Column(name = "country")
    private String country;
    @Size(max = 20)
    @Column(name = "lastactiondate")
    private String lastactiondate;
    @Size(max = 20)
    @Column(name = "certissuedate")
    private String certissuedate;
    @Size(max = 20)
    @Column(name = "certification")
    private String certification;
    @Size(max = 20)
    @Column(name = "typeaircraft")
    private String typeaircraft;
    @Size(max = 20)
    @Column(name = "typeengine")
    private String typeengine;
    @Size(max = 20)
    @Column(name = "statuscode")
    private String statuscode;
    @Size(max = 20)
    @Column(name = "modescode")
    private String modescode;
    @Size(max = 20)
    @Column(name = "fractowner")
    private String fractowner;
    @Size(max = 20)
    @Column(name = "airworthdate")
    private String airworthdate;
    @Size(max = 50)
    @Column(name = "other1")
    private String other1;
    @Size(max = 50)
    @Column(name = "other2")
    private String other2;
    @Size(max = 50)
    @Column(name = "other3")
    private String other3;
    @Size(max = 50)
    @Column(name = "other4")
    private String other4;
    @Size(max = 50)
    @Column(name = "other5")
    private String other5;
    @Size(max = 20)
    @Column(name = "expirationdate")
    private String expirationdate;
    @Size(max = 20)
    @Column(name = "uniqueid")
    private String uniqueid;
    @Size(max = 30)
    @Column(name = "kitmfr")
    private String kitmfr;
    @Size(max = 20)
    @Column(name = "KITMODEL")
    private String kitmodel;
    @Size(max = 20)
    @Column(name = "modescodehex")
    private String modescodehex;
    @Column(name = "lastcol")
    private Character lastcol;

    public Master() {
    }

    public Master(String nnumber) {
        this.nnumber = nnumber;
    }

    public String getNnumber() {
        return nnumber;
    }

    public void setNnumber(String nnumber) {
        this.nnumber = nnumber;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getMfrmdlcode() {
        return mfrmdlcode;
    }

    public void setMfrmdlcode(String mfrmdlcode) {
        this.mfrmdlcode = mfrmdlcode;
    }

    public String getEngmfrmdl() {
        return engmfrmdl;
    }

    public void setEngmfrmdl(String engmfrmdl) {
        this.engmfrmdl = engmfrmdl;
    }

    public String getYearmfr() {
        return yearmfr;
    }

    public void setYearmfr(String yearmfr) {
        this.yearmfr = yearmfr;
    }

    public String getTypereg() {
        return typereg;
    }

    public void setTypereg(String typereg) {
        this.typereg = typereg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLastactiondate() {
        return lastactiondate;
    }

    public void setLastactiondate(String lastactiondate) {
        this.lastactiondate = lastactiondate;
    }

    public String getCertissuedate() {
        return certissuedate;
    }

    public void setCertissuedate(String certissuedate) {
        this.certissuedate = certissuedate;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getTypeaircraft() {
        return typeaircraft;
    }

    public void setTypeaircraft(String typeaircraft) {
        this.typeaircraft = typeaircraft;
    }

    public String getTypeengine() {
        return typeengine;
    }

    public void setTypeengine(String typeengine) {
        this.typeengine = typeengine;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getModescode() {
        return modescode;
    }

    public void setModescode(String modescode) {
        this.modescode = modescode;
    }

    public String getFractowner() {
        return fractowner;
    }

    public void setFractowner(String fractowner) {
        this.fractowner = fractowner;
    }

    public String getAirworthdate() {
        return airworthdate;
    }

    public void setAirworthdate(String airworthdate) {
        this.airworthdate = airworthdate;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }

    public String getOther4() {
        return other4;
    }

    public void setOther4(String other4) {
        this.other4 = other4;
    }

    public String getOther5() {
        return other5;
    }

    public void setOther5(String other5) {
        this.other5 = other5;
    }

    public String getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(String expirationdate) {
        this.expirationdate = expirationdate;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getKitmfr() {
        return kitmfr;
    }

    public void setKitmfr(String kitmfr) {
        this.kitmfr = kitmfr;
    }

    public String getKitmodel() {
        return kitmodel;
    }

    public void setKitmodel(String kitmodel) {
        this.kitmodel = kitmodel;
    }

    public String getModescodehex() {
        return modescodehex;
    }

    public void setModescodehex(String modescodehex) {
        this.modescodehex = modescodehex;
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
        hash += (nnumber != null ? nnumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Master)) {
            return false;
        }
        Master other = (Master) object;
        if ((this.nnumber == null && other.nnumber != null) || (this.nnumber != null && !this.nnumber.equals(other.nnumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fdorigo.rmfly.Master[ nnumber=" + nnumber + " ]";
    }
    
}
