package com.globallogic.protoorganizer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.globallogic.protoorganizer.database.ProjectsColumns;
import com.globallogic.protoorganizer.database.TableNames;
import com.globallogic.protoorganizer.database.UsersColumns;
import com.globallogic.protoorganizer.jdbc.ProjectRowMapper;
import com.globallogic.protoorganizer.jdbc.UserRowMapper;
import com.globallogic.protoorganizer.model.Project;
import com.globallogic.protoorganizer.model.User;

public class UsersDAOImpl implements UsersDAO {

	@Autowired
	DataSource dataSource;

	public List<User> getUsersList() {
		List<User> users = new ArrayList<User>();

		String sql = "select * from " + TableNames.USERS;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		users = jdbcTemplate.query(sql, new UserRowMapper());

		return users;
	}

	public void insertUser(User user) {
		String sql = "INSERT INTO " + TableNames.USERS + " ("
				+ UsersColumns.NAME + ", " + UsersColumns.EMAIL + ", "
				+ UsersColumns.BAR_CODE + ", " + UsersColumns.SPECIAL_RIGHTS
				+ ") VALUES (?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { user.getName(),
				user.getEmail(), user.getBarcode(), user.getRights() });

	}

	public void deleteUser(long id) {
		String sql = "delete from " + TableNames.USERS + " where "
				+ UsersColumns.ID + "=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);

	}

	public void updateUser(User user) {
		String sql = "UPDATE " + TableNames.USERS + " set " + UsersColumns.NAME
				+ " = ?," + UsersColumns.EMAIL + " = ?,"
				+ UsersColumns.BAR_CODE + " = ?," + UsersColumns.SPECIAL_RIGHTS
				+ " = ?," + " where " + UsersColumns.ID + " = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { user.getName(), user.getEmail(),
						user.getBarcode(), user.getRights(), user.getId() });

	}
	
	public void deleteBatch(final List<Long> ids){
		 
		String sql = "delete from " + TableNames.USERS + " where "
				+ UsersColumns.ID + "=?";
	 
	  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	  jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	 
		
		public void setValues(java.sql.PreparedStatement ps, int i)
				throws SQLException {
			ps.setLong(1, ids.get(i));
			
		}

		public int getBatchSize() {
			return ids.size();
		}
	  });
	}

}
