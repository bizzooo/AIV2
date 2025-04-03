package org.example.aivaje2.DAO;

import org.example.aivaje2.VAO.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO implements iUserDAO{
    private final List<User> users = Collections.synchronizedList(new ArrayList<User>());
    private static volatile UserDAO instance = null;

    private UserDAO() {}

    public static UserDAO getInstance() {
        if(instance == null) {
            synchronized (UserDAO.class) {
                if(instance == null) {
                    instance = new UserDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public void insertUser(User user) {
        synchronized (users) {
            users.add(user);
        }
    }

    @Override
    public void updateUser(User user) {
        synchronized (users) {
            users.stream().filter(u -> u == user).findFirst().ifPresent(u -> users.set(users.indexOf(u), user));
        }
    }

    @Override
    public void deleteUser(User user) {
        synchronized (users) {
            users.remove(user);
        }
    }
}
