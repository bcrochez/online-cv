package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.Privacy;
import fr.upem.onlinecv.model.UserCv;
import java.io.Serializable;
import java.util.Objects;
import javax.faces.context.FacesContext;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 *
 * @author mdamis
 */
public class ProfileManagedBean implements Serializable {

    private Integer userId;
    private UserCv user;

    private UserManagedBean connectedUser;

    public ProfileManagedBean() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserCv getUser() {
        return user;
    }

    public void setUser(UserCv user) {
        this.user = user;
    }

    public void setConnectedUser(UserManagedBean connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void onload() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        if(userId == null) {
            redirectToIndex();
        }
        
        user = (UserCv) (session.getNamedQuery("UserCv.findByUserId").setInteger("userId", userId).uniqueResult());
        
        if(user == null) {
            redirectToIndex();
        }
        
        Hibernate.initialize(user);
        Hibernate.initialize(user.getExperienceList());
        Hibernate.initialize(user.getEducationList());
        Hibernate.initialize(user.getSkillList());
        Hibernate.initialize(user.getSpeaksList());
        Hibernate.initialize(user.getHasConnectionSet());
        Hibernate.initialize(user.getConnectionsSet());
        Hibernate.initialize(user.getWantsConnectionSet());
        Hibernate.initialize(user.getRequestsSet());
        session.close();

        for (UserCv user : user.getConnectionsSet()) {
            System.out.println("ConnectionsSet " + user.getEmail());
        }
        for (UserCv user : user.getHasConnectionSet()) {
            System.out.println("HasConnectionsSet " + user.getEmail());
        }
        for (UserCv user : user.getWantsConnectionSet()) {
            System.out.println("WantsConnectionsSet " + user.getEmail());
        }
        for (UserCv user : user.getRequestsSet()) {
            System.out.println("RequestsSet " + user.getEmail());
        }
    }

    public boolean canSeeSection(String sectionName) {
        int privacy;
        switch (sectionName) {
            case "Education":
                privacy = user.getEducationPrivacy();
                break;
            case "Experience":
                privacy = user.getExperiencesPrivacy();
                break;
            case "Skills":
                privacy = user.getSkillsPrivacy();
                break;
            case "Languages":
                privacy = user.getLanguagesPrivacy();
                break;
            default:
                return false;
        }

        if (privacy == Privacy.PRIVATE.getValue()) {
            return isOwnProfile();
        } else if (privacy == Privacy.USERS.getValue()) {
            return connectedUser.isLogin();
        } else {
            return true;
        }
    }

    public boolean isOwnProfile() {
        if (connectedUser.isLogin()) {
            return Objects.equals(this.userId, connectedUser.getUser().getUserId());
        }
        return false;
    }

    public void updateProfile() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();

        session.close();
    }

    public boolean canSeeConnectionButton() {
        if (isOwnProfile()) {
            return false;
        }
        if (!connectedUser.isLogin()) {
            return false;
        }
        if (user.getHasConnectionSet().contains(connectedUser.getUser()) || user.getConnectionsSet().contains(connectedUser.getUser())) {
            return false;
        }
        if (user.getWantsConnectionSet().contains(connectedUser.getUser()) || user.getRequestsSet().contains(connectedUser.getUser())) {
            return false;
        }
        return true;
    }

    public void addConnectionRequest() {
        user.getWantsConnectionSet().add(connectedUser.getUser());

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();

        refresh();
    }

    public void acceptRequest(UserCv user) {
        this.user.getHasConnectionSet().add(user);

        deleteRequest(user);
    }

    public void deleteRequest(UserCv user) {
        this.user.getWantsConnectionSet().remove(user);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(this.user);
        session.getTransaction().commit();
        session.close();

        refresh();
    }

    private void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/profile.xhtml?faces-redirect=true&id=" + userId);
    }

    private void redirectToIndex() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
    }
    
}
