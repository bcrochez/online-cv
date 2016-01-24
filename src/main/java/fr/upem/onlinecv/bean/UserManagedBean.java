package fr.upem.onlinecv.bean;

import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bastien
 */
public class UserManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName = "Bastien";
    private String lastName = "Crochez";
    private String email;
    private String password;
    private boolean isLogin = true;

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
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

    public boolean isLogin() {
        return isLogin;
    }

    public void isLogin(boolean b) {
        isLogin = b;
    }

    public void logOut() {
        if(isLogin) {
            isLogin = false;
            FacesContext context = FacesContext.getCurrentInstance();
            context.getApplication().getNavigationHandler().handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
        }

    }

    public void checkLogin() {
        if (isLogin) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getApplication().getNavigationHandler().handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
        }
    }
}
