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
@Table(name = "EXPERIENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experience.findAll", query = "SELECT e FROM Experience e"),
    @NamedQuery(name = "Experience.findByExperienceId", query = "SELECT e FROM Experience e WHERE e.experienceId = :experienceId"),
    @NamedQuery(name = "Experience.findByCompanyName", query = "SELECT e FROM Experience e WHERE e.companyName = :companyName"),
    @NamedQuery(name = "Experience.findByLocation", query = "SELECT e FROM Experience e WHERE e.location = :location"),
    @NamedQuery(name = "Experience.findByStartMonth", query = "SELECT e FROM Experience e WHERE e.startMonth = :startMonth"),
    @NamedQuery(name = "Experience.findByStartYear", query = "SELECT e FROM Experience e WHERE e.startYear = :startYear"),
    @NamedQuery(name = "Experience.findByEndMonth", query = "SELECT e FROM Experience e WHERE e.endMonth = :endMonth"),
    @NamedQuery(name = "Experience.findByEndYear", query = "SELECT e FROM Experience e WHERE e.endYear = :endYear"),
    @NamedQuery(name = "Experience.findByIsCurrent", query = "SELECT e FROM Experience e WHERE e.isCurrent = :isCurrent")})
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "experience_id")
    private Integer experienceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "company_name")
    private String companyName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "start_month")
    private String startMonth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_year")
    private int startYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "end_month")
    private String endMonth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_year")
    private int endYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_current")
    private boolean isCurrent;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private UserCv userId;

    public Experience() {
    }

    public Experience(Integer experienceId) {
        this.experienceId = experienceId;
    }

    public Experience(Integer experienceId, String companyName, String location, String description, String startMonth, int startYear, String endMonth, int endYear, boolean isCurrent) {
        this.experienceId = experienceId;
        this.companyName = companyName;
        this.location = location;
        this.description = description;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.isCurrent = isCurrent;
    }

    public Integer getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Integer experienceId) {
        this.experienceId = experienceId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
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
        hash += (experienceId != null ? experienceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Experience)) {
            return false;
        }
        Experience other = (Experience) object;
        if ((this.experienceId == null && other.experienceId != null) || (this.experienceId != null && !this.experienceId.equals(other.experienceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.upem.onlinecv.model.Experience[ experienceId=" + experienceId + " ]";
    }
    
}
