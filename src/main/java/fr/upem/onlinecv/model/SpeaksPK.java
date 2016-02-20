/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.upem.onlinecv.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mdamis
 */
@Embeddable
public class SpeaksPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "language_id")
    private int languageId;

    public SpeaksPK() {
    }

    public SpeaksPK(int userId, int languageId) {
        this.userId = userId;
        this.languageId = languageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) languageId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpeaksPK)) {
            return false;
        }
        SpeaksPK other = (SpeaksPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.languageId != other.languageId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.upem.onlinecv.model.SpeaksPK[ userId=" + userId + ", languageId=" + languageId + " ]";
    }
    
}
