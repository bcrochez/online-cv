package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Bastien
 */
public class SearchManagedBean {

    private String query;
    private ArrayList<UserEntity> users = new ArrayList();

    /**
     * Creates a new instance of SearchManagedBean
     */
    public SearchManagedBean() {
    }

    public String getQuery() {
        return query;
    }
    
    public void setQuery(String query) {
        System.out.println("set query : "+query);
        this.query = query;
    }
    
    public ArrayList<UserEntity> getUsers() {
        return users;
    }
    
    public void search() {
        users.clear();
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query q = session.getNamedQuery("UserEntity.findByName");
        q.setString("name", "%" + query + "%");
        users.addAll(q.list());
        
        session.close();
        
        for(UserEntity user : users) {
            System.out.println(user.getFirstName() + " " + user.getLastName());
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/search.xhtml?faces-redirect=true");
    }
}
