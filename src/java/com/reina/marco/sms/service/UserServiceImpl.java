package com.reina.marco.sms.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.reina.marco.sms.domain.User;
import com.reina.marco.sms.eis.UserDao;

@Stateless
public class UserServiceImpl implements UserServiceRemote, UserService {
	
    @EJB
    private UserDao userDao;

    public List<User> listUsers() {
        return userDao.findAllUsers();
    }

    public User findUserById(User user) {
        return userDao.findUserById(user);
    }

    public void registerUser(User user) {
        userDao.insertUser(user);
    }

    public void modifyUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
