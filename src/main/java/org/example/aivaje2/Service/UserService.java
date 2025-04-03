package org.example.aivaje2.Service;

import org.example.aivaje2.COR.iPolnilnicaHandler;
import org.example.aivaje2.DAO.UserDAO;
import org.example.aivaje2.DAO.iUserDAO;
import org.example.aivaje2.VAO.User;

public class UserService {
    private final iUserDAO userDAO = UserDAO.getInstance();

    iPolnilnicaHandler PolnilnicaNaVoljo;

    public void dodajUser(User user) {
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
}
