package com.reina.marco.sms.eis;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.reina.marco.sms.domain.User;

@Stateless
public class UserDaoImpl implements UserDao {
	
    @PersistenceContext(unitName = "PersonPU")
    EntityManager em;

    public List<User> findAllUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    public User findUserById(User user) {
        return (User)em.find(User.class, user.getIdUser());
    }

    public void insertUser(User user) {
        em.persist(user);
    }

    public void updateUser(User user) {
        em.merge(user);
    }

    public void deleteUser(User user) {
        em.merge(user);
        em.remove(user);
    }
}
