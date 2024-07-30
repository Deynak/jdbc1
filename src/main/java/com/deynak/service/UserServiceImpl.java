package com.deynak.service;

import com.deynak.dao.UserDao;
import com.deynak.dao.UserDaoHibernateImpl;
import com.deynak.dao.UserDaoJDBCImpl;
import com.deynak.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoJDBCImpl();
    private final UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
