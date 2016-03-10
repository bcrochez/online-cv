package fr.upem.onlinecv.bean;

import fr.upem.onlinecv.model.HibernateUtil;
import fr.upem.onlinecv.model.Privacy;
import fr.upem.onlinecv.model.UserCv;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.faces.context.FacesContext;
import org.hibernate.Session;

/**
 *
 * @author Bastien
 */
public class SearchManagedBean implements Serializable {

    private enum SearchType {
        NAME, SKILL, POSITION, EDUCATION;
    }

    private static final int LIMIT = 10;

    private String query;
    private int type;
    private Set<UserCv> users = new HashSet<>();
    private Set<UserCv> previewUsers = new HashSet<>();

    private UserManagedBean connectedUser;

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
        return new ArrayList<>(users);
    }

    public ArrayList<UserCv> getPreviewUsers() {
        return new ArrayList<>(previewUsers);
    }

    public void setConnectedUser(UserManagedBean connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void search() {
        users.clear();
        previewUsers.clear();

        List<String> tokens = query.length() != 0 ? Arrays.asList(query.split(" ")) : Collections.EMPTY_LIST; // FIXME if query is empty shows nothing
        Set<UserCv> userSet = new HashSet<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        if (type == SearchType.NAME.ordinal()) {
            for (String token : tokens) {
                if (!token.equals("") && !token.equals(" ")) {
                    userSet.addAll(session.getNamedQuery("UserCv.findByName").setString("name", "%" + token + "%").list());
                }
            }
        } else if (type == SearchType.SKILL.ordinal()) {
            for (String token : tokens) {
                if (!token.equals("") && !token.equals(" ")) {
                    List<UserCv> users = session.getNamedQuery("UserCv.findBySkill").setString("label", "%" + token + "%").list();
                    for (UserCv user : users) {
                        if (canSeeSection("Skills", user)) {
                            userSet.add(user);
                        }
                    }
                }
            }
        } else if (type == SearchType.POSITION.ordinal()) {
            for (String token : tokens) {
                if (!token.equals("") && !token.equals(" ")) {
                    List<UserCv> users = session.getNamedQuery("UserCv.findByPosition").setString("title", "%" + token + "%").list();
                    for (UserCv user : users) {
                        if (canSeeSection("Experience", user)) {
                            userSet.add(user);
                        }
                    }
                }
            }
        } else if (type == SearchType.EDUCATION.ordinal()) {
            for (String token : tokens) {
                if (!token.equals("") && !token.equals(" ")) {
                    List<UserCv> users = session.getNamedQuery("UserCv.findByEducation").setString("title", "%" + token + "%").list();
                    for (UserCv user : users) {
                        if (canSeeSection("Education", user)) {
                            userSet.add(user);
                        }
                    }
                }
            }
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

    //TODO Refactor
    public boolean canSeeSection(String sectionName, UserCv user) {
        if (isOwnProfile(user)) {
            return true;
        }

        int privacy;
        switch (sectionName) {
            case "Education":
                privacy = user.getEducationPrivacy();
                break;
            case "Experience":
                privacy = user.getExperiencesPrivacy();
                break;
            case "Skills":
                privacy = user.getSkillsPrivacy();
                break;
            case "Languages":
                privacy = user.getLanguagesPrivacy();
                break;
            default:
                return false;
        }

        if (privacy == Privacy.PRIVATE.getValue()) {
            return isOwnProfile(user);
        } else if (privacy == Privacy.USERS.getValue()) {
            return connectedUser.isLogin();
        } else if (privacy == Privacy.CONNECTIONS.getValue()) {
            return (user.getHasConnectionSet().contains(connectedUser.getUser()) || user.getConnectionsSet().contains(connectedUser.getUser()));
        } else {
            return true;
        }
    }

    public boolean isOwnProfile(UserCv user) {
        if (connectedUser.isLogin()) {
            return Objects.equals(user.getUserId(), connectedUser.getUser().getUserId());
        }
        return false;
    }
}
