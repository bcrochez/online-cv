package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.UserCv;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;

/**
 *
 * @author mdamis
 */
public class ProfileManagedBean {
    
    private String userId;
    private UserCv user;
    
    public ProfileManagedBean() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public UserCv getUser() {
        return user;
    }

    public void setUser(UserCv user) {
        this.user = user;
    }
    
    public void onload() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        user = (UserCv) (session.getNamedQuery("UserCv.findByUserId").setString("userId", userId).uniqueResult());
        user.setEducationList(session.getNamedQuery("Education.findByUserId").setString("userId", userId).list());
        session.close();
    }
}
