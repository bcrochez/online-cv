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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Bastien
 */
@Entity
@Table(name = "SKILL", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID") })
@NamedQueries({
    @NamedQuery(name = "SkillEntity.findAll", query = "SELECT s FROM SkillEntity s"),
    @NamedQuery(name = "SkillEntity.findById", query = "SELECT s FROM SkillEntity s WHERE s.id = :id"),
    @NamedQuery(name = "SkillEntity.findByUserId", query = "SELECT s FROM SkillEntity s WHERE s.userId = :userId")
})
public class SkillEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "TITLE", unique = false, nullable = false, length = 100)
    private String title;
    
    @Column(name = "USER_ID", unique = false, nullable = false)
    private long userId;

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkillEntity)) {
            return false;
        }
        SkillEntity other = (SkillEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.upem.onlinecv.model.SkillEntity[ id=" + id + " ]";
    }
    
}
