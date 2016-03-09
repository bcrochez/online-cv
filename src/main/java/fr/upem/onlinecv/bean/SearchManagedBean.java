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

    private enum SearchType {
        NAME, SKILL;
    }

    private String query;
    private int type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<UserCv> getUsers() {
        return users;
    }

    public void search() {
        users.clear();

        List<String> tokens = query.length() != 0 ? Arrays.asList(query.split(" ")) : Collections.EMPTY_LIST; // FIXME if query is empty shows nothing
        Set<UserCv> userSet = new HashSet<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        if (type == SearchType.NAME.ordinal()) {
            for (String token : tokens) {
                userSet.addAll(session.getNamedQuery("UserCv.findByName").setString("name", "%" + token + "%").list());
            }
        } else if (type == SearchType.SKILL.ordinal()) {
            for (String token : tokens) {
                userSet.addAll(session.getNamedQuery("UserCv.findBySkill").setString("label", "%" + token + "%").list());
            }
        }
        session.close();

        users.addAll(userSet);
        query = "";

        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/search.xhtml?faces-redirect=true");
    }
}
