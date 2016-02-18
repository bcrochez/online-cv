/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.upem.onlinecv.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mdamis
 */
@Entity
@Table(name = "PROFICIENCY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proficiency.findAll", query = "SELECT p FROM Proficiency p"),
    @NamedQuery(name = "Proficiency.findByProficiencyId", query = "SELECT p FROM Proficiency p WHERE p.proficiencyId = :proficiencyId"),
    @NamedQuery(name = "Proficiency.findByLevel", query = "SELECT p FROM Proficiency p WHERE p.level = :level")})
public class Proficiency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proficiency_id")
    private Integer proficiencyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "level")
    private String level;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proficiency")
    private List<Speaks> speaksList;

    public Proficiency() {
    }

    public Proficiency(Integer proficiencyId) {
        this.proficiencyId = proficiencyId;
    }

    public Proficiency(Integer proficiencyId, String level) {
        this.proficiencyId = proficiencyId;
        this.level = level;
    }

    public Integer getProficiencyId() {
        return proficiencyId;
    }

    public void setProficiencyId(Integer proficiencyId) {
        this.proficiencyId = proficiencyId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @XmlTransient
    public List<Speaks> getSpeaksList() {
        return speaksList;
    }

    public void setSpeaksList(List<Speaks> speaksList) {
        this.speaksList = speaksList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proficiencyId != null ? proficiencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proficiency)) {
            return false;
        }
        Proficiency other = (Proficiency) object;
        if ((this.proficiencyId == null && other.proficiencyId != null) || (this.proficiencyId != null && !this.proficiencyId.equals(other.proficiencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.upem.onlinecv.model.Proficiency[ proficiencyId=" + proficiencyId + " ]";
    }
    
}
