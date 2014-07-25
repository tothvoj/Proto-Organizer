package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.globallogic.protoorganizer.model.User;

public class UserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserExtractor userExtractor = new UserExtractor();
		User user = userExtractor.extractData(rs);
		return user;
	}

}
