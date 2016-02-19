package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.UserCv;
import java.util.Objects;
import org.hibernate.Session;

/**
 *
 * @author mdamis
 */
public class ProfileManagedBean {
    
    private Integer userId;
    private UserCv user;
    
    private UserManagedBean connectedUser;
    
    public ProfileManagedBean() {}

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
        user = (UserCv) (session.getNamedQuery("UserCv.findByUserId").setInteger("userId", userId).uniqueResult());
        user.setEducationList(session.getNamedQuery("Education.findByUserId").setInteger("userId", userId).list());
        session.close();
    }
    
    public boolean isOwnProfile() {
        if(connectedUser.isLogin()) {
            return Objects.equals(this.userId, connectedUser.getUser().getUserId());
        }
        return false;
    }
}
