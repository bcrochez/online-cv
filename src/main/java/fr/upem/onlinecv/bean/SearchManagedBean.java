package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.UserCv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;
import org.hibernate.Session;

/**
 *
 * @author Bastien
 */
public class SearchManagedBean {

    private String query;
    private ArrayList<UserCv> users = new ArrayList();

    /**
     * Creates a new instance of SearchManagedBean
     */
    public SearchManagedBean() {
    }

    public String getQuery() {
        return query;
    }
    
    public void setQuery(String query) {
        this.query = query;
    }
    
    public ArrayList<UserCv> getUsers() {
        return users;
    }
    
    public void search() {
        users.clear();
        
        List<String> tokens = query.length() != 0 ? Arrays.asList(query.split(" ")) : Collections.EMPTY_LIST;
        Set<UserCv> userSet = new HashSet<>();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        for(String token : tokens) {
            userSet.addAll(session.getNamedQuery("UserCv.findByName").setString("name", "%" + token + "%").list());
        }     
        session.close();
   
        users.addAll(userSet);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/search.xhtml?faces-redirect=true");
    }
}
