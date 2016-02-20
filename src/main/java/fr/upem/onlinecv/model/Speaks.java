/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.upem.onlinecv.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mdamis
 */
@Entity
@Table(name = "speaks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Speaks.findAll", query = "SELECT s FROM Speaks s"),
    @NamedQuery(name = "Speaks.findByProficiency", query = "SELECT s FROM Speaks s WHERE s.proficiency = :proficiency"),
    @NamedQuery(name = "Speaks.findByUserId", query = "SELECT s FROM Speaks s WHERE s.speaksPK.userId = :userId"),
    @NamedQuery(name = "Speaks.findByLanguageId", query = "SELECT s FROM Speaks s WHERE s.speaksPK.languageId = :languageId")})
public class Speaks implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpeaksPK speaksPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proficiency")
    private int proficiency;
    @JoinColumn(name = "language_id", referencedColumnName = "language_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Language language;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserCv userCv;

    public Speaks() {
    }

    public Speaks(SpeaksPK speaksPK) {
        this.speaksPK = speaksPK;
    }

    public Speaks(SpeaksPK speaksPK, int proficiency) {
        this.speaksPK = speaksPK;
        this.proficiency = proficiency;
    }

    public Speaks(int userId, int languageId) {
        this.speaksPK = new SpeaksPK(userId, languageId);
    }

    public SpeaksPK getSpeaksPK() {
        return speaksPK;
    }

    public void setSpeaksPK(SpeaksPK speaksPK) {
        this.speaksPK = speaksPK;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public UserCv getUserCv() {
        return userCv;
    }

    public void setUserCv(UserCv userCv) {
        this.userCv = userCv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (speaksPK != null ? speaksPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Speaks)) {
            return false;
        }
        Speaks other = (Speaks) object;
        if ((this.speaksPK == null && other.speaksPK != null) || (this.speaksPK != null && !this.speaksPK.equals(other.speaksPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.upem.onlinecv.model.Speaks[ speaksPK=" + speaksPK + " ]";
    }
    
}
