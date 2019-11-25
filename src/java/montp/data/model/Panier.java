package montp.data.model;


import montp.data.model.security.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Panier extends GenericEntity implements Serializable {

    private double cote;

    private String siegle;
    @ManyToOne
    private User user;
}
