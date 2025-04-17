package org.example.aivaje2.Service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.example.aivaje2.COR.iPolnilnicaHandler;
import org.example.aivaje2.DAO.UserDAO;
import org.example.aivaje2.DAO.iUserDAO;
import org.example.aivaje2.VAO.User;

import java.util.List;

@Stateless
public class UserService {
    @EJB
    private iUserDAO userDAO;

    iPolnilnicaHandler PolnilnicaNaVoljo;

    public User getUser(User user) {
        if(user.getEmail() == null){
            throw new IllegalArgumentException("Email is null");
        }
        return userDAO.getUser(user);
    }

    public void insertUser(User user) {
        if(user.getEmail() == null){
            throw new IllegalArgumentException("Email is null");
        }
        userDAO.insertUser(user);
    }

    public void posodobiUsera(User user) {
        if(user.getEmail() == null){
            throw new IllegalArgumentException("Email is null");
        }
        userDAO.updateUser(user);
    }

    public void odstraniUsera(User user) {
        if(user.getEmail() == null){
            throw new IllegalArgumentException("Email is null");
        }
        userDAO.deleteUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
