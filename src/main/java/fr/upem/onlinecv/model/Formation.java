/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.upem.onlinecv.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mdamis
 */
@Entity
@Table(name = "FORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formation.findAll", query = "SELECT f FROM Formation f"),
    @NamedQuery(name = "Formation.findByFormationId", query = "SELECT f FROM Formation f WHERE f.formationId = :formationId"),
    @NamedQuery(name = "Formation.findBySchoolName", query = "SELECT f FROM Formation f WHERE f.schoolName = :schoolName"),
    @NamedQuery(name = "Formation.findByLocation", query = "SELECT f FROM Formation f WHERE f.location = :location"),
    @NamedQuery(name = "Formation.findByStartMonth", query = "SELECT f FROM Formation f WHERE f.startMonth = :startMonth"),
    @NamedQuery(name = "Formation.findByStartYear", query = "SELECT f FROM Formation f WHERE f.startYear = :startYear"),
    @NamedQuery(name = "Formation.findByEndMonth", query = "SELECT f FROM Formation f WHERE f.endMonth = :endMonth"),
    @NamedQuery(name = "Formation.findByEndYear", query = "SELECT f FROM Formation f WHERE f.endYear = :endYear"),
    @NamedQuery(name = "Formation.findByIsCurrent", query = "SELECT f FROM Formation f WHERE f.isCurrent = :isCurrent"),
    @NamedQuery(name = "Formation.findByUserId", query = "SELECT f FROM Formation f WHERE f.userId.userId = :userId")
})
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "formation_id")
    private Integer formationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "school_name")
    private String schoolName;
    @Size(max = 100)
    @Column(name = "location")
    private String location;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 25)
    @Column(name = "start_month")
    private String startMonth;
    @Column(name = "start_year")
    private Integer startYear;
    @Size(max = 25)
    @Column(name = "end_month")
    private String endMonth;
    @Column(name = "end_year")
    private Integer endYear;
    @Column(name = "is_current")
    private Boolean isCurrent;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private UserCv userId;

    public Formation() {
    }

    public Formation(Integer formationId) {
        this.formationId = formationId;
    }

    public Formation(Integer formationId, String schoolName) {
        this.formationId = formationId;
        this.schoolName = schoolName;
    }

    public Integer getFormationId() {
        return formationId;
    }

    public void setFormationId(Integer formationId) {
        this.formationId = formationId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public UserCv getUserId() {
        return userId;
    }

    public void setUserId(UserCv userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formationId != null ? formationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.formationId == null && other.formationId != null) || (this.formationId != null && !this.formationId.equals(other.formationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.upem.onlinecv.model.Formation[ formationId=" + formationId + " ]";
    }
    
}
