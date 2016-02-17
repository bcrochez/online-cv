/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.upem.onlinecv.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Bastien
 */
@Entity
@Table(name = "LANGUAGE_CV", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID") }) 
public class LanguageEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "USER_ID", unique = false, nullable = false)
    private long userId;
    
    @Column(name="TITLE", unique = false, nullable = false, length = 100)
    private String title;
    
    @Column(name="PROFICIENCY", unique = false, nullable = false, length = 100)
    private String proficiency;

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
    
    public String getProficiency() {
        return proficiency;
    }
    
    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
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
        if (!(object instanceof LanguageEntity)) {
            return false;
        }
        LanguageEntity other = (LanguageEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.upem.onlinecv.model.LanguageEntity[ id=" + id + " ]";
    }
    
}
