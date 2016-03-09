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

    private static final int LIMIT = 10;

    private String query;
    private Set<UserCv> users = new HashSet<>();
    private Set<UserCv> previewUsers = new HashSet<>();

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
        return new ArrayList<>(users);
    }

    public ArrayList<UserCv> getPreviewUsers() {
        return new ArrayList<>(previewUsers);
    }

    public void search() {
        System.out.println("query " + query);

        users.clear();
        previewUsers.clear();

        List<String> tokens = query.length() != 0 ? Arrays.asList(query.split(" ")) : Collections.EMPTY_LIST; // FIXME if query is empty shows nothing
        Set<UserCv> userSet = new HashSet<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        for (String token : tokens) {
            userSet.addAll(session.getNamedQuery("UserCv.findByName").setString("name", "%" + token + "%").list());
        }
        session.close();

        users.addAll(userSet);

        if (users.size() > LIMIT) {
            ArrayList<UserCv> userList = new ArrayList<>(users);
            previewUsers = new HashSet<>(userList.subList(0, LIMIT));
        } else {
            previewUsers = users;
        }
    }

    public void searchAndNavigate() {
        search();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/search.xhtml?faces-redirect=true");
    }
}
