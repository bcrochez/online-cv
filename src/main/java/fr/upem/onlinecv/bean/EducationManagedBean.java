
package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.Education;
import fr.upem.onlinecv.model.HibernateUtil;
import javax.faces.context.FacesContext;
import org.hibernate.Session;


/**
 *
 * @author Bastien
 */
public class EducationManagedBean {
    
    private String title;
    private String school;
    private String location;
    
    private String startMonth;
    private String startYear;
    
    private String endMonth;
    private String endYear;
    
    private String description;
    private boolean isCurrent;
    
    private ProfileManagedBean profile;

    /**
     * Creates a new instance of EducationManagedBean
     */
    public EducationManagedBean() {
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the school
     */
    public String getSchool() {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the startMonth
     */
    public String getStartMonth() {
        return startMonth;
    }

    /**
     * @param startMonth the startMonth to set
     */
    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    /**
     * @return the startYear
     */
    public String getStartYear() {
        return startYear;
    }

    /**
     * @param startYear the startYear to set
     */
    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    /**
     * @return the endMonth
     */
    public String getEndMonth() {
        return endMonth;
    }

    /**
     * @param endMonth the endMonth to set
     */
    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    /**
     * @return the endYear
     */
    public String getEndYear() {
        return endYear;
    }

    /**
     * @param endYear the endYear to set
     */
    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the isCurrent
     */
    public boolean isIsCurrent() {
        return isCurrent;
    }

    /**
     * @param isCurrent the isCurrent to set
     */
    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }
    
    public void setProfile(ProfileManagedBean profile) {
        this.profile = profile;
    }
    
    public void addEducation() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Education education =  new Education(title, school, location, startMonth, startYear, endMonth, endYear, description, isCurrent, profile.getUser());
        
        session.beginTransaction();
        int id = (int) session.save(education);
        session.getTransaction().commit();
        session.close();
        
        redirectToProfile();

    }
    
    public void removeEducation(Education education) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        session.delete(education);
        session.getTransaction().commit();
        session.close();
        
        redirectToProfile();
    }

    private void redirectToProfile() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/profile.xhtml?faces-redirect=true&id="+profile.getUserId());
    }
    
    public void updateEducation(Education education) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        session.update(education);
        session.getTransaction().commit();
        
        session.close();
    }
}
