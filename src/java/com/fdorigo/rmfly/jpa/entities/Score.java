/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
 * @author fdorigo
 */
@Entity
@Table(name = "score")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s"),
    @NamedQuery(name = "Score.findById", query = "SELECT s FROM Score s WHERE s.id = :id"),
    @NamedQuery(name = "Score.findByNnumber", query = "SELECT s FROM Score s WHERE s.nnumber = :nnumber"),
    @NamedQuery(name = "Score.findByDate", query = "SELECT s FROM Score s WHERE s.date = :date"),
    @NamedQuery(name = "Score.findByOwnerName", query = "SELECT s FROM Score s WHERE s.ownerName = :ownerName"),
    @NamedQuery(name = "Score.findByJudgeName", query = "SELECT s FROM Score s WHERE s.judgeName = :judgeName"),
    @NamedQuery(name = "Score.findByCategory", query = "SELECT s FROM Score s WHERE s.category = :category")})
public class Score implements Serializable {
    @Column(name = "scoreOverall")
    private Short scoreOverall;
    @Column(name = "scoreFuselage")
    private Short scoreFuselage;
    @Column(name = "scoreLifts")
    private Short scoreLifts;
    @Column(name = "scorePitch")
    private Short scorePitch;
    @Column(name = "scoreLanding")
    private Short scoreLanding;
    @Column(name = "scoreCockpit")
    private Short scoreCockpit;
    @Column(name = "scorePower")
    private Short scorePower;
    @Column(name = "scoreFinish")
    private Integer scoreFinish;
    @Column(name = "scoreInnovation")
    private Integer scoreInnovation;
    @Lob
    @Size(max = 65535)
    @Column(name = "judgeNotes")
    private String judgeNotes;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "owner_name")
    private String ownerName;
    @Size(max = 50)
    @Column(name = "judge_name")
    private String judgeName;
    @Size(max = 50)
    @Column(name = "category")
    private String category;
    @JoinColumn(name = "nnumber", referencedColumnName = "nnumber")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Record nnumber;

    public Score() {
    }

    public Score(Integer id) {
        this.id = id;
    }

    public Score(Integer id, String ownerName) {
        this.id = id;
        this.ownerName = ownerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Record getNnumber() {
        return nnumber;
    }

    public void setNnumber(Record nnumber) {
        this.nnumber = nnumber;
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
        if (!(object instanceof Score)) {
            return false;
        }
        Score other = (Score) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fdorigo.rmfly.jpa.entities.Score[ id=" + id + " ]";
    }

    public Short getScoreOverall() {
        return scoreOverall;
    }

    public void setScoreOverall(Short scoreOverall) {
        this.scoreOverall = scoreOverall;
    }

    public Short getScoreFuselage() {
        return scoreFuselage;
    }

    public void setScoreFuselage(Short scoreFuselage) {
        this.scoreFuselage = scoreFuselage;
    }

    public Short getScoreLifts() {
        return scoreLifts;
    }

    public void setScoreLifts(Short scoreLifts) {
        this.scoreLifts = scoreLifts;
    }

    public Short getScorePitch() {
        return scorePitch;
    }

    public void setScorePitch(Short scorePitch) {
        this.scorePitch = scorePitch;
    }

    public Short getScoreLanding() {
        return scoreLanding;
    }

    public void setScoreLanding(Short scoreLanding) {
        this.scoreLanding = scoreLanding;
    }

    public Short getScoreCockpit() {
        return scoreCockpit;
    }

    public void setScoreCockpit(Short scoreCockpit) {
        this.scoreCockpit = scoreCockpit;
    }

    public Short getScorePower() {
        return scorePower;
    }

    public void setScorePower(Short scorePower) {
        this.scorePower = scorePower;
    }

    public Integer getScoreFinish() {
        return scoreFinish;
    }

    public void setScoreFinish(Integer scoreFinish) {
        this.scoreFinish = scoreFinish;
    }

    public Integer getScoreInnovation() {
        return scoreInnovation;
    }

    public void setScoreInnovation(Integer scoreInnovation) {
        this.scoreInnovation = scoreInnovation;
    }

    public String getJudgeNotes() {
        return judgeNotes;
    }

    public void setJudgeNotes(String judgeNotes) {
        this.judgeNotes = judgeNotes;
    }
    
}
