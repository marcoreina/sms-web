package com.reina.marco.sms.service;

import java.util.List;
import javax.ejb.Remote;
import com.reina.marco.sms.domain.User;

@Remote
public interface UserServiceRemote {

    public List<User> listUsers();

    public User findUserById(User user);

    public void registerUser(User user);

    public void modifyUser(User user);

    public void deleteUser(User user);
}
