package com.adamkoksal.WebServiceProject.Service;

import com.adamkoksal.WebServiceProject.Dao.UserDao;
import com.adamkoksal.WebServiceProject.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public void createUser(User user) throws SQLException {
        userDao.createUser(user);
    }

    public User getUserById(int id) throws SQLException {
        return userDao.getUserById(id);
    }

    public void deleteUserById(int id) throws SQLException {
        userDao.deleteUserById(id);
    }

    public void updateUserById(int id, User user) throws SQLException {
        userDao.updateUserById(id, user);
    }

    public void signUpUser(User user) throws SQLException {
        userDao.signUpUser(user);
    }

    public boolean loginUser(User user) throws SQLException {
        return userDao.loginUser(user);
    }

    public void approveUser(int id) throws SQLException {
        userDao.approveUser(id);
    }

    public boolean isManager(int id) throws SQLException {
        return userDao.isManager(id);
    }

    public int getId(User user) throws SQLException {
        return userDao.getId(user);
    }

    public List<User> getUserByUsername(String username) throws SQLException {
        return userDao.getUserByUsername(username);
    }
}
