package com.globallogic.protoorganizer;

import java.util.List;

import com.globallogic.protoorganizer.model.User;

public interface UsersDAO {

	public List<User> getUsersList();
	
	public List<User> getUsersList(boolean isPerson);

	public boolean insertUser(User user);

	public void deleteUser(long id);

	public void updateUser(User user);

	public void deleteBatch(final List<Long> ids);
	
	public User getUserByEmail(String email);
	
	public void changePassword(String username, String password);

}
