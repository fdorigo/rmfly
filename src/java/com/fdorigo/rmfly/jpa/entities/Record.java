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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author fdorigo
 */
@Entity
@Table(name = "record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r"),
    @NamedQuery(name = "Record.findByNnumber", query = "SELECT r FROM Record r WHERE r.nnumber = :nnumber"),
    @NamedQuery(name = "Record.findByFirstName", query = "SELECT r FROM Record r WHERE r.firstName = :firstName"),
    @NamedQuery(name = "Record.findByLastName", query = "SELECT r FROM Record r WHERE r.lastName = :lastName"),
    @NamedQuery(name = "Record.findByAddressOne", query = "SELECT r FROM Record r WHERE r.addressOne = :addressOne"),
    @NamedQuery(name = "Record.findByAddressTwo", query = "SELECT r FROM Record r WHERE r.addressTwo = :addressTwo"),
    @NamedQuery(name = "Record.findByAddressCity", query = "SELECT r FROM Record r WHERE r.addressCity = :addressCity"),
    @NamedQuery(name = "Record.findByAddressState", query = "SELECT r FROM Record r WHERE r.addressState = :addressState"),
    @NamedQuery(name = "Record.findByAddressZip", query = "SELECT r FROM Record r WHERE r.addressZip = :addressZip"),
    @NamedQuery(name = "Record.findByPrimaryPhone", query = "SELECT r FROM Record r WHERE r.primaryPhone = :primaryPhone"),
    @NamedQuery(name = "Record.findBySecondaryphone", query = "SELECT r FROM Record r WHERE r.secondaryphone = :secondaryphone"),
    @NamedQuery(name = "Record.findByEmailAddress", query = "SELECT r FROM Record r WHERE r.emailAddress = :emailAddress"),
    @NamedQuery(name = "Record.findByArrivalDate", query = "SELECT r FROM Record r WHERE r.arrivalDate = :arrivalDate"),
    @NamedQuery(name = "Record.findByAirplaneModel", query = "SELECT r FROM Record r WHERE r.airplaneModel = :airplaneModel"),
    @NamedQuery(name = "Record.findByAirplaneMake", query = "SELECT r FROM Record r WHERE r.airplaneMake = :airplaneMake"),
    @NamedQuery(name = "Record.findByManufactureYear", query = "SELECT r FROM Record r WHERE r.manufactureYear = :manufactureYear"),
    @NamedQuery(name = "Record.findByAirplaneColor", query = "SELECT r FROM Record r WHERE r.airplaneColor = :airplaneColor"),
    @NamedQuery(name = "Record.findByNumberOfOccupants", query = "SELECT r FROM Record r WHERE r.numberOfOccupants = :numberOfOccupants"),
    @NamedQuery(name = "Record.findByHomebase", query = "SELECT r FROM Record r WHERE r.homebase = :homebase"),
    @NamedQuery(name = "Record.findByNeedJudging", query = "SELECT r FROM Record r WHERE r.needJudging = :needJudging"),
    @NamedQuery(name = "Record.findByIsJudged", query = "SELECT r FROM Record r WHERE r.isJudged = :isJudged"),
    @NamedQuery(name = "Record.findByCategory", query = "SELECT r FROM Record r WHERE r.category = :category")})
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nnumber")
    private String nnumber;
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 50)
    @Column(name = "address_one")
    private String addressOne;
    @Size(max = 50)
    @Column(name = "address_two")
    private String addressTwo;
    @Size(max = 50)
    @Column(name = "address_city")
    private String addressCity;
    @Size(max = 50)
    @Column(name = "address_state")
    private String addressState;
    @Size(max = 50)
    @Column(name = "address_zip")
    private String addressZip;
    @Size(max = 50)
    @Column(name = "primary_phone")
    private String primaryPhone;
    @Size(max = 50)
    @Column(name = "secondaryphone")
    private String secondaryphone;
    @Size(max = 50)
    @Column(name = "email_address")
    private String emailAddress;
    @Size(max = 50)
    @Column(name = "arrival_date")
    private String arrivalDate;
    @Size(max = 50)
    @Column(name = "airplane_model")
    private String airplaneModel;
    @Size(max = 50)
    @Column(name = "airplane_make")
    private String airplaneMake;
    @Size(max = 50)
    @Column(name = "manufacture_year")
    private String manufactureYear;
    @Size(max = 50)
    @Column(name = "airplane_color")
    private String airplaneColor;
    @Size(max = 50)
    @Column(name = "number_of_occupants")
    private String numberOfOccupants;
    @Size(max = 50)
    @Column(name = "homebase")
    private String homebase;
    @Column(name = "need_judging")
    private Boolean needJudging;
    @Column(name = "is_judged")
    private Boolean isJudged;
    @Size(max = 50)
    @Column(name = "category")
    private String category;

    public Record() {
    }

    public Record(String nnumber) {
        this.nnumber = nnumber;
    }

    public Record(String nnumber, String firstName) {
        this.nnumber = nnumber;
        this.firstName = firstName;
    }

    public String getNnumber() {
        return nnumber;
    }

    public void setNnumber(String nnumber) {
        this.nnumber = nnumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondaryphone() {
        return secondaryphone;
    }

    public void setSecondaryphone(String secondaryphone) {
        this.secondaryphone = secondaryphone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public String getAirplaneMake() {
        return airplaneMake;
    }

    public void setAirplaneMake(String airplaneMake) {
        this.airplaneMake = airplaneMake;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getAirplaneColor() {
        return airplaneColor;
    }

    public void setAirplaneColor(String airplaneColor) {
        this.airplaneColor = airplaneColor;
    }

    public String getNumberOfOccupants() {
        return numberOfOccupants;
    }

    public void setNumberOfOccupants(String numberOfOccupants) {
        this.numberOfOccupants = numberOfOccupants;
    }

    public String getHomebase() {
        return homebase;
    }

    public void setHomebase(String homebase) {
        this.homebase = homebase;
    }

    public Boolean getNeedJudging() {
        return needJudging;
    }

    public void setNeedJudging(Boolean needJudging) {
        this.needJudging = needJudging;
    }

    public Boolean getIsJudged() {
        return isJudged;
    }

    public void setIsJudged(Boolean isJudged) {
        this.isJudged = isJudged;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        if (!(object instanceof Record)) {
            return false;
        }
        Record other = (Record) object;
        if ((this.nnumber == null && other.nnumber != null) || (this.nnumber != null && !this.nnumber.equals(other.nnumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("nnum", nnumber)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .toString();
    }

}
