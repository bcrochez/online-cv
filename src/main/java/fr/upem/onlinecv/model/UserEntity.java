package fr.upem.onlinecv.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "USER_CV", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "EMAIL") }) 
@NamedQueries({
    @NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u"),
    @NamedQuery(name = "UserEntity.findById", query = "SELECT u FROM UserEntity u WHERE u.id = :id"),
    @NamedQuery(name = "UserEntity.findByFirstName", query = "SELECT u FROM UserEntity u WHERE u.firstName LIKE :firstName"),
    @NamedQuery(name = "UserEntity.findByLastName", query = "SELECT u FROM UserEntity u WHERE u.lastName LIKE :lastName"),
    @NamedQuery(name = "UserEntity.findByName", query = "SELECT u FROM UserEntity u WHERE u.firstName LIKE :name OR u.lastName LIKE :name")
})

public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
    private String firstName;
    
    @Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
    private String lastName;
    
    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;
    
    @Column(name = "PASSWORD", unique = false, nullable = false, length = 100)
    private String password;

    protected UserEntity() {}
    
    public UserEntity(String firstName, String lastName, String email, String password) {
        //this.id = id;
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
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
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