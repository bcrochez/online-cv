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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "USER_CV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserCv.findAll", query = "SELECT u FROM UserCv u"),
    @NamedQuery(name = "UserCv.findByUserId", query = "SELECT u FROM UserCv u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserCv.findByEmail", query = "SELECT u FROM UserCv u WHERE u.email = :email"),
    @NamedQuery(name = "UserCv.findByFirstName", query = "SELECT u FROM UserCv u WHERE u.firstName LIKE :firstName"),
    @NamedQuery(name = "UserCv.findByLastName", query = "SELECT u FROM UserCv u WHERE u.lastName LIKE :lastName"),
    @NamedQuery(name = "UserCv.findByName", query = "SELECT u FROM UserCv u WHERE u.firstName LIKE :name OR u.lastName LIKE :name")
})
public class UserCv implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "last_name")
    private String lastName;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 25)
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @Column(name = "gender")
    private Boolean gender;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Column(name = "formations_privacy")
    private Integer formationsPrivacy;
    @Column(name = "experiences_privacy")
    private Integer experiencesPrivacy;
    @Column(name = "skills_privacy")
    private Integer skillsPrivacy;
    @Column(name = "languages_privacy")
    private Integer languagesPrivacy;
    @ManyToMany(mappedBy = "userCvList")
    private List<Skill> skillList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Formation> formationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCv")
    private List<Speaks> speaksList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Experience> experienceList;

    public UserCv() {
    }

    public UserCv(Integer userId) {
        this.userId = userId;
    }

    public UserCv(Integer userId, String email, String password, String firstName, String lastName) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserCv(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.experiencesPrivacy = Privacy.PUBLIC.ordinal();
        this.formationsPrivacy = Privacy.PUBLIC.ordinal();
        this.skillsPrivacy = Privacy.PUBLIC.ordinal();
        this.languagesPrivacy = Privacy.PUBLIC.ordinal();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFormationsPrivacy() {
        return formationsPrivacy;
    }

    public void setFormationsPrivacy(Integer formationsPrivacy) {
        this.formationsPrivacy = formationsPrivacy;
    }

    public Integer getExperiencesPrivacy() {
        return experiencesPrivacy;
    }

    public void setExperiencesPrivacy(Integer experiencesPrivacy) {
        this.experiencesPrivacy = experiencesPrivacy;
    }

    public Integer getSkillsPrivacy() {
        return skillsPrivacy;
    }

    public void setSkillsPrivacy(Integer skillsPrivacy) {
        this.skillsPrivacy = skillsPrivacy;
    }

    public Integer getLanguagesPrivacy() {
        return languagesPrivacy;
    }

    public void setLanguagesPrivacy(Integer languagesPrivacy) {
        this.languagesPrivacy = languagesPrivacy;
    }

    @XmlTransient
    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    @XmlTransient
    public List<Formation> getFormationList() {
        return formationList;
    }

    public void setFormationList(List<Formation> formationList) {
        this.formationList = formationList;
    }

    @XmlTransient
    public List<Speaks> getSpeaksList() {
        return speaksList;
    }

    public void setSpeaksList(List<Speaks> speaksList) {
        this.speaksList = speaksList;
    }

    @XmlTransient
    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCv)) {
            return false;
        }
        UserCv other = (UserCv) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.upem.onlinecv.model.UserCv[ userId=" + userId + " ]";
    }
    
}
