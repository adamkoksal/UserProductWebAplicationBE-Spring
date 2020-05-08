package com.adamkoksal.WebServiceProject.Dao;

import com.adamkoksal.WebServiceProject.Entity.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class UserDao {
    Connection myConn;
    Statement myStmt;
    List<User> users;

    public UserDao() throws SQLException {
        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_website","root",password);
        myStmt = myConn.createStatement();
    }

    public void refreshUserList() throws SQLException {
        users = new ArrayList<>();
        ResultSet myRs = myStmt.executeQuery("SELECT * FROM my_website.users");

        while (myRs.next()) {
            User user = new User();
            user.setId(myRs.getInt(1));
            user.setUsername(myRs.getString(2));
            user.setPassword(myRs.getString(3));
            user.setManager(myRs.getBoolean(4));
            user.setApproved(myRs.getBoolean(5));
            users.add(user);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        refreshUserList();
        return users;
    }

    public void createUser(User user) throws SQLException {
        String sql = String.format("INSERT INTO users (username, password, is_manager, is_approved) VALUES (\"%s\", \"%s\", \"%s\", \"%s\")",
                user.getUsername(), user.getPassword(), user.isManager(), user.isApproved());

        myStmt.executeUpdate(sql);
    }

    public User getUserById(int id) throws SQLException {
        return findUserById(id);
    }

    public List<User> getUserByUsername(String username) throws SQLException {
        refreshUserList();

        Optional<User> filteredUser = users.stream()
                .filter(user -> user.getUsername().toLowerCase().equals(username.toLowerCase()))
                .findFirst();
        ArrayList<User> list = new ArrayList<>();
        filteredUser.ifPresent(list::add);
        return list;
    }

    public User findUserById(int id) throws SQLException {
        refreshUserList();
        Optional<User> filteredUser = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        return filteredUser.orElse(null);
    }


    public void deleteUserById(int id) throws SQLException {
        String sql = String.format("DELETE FROM users WHERE id = \"%s\" ", id);
        myStmt.executeUpdate(sql);
    }

    public void updateUserById(int id, User user) throws SQLException {

        String sql = String.format(
                "UPDATE users SET username = \"%s\", password = \"%s\", is_manager = \"%s\", is_approved = \"%s\" WHERE id = \"%s\"  ",
                user.getUsername(), user.getPassword(), user.isManager(), user.isApproved() , id);
        myStmt.executeUpdate(sql);
    }

    public void signUpUser(User user) throws SQLException {
        String sql = String.format("INSERT INTO users (username, password, is_manager, is_approved) VALUES (\"%s\", \"%s\", false, false)",
                user.getUsername(), user.getPassword());

        myStmt.executeUpdate(sql);
    }

    public boolean loginUser(User user) throws SQLException {
        Optional<User> filteredUser =  filterUser(user);

        if (filteredUser.orElse(null) == null) return false;

        else if (!filteredUser.get().getPassword().equals(user.getPassword())) return false;

        return (filteredUser.get().isApproved());
    }

    public boolean isManager(int id) throws SQLException {
        User filteredUser =  findUserById(id);

        return filteredUser.isManager();

    }

    public int getId(User user) throws SQLException {
        Optional<User> filteredUser = filterUser(user);
        return filteredUser.get().getId();
    }

    private Optional<User> filterUser(User user) throws SQLException {
        refreshUserList();
        return users.stream()
                .filter(u -> u.getUsername().toLowerCase().equals(user.getUsername().toLowerCase())).findFirst();
    }

    public void approveUser(int id) throws SQLException {
        String sql = String.format(
                "UPDATE users SET is_approved = true WHERE id = \"%s\"  ", id);
        myStmt.executeUpdate(sql);
    }


}
