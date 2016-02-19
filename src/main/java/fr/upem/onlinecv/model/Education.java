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
@Table(name = "EDUCATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Education.findAll", query = "SELECT e FROM Education e"),
    @NamedQuery(name = "Education.findByEducationId", query = "SELECT e FROM Education e WHERE e.educationId = :educationId"),
    @NamedQuery(name = "Education.findByTitle", query = "SELECT e FROM Education e WHERE e.title = :title"),
    @NamedQuery(name = "Education.findBySchoolName", query = "SELECT e FROM Education e WHERE e.schoolName = :schoolName"),
    @NamedQuery(name = "Education.findByLocation", query = "SELECT e FROM Education e WHERE e.location = :location"),
    @NamedQuery(name = "Education.findByStartMonth", query = "SELECT e FROM Education e WHERE e.startMonth = :startMonth"),
    @NamedQuery(name = "Education.findByStartYear", query = "SELECT e FROM Education e WHERE e.startYear = :startYear"),
    @NamedQuery(name = "Education.findByEndMonth", query = "SELECT e FROM Education e WHERE e.endMonth = :endMonth"),
    @NamedQuery(name = "Education.findByEndYear", query = "SELECT e FROM Education e WHERE e.endYear = :endYear"),
    @NamedQuery(name = "Education.findByIsCurrent", query = "SELECT e FROM Education e WHERE e.isCurrent = :isCurrent"),
@NamedQuery(name = "Education.findByUserId", query = "SELECT e FROM Education e WHERE e.userId.userId = :userId")
})
public class Education implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "education_id")
    private Integer educationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
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

    public Education() {
    }

    public Education(Integer educationId) {
        this.educationId = educationId;
    }

    public Education(Integer educationId, String title, String schoolName) {
        this.educationId = educationId;
        this.title = title;
        this.schoolName = schoolName;
    }
    
    public Education(String title, String schoolName, String location, String startMonth, int startYear, String endMonth, int endYear, String description, boolean isCurrent, UserCv user) {
        this.title = title;
        this.schoolName = schoolName;
        this.location = location;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.description = description;
        this.isCurrent = isCurrent;
        this.userId = user;
    }

    public Integer getEducationId() {
        return educationId;
    }

    public void setEducationId(Integer educationId) {
        this.educationId = educationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        hash += (educationId != null ? educationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Education)) {
            return false;
        }
        Education other = (Education) object;
        if ((this.educationId == null && other.educationId != null) || (this.educationId != null && !this.educationId.equals(other.educationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.upem.onlinecv.model.Education[ educationId=" + educationId + " ]";
    }
    
}
