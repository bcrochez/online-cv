package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.User;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Bastien
 */
public class UserManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isLogin = false;

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
        if (isLogin) {
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
        System.out.println("First name = " + firstName + " - Last name = " + lastName + " - email = " + email + " - password = " + password);

        // TODO tester que l'adresse email n'est pas déjà utilisée
        // l'utilisateur est maintenant connecté
        isLogin = true;
        redirectToIndex();
    }

    public void signIn() {
        System.out.println(" email = " + email + " - password = " + password);

        // TODO vérifier que l'adresse mail existe et que le mot de passe est correct
        Session session = HibernateUtil.getSessionFactory().openSession();

        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();

        if (user == null) {
            System.out.println("user null");
        } else {
            System.out.println("id : " + user.getId() + " - name : " + user.getFirstName() + " " + user.getLastName() + " - email pw : " + user.getEmail() + " " + user.getPassword());
        }
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            password = new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException ex) {

        }

        session.close();

        // TODO récupérer le prénom et le nom
        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Utilisateur avec l'adresse '" + email + "' n'existe pas.", ""));
        } else if (!user.getPassword().equals(password)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mot de passe incorrect.", ""));
        } else {
            id = user.getId();
            firstName = user.getFirstName();
            lastName = user.getLastName();

            // l'utilisateur est maintenant connecté
            isLogin = true;
            redirectToIndex();
        }

    }

    private void redirectToIndex() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
    }
}
