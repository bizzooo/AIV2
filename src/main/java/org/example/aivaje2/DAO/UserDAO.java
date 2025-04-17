package org.example.aivaje2.DAO;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.aivaje2.VAO.User;

import java.util.List;


@Stateless
@Local(iUserDAO.class)
public class UserDAO implements iUserDAO{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        User managedUser = em.merge(user);
        em.remove(managedUser);
    }

    @Override
    public User getUser(User user) {
        return em.find(User.class, user.getId());
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

}
