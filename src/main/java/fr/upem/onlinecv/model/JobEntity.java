package fr.upem.onlinecv.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Bastien
 */
@Entity
@Table(name = "JOB", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID") })
@NamedQueries({
    @NamedQuery(name = "JobEntity.findAll", query = "SELECT j FROM JobEntity j"),
    @NamedQuery(name = "JobEntity.findById", query = "SELECT j FROM JobEntity j WHERE j.id = :id"),
    @NamedQuery(name = "JobEntity.findByUserId", query = "SELECT j FROM JobEntity j WHERE j.userId = :userId")
})
public class JobEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "TITLE", unique = false, nullable = false, length = 100)
    private String title;
    
    @Column(name = "COMPANY", unique = false, nullable = false, length = 100)
    private String company;
    
    @Column(name = "LOCATION", unique = false, nullable = true, length = 100)
    private String location;
    
    @Column(name = "DESCRIPTION", unique = false, nullable = true, length = 1000)
    private String description;
    
    @Column(name = "START_DATE", unique = false, nullable = true)
    private Date startDate;
    
    @Column(name = "END_DATE", unique = false, nullable = true)
    private Date endDate;
    
    @Column(name = "USER_ID", unique = false, nullable = false)
    private long userId;
    
    @Column(name = "TYPE", unique = false, nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
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
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof JobEntity)) {
            return false;
        }
        JobEntity other = (JobEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "fr.upem.onlinecv.model.JobEntity[ id=" + id + " ]";
    }
    
}
