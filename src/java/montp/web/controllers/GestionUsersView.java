package montp.web.controllers;

import montp.data.dao.GroupDAO;
import montp.data.dao.UserDAO;
import montp.data.model.security.Group;
import montp.data.model.security.User;
import montp.locale.Messages;
import montp.web.FacesTools;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionalException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ViewScoped
@Named
public class GestionUsersView implements Serializable {
    @Inject
    private UserDAO res;
    @Inject
    private User user;
    private List<User> rst;
    private Messages messages;
    private List<Group> groupList;
    @Inject
    private GroupDAO groupDAO;
    @PostConstruct
    public void init() {
        rst = res.getUsers();
    }
    public Collection<User> getRestyp() {

        return res.getUsers();
    }
    public void create(){
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Messages getMessages() {
        return messages;
    }

    public void save(){
        if (user.getId() == null) {

            user.setGroups(groupList.subList(0,1));
            System.out.print("hola"+ Arrays.toString(groupList.subList(0,1).toArray()));
            user.setActive(true);
            res.insert(user);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Utilisateur créé");
        } else {
            res.update(user);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }



    public void delete(User user) {
        try {
            res.delete(user);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                messages.get("app.delete.error"),
                user);
        }
    }
    public void update(User user) {
        try {

            res.update(user);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                "Impossible de metre à jour user %s car il fait partie  des resources de ce type",
                user);
        }
    }

}
