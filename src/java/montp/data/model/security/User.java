package montp.data.model.security;


import montp.data.model.GenericEntity;
import montp.data.model.Panier;

import javax.persistence.*;
import java.util.List;



@Entity
@Table(name = "SECURITY_USER")
public class User extends GenericEntity {

    @Column(nullable = false)
    private Boolean active;
    @Column(nullable = false, unique = true, length = 50)
     private String userName;
    @Column(columnDefinition = "TEXT")
    private String password;
    @Column(columnDefinition = "TEXT")
    private String oldPassword;


    @ManyToMany
    @JoinTable(name = "SECURITY_USER_GROUP",
            joinColumns = @JoinColumn(name = "username",
                    referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "groupname",
                    referencedColumnName = "groupname"))
    private List<Group> groups;
    @OneToMany
    private List<Panier> panierList;

    public User() {
    }

    public List<Panier> getPanierList() {
        return panierList;
    }

    public void setPanierList(List<Panier> panierList) {
        this.panierList = panierList;
    }

    public User(String userName, String password, Boolean active) {
        this.userName = userName;
        this.password = password;
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public String toString() {
        return userName;
    }

}
