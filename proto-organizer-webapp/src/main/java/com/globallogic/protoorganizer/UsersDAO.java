package com.globallogic.protoorganizer;

import java.util.List;

import com.globallogic.protoorganizer.model.User;

public interface UsersDAO {

	public List<User> getUsersList();

	public void insertUser(User user);

	public void deleteUser(long id);

	public void updateUser(User user);

	public void deleteBatch(final List<Long> ids);

}
