package montp.data.dao;

import montp.data.model.security.Group;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class GroupDAO {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Group> groupEvent;

    public Group get(int id) {
        return em.find(Group.class, id);
    }
    public Group getByName(String name) {
        try {
            return (Group) em.createQuery(
                "SELECT e FROM Group e WHERE e.groupName = :j")
                .setParameter("j", name)
                .setMaxResults(1)
                .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Group> getAll() {
        return em.createQuery("SELECT e FROM Group e")
            .getResultList();
    }



}
