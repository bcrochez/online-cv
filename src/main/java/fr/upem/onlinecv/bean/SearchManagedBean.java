package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.UserEntity;
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
    
    public void search() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query q = session.getNamedQuery("UserEntity.findByName");
        q.setString("name", query);
        List<UserEntity> users = q.list();
        
        session.close();
        
        for(UserEntity user : users) {
            System.out.println(user);
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/search.xhtml?faces-redirect=true");
    }
}
