package org.example.aivaje2.DAO;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import org.example.aivaje2.VAO.User;

import java.util.List;

@Local
public interface iUserDAO {
    public void insertUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public List<User> getAllUsers();
    public User getUser(User user);
}
