
package fr.upem.onlinecv.bean;

import java.io.Serializable;

/**
 *
 * @author Bastien
 */
public class UserManagedBean implements Serializable {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    
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
}
