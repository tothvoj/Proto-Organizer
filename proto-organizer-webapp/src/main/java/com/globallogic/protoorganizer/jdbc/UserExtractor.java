package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.globallogic.protoorganizer.database.UsersColumns;
import com.globallogic.protoorganizer.model.User;

public class UserExtractor implements ResultSetExtractor<User> {

	public User extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		User user = new User(rs.getLong(rs.findColumn(UsersColumns.ID)),
				rs.getString(rs.findColumn(UsersColumns.NAME)),
				rs.getString(rs.findColumn(UsersColumns.EMAIL)),
				rs.getString(rs.findColumn(UsersColumns.BAR_CODE)),
				rs.getBoolean(rs.findColumn(UsersColumns.SPECIAL_RIGHTS)));
		return user;
	}

}
