package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.UserEntity;
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

        // on teste que l'adresse email n'est pas déjà utilisée
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserEntity user = (UserEntity) session.createCriteria(UserEntity.class).add(Restrictions.eq("email", email)).uniqueResult();

        if (user == null) {
            // si l'utilisateur n'existe pas c'est bon
            session.beginTransaction();
            
            // on crypte le mot de passe
            digestPassword();
            // on insère l'utilisateur dans la base
            UserEntity newUser = new UserEntity(firstName, lastName, email, password);
            session.save(newUser);
            session.getTransaction().commit();

            session.close();

            // l'utilisateur est maintenant connecté
            isLogin = true;
            redirectToIndex();
        } else {
            // l'utilisateur existe déjà
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cette adresse mail est déjà utilisée.", ""));
        }

    }

    public void signIn() {
        System.out.println(" email = " + email + " - password = " + password);

        // on vérifie que l'adresse mail existe et que le mot de passe est correct
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserEntity user = (UserEntity) session.createCriteria(UserEntity.class).add(Restrictions.eq("email", email)).uniqueResult();

        // on crypte le mot de passe
        digestPassword();

        session.close();

        // on récupére les infos de l'utilisateur
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

    private void digestPassword() {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            password = new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException ex) {
            
        }
    }

    private void redirectToIndex() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
    }
}
