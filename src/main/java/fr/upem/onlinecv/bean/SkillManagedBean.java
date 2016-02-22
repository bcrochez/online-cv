package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.Skill;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mdamis
 */
public class SkillManagedBean implements Serializable {

    private String label;

    private ProfileManagedBean profile;

    public SkillManagedBean() {
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(ProfileManagedBean profile) {
        this.profile = profile;
    }

    public void addSkill() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Skill skill = (Skill) session.createCriteria(Skill.class).add(Restrictions.eq("label", label)).uniqueResult();
        session.beginTransaction();
        if (skill == null) {
            System.out.println("null");
            skill = new Skill(label, profile.getUser());
        } else {
            System.out.println("not null");
            skill.addUser(profile.getUser());
        }
        session.save(skill);
        session.getTransaction().commit();
        session.close();

        redirectToProfile();
    }

    private void redirectToProfile() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/profile.xhtml?faces-redirect=true&id=" + profile.getUserId());
    }
    
    public void removeSkill(Skill skill) {
        Session session = HibernateUtil.getSessionFactory().openSession();
  
        session.beginTransaction();
        skill.removeUser(profile.getUser());
        session.update(skill);
        session.getTransaction().commit();
        session.close();
        
        redirectToProfile();
    }
}
