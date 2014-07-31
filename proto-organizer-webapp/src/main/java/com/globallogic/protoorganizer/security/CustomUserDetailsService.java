package com.globallogic.protoorganizer.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.globallogic.protoorganizer.UsersDAO;
import com.globallogic.protoorganizer.model.User;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UsersDAO usersDAO;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		// Declare a null Spring User
		UserDetails springUser = null;

		User user = usersDAO.getUserByEmail(username);
		
		if (user == null){
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		
		springUser = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user.getRights()));
		
		return springUser;
	}

	/**
	 * Retrieves the correct ROLE type depending on the access level, where
	 * access level is an Integer. Basically, this interprets the access value
	 * whether it's for a regular user or admin.
	 * 
	 * @param access
	 *            an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	public Collection<SimpleGrantedAuthority> getAuthorities(boolean specialRights) {
		// Create a list of grants for this user
		List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>(
				2);

		// All users are granted with ROLE_USER access
		// Therefore this user gets a ROLE_USER by default
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));

		// Check if this user has admin access
		// We interpret Integer(1) as an admin user
		if (specialRights) {
			// User has admin access
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		// Return list of granted authorities
		return authList;
	}

}
