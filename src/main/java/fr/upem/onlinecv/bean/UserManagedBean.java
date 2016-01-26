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
            redirectToIndex();
        }

    }

    public void checkLogin() {
        if (isLogin) {
            redirectToIndex();
        }
    }
    
    public void signUp() {
        System.out.println("First name = "+firstName+" - Last name = "+lastName+" - email = "+email+" - password = "+password);
        
        // TODO tester que l'adresse email n'est pas déjà utilisée
        
        // l'utilisateur est maintenant connecté
        isLogin = true;
        redirectToIndex();
    }
    
    public void signIn() {
        System.out.println(" email = "+email+" - password = "+password);
        
        // TODO vérifier que l'adresse mail existe et que le mot de passe est correct
        
        // TODO récupérer le prénom et le nom
        
        // l'utilisateur est maintenant connecté
        isLogin = true;
        redirectToIndex();
    }
    
    private void redirectToIndex() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
    }
}
