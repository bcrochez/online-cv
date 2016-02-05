package fr.upem.onlinecv.bean;

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
        this.query = query;
    }
}
