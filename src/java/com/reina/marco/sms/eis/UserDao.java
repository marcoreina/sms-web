package com.reina.marco.sms.eis;

import java.util.List;
import com.reina.marco.sms.domain.User;

public interface UserDao {

	public List<User> findAllUsers();
	
	public User findUserById(User user);
	
	public void insertUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
}
