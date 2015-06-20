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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author fdorigo
 */
@Entity
@Table(name = "judge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Judge.findAll", query = "SELECT j FROM Judge j"),
    @NamedQuery(name = "Judge.findById", query = "SELECT j FROM Judge j WHERE j.id = :id"),
    @NamedQuery(name = "Judge.findByFirstName", query = "SELECT j FROM Judge j WHERE j.firstName = :firstName"),
    @NamedQuery(name = "Judge.findByLastName", query = "SELECT j FROM Judge j WHERE j.lastName = :lastName"),
    @NamedQuery(name = "Judge.findByFirstLastName", query = "SELECT j FROM Judge j WHERE j.firstName = :firstName AND j.lastName = :lastName"),
    @NamedQuery(name = "Judge.findByPhoneNumber", query = "SELECT j FROM Judge j WHERE j.phoneNumber = :phoneNumber")})
public class Judge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 45)
    @Column(name = "last_namel")
    private String lastName;
    @Size(max = 15)
    @Column(name = "phone_number")
    private String phoneNumber;

    public Judge() {
    }

    public Judge(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = StringUtils.capitalize(firstName.toLowerCase());
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = StringUtils.capitalize(lastName.toLowerCase());;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Judge)) {
            return false;
        }
        Judge other = (Judge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
