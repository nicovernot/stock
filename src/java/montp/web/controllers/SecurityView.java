package montp.web.controllers;

import montp.data.dao.GroupDAO;
import montp.data.dao.UserDAO;
import montp.data.model.security.Group;
import montp.data.model.security.User;
import montp.web.FacesTools;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@ViewScoped
@Named
public class SecurityView implements Serializable {


    private User user;
    @Inject
    private UserDAO userDAO;

    private List<Group> groupList;
    @Inject
    private GroupDAO groupDAO;
    private String pwd;
    @PostConstruct
    public void init() {
    groupList = groupDAO.getAll();

    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void save(){
        if (user.getId() == null) {

            user.setGroups(groupList.subList(0,1));
            System.out.print("hola"+ Arrays.toString(groupList.subList(0,1).toArray()));
            user.setActive(true);
            userDAO.insert(user);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Utilisateur créé");
        } else {
            userDAO.update(user);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

    public void create(){
this.user = new User();
this.user.setActive(true);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
