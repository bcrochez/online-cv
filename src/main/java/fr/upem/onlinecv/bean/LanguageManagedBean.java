
package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.Language;
import fr.upem.onlinecv.model.Speaks;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Bastien
 */
public class LanguageManagedBean {
    
    private String label;
    
    private String proficiency;
    
    private ProfileManagedBean profile;

    /**
     * Creates a new instance of LanguageManagedBean
     */
    public LanguageManagedBean() {
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
     * @return the proficiency
     */
    public String getProficiency() {
        return proficiency;
    }

    /**
     * @param proficiency the proficiency to set
     */
    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(ProfileManagedBean profile) {
        this.profile = profile;
    }
    
    public void addLanguage() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Language language = (Language) session.createCriteria(Language.class).add(Restrictions.eq("label", label)).uniqueResult();
        session.beginTransaction();
        int languageId;
        if (language == null) {
            System.out.println("null");
            language = new Language(label);
            languageId = (int) session.save(language);
        } else {
            System.out.println("not null");
            languageId = language.getLanguageId();
        }
        
        Speaks speaks = new Speaks(Integer.parseInt(proficiency), profile.getUserId(), languageId);
        session.save(speaks);
        session.getTransaction().commit();
        session.close();

        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/profile.xhtml?faces-redirect=true&id=" + profile.getUserId());
    }
    
}
