package fr.upem.onlinecv.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "USER") 
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM USER u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM USER u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM USER u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM USER u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM USER u WHERE u.firstName = :firstName OR u.lastName = :lastName")
})

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    protected User() {}
    
    public User(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    
    @Override
    public String toString() {
        return "User[id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}