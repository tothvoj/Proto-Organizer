package com.globallogic.protoorganizer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.globallogic.protoorganizer.database.TableNames;
import com.globallogic.protoorganizer.database.UsersColumns;
import com.globallogic.protoorganizer.jdbc.UserRowMapper;
import com.globallogic.protoorganizer.model.User;

public class UsersDAOImpl implements UsersDAO {

	@Autowired
	DataSource dataSource;

	public List<User> getUsersList() {
		List<User> users = new ArrayList<User>();

		String sql = "select * from " + TableNames.USERS ;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		users = jdbcTemplate.query(sql, new UserRowMapper());

		return users;
	}
	
	public List<User> getUsersList(boolean isPerson) {
		List<User> users = new ArrayList<User>();

		String sql = "select * from " + TableNames.USERS + " where " 
				+ UsersColumns.IS_PERSON + " = " + (isPerson ? "1" : "0"); 

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		users = jdbcTemplate.query(sql, new UserRowMapper());

		return users;
	}

	public boolean insertUser(User user) {
		String sql = "INSERT INTO " + TableNames.USERS + " ("
				+ UsersColumns.FIRST_NAME + ", " 
				+ UsersColumns.LAST_NAME + ", " 
				+ UsersColumns.EMAIL + ", "
				+ UsersColumns.BAR_CODE + ", " 
				+ UsersColumns.SPECIAL_RIGHTS + ", "
				+ UsersColumns.IS_PERSON + ", "
				+ UsersColumns.IS_ACTIVE 
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		int rows = jdbcTemplate.update(sql, new Object[] { 
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(), 
				user.getBarcode(), 
				user.getRights(),
				user.getIsPerson(),
				1});		
		if (rows == 1){
			return true;
		}

		return false;
	}

	public void deleteUser(long id) {
		String sql = "delete from " + TableNames.USERS + " where "
				+ UsersColumns.ID + "=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);

	}

	public void updateUser(User user) {
		String sql = "UPDATE " + TableNames.USERS + " set " 
				+ UsersColumns.FIRST_NAME + " = ?," 
				+ UsersColumns.LAST_NAME + " = ?,"
				+ UsersColumns.EMAIL + " = ?,"
				+ UsersColumns.BAR_CODE + " = ?," 
				+ UsersColumns.SPECIAL_RIGHTS + " = ?,"
				+ UsersColumns.IS_PERSON
				+ " = ?, " + " where " + UsersColumns.ID + " = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { 
						user.getFirstName(), 
						user.getLastName(),
						user.getEmail(),
						user.getBarcode(), 
						user.getRights(),
						user.getIsPerson(),
						user.getId() });

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
	
	public int deactivateBatch(final List<Long> ids, Boolean isActive){
		 
		String sql = "update " + TableNames.USERS + " set " + UsersColumns.IS_ACTIVE + " = " + 
				(isActive ? 1 : 0 ) + " where " + UsersColumns.ID + " =? ";
	 
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int[] result = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	 
		public void setValues(java.sql.PreparedStatement ps, int i)
				throws SQLException {
			ps.setLong(1, ids.get(i));
			
		}

		public int getBatchSize() {
			return ids.size();
		}
	  });
	  
	  return result[0];
	}

	public User getUserByEmail(final String email) {
		
		List<User> users = new ArrayList<User>();
		
		
		PreparedStatementCreator creator = new PreparedStatementCreator() {

			public java.sql.PreparedStatement createPreparedStatement(
					Connection con) throws SQLException {

				String sql = "select * from " + TableNames.USERS + " where "
						+ UsersColumns.EMAIL + "=?";
				java.sql.PreparedStatement preparedStatement = con
						.prepareStatement(sql);
				preparedStatement.setString(1, email);

				return preparedStatement;
			}
		};
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		users = jdbcTemplate.query(creator, new UserRowMapper());
				
		if (users.size() == 1){
			return users.get(0);
		}
		
		return null;
	}
	
	public void changePassword(String username, String password){
		String sql = "UPDATE " + TableNames.USERS + " set " +  UsersColumns.PASSWORD
				+ " = ?" + " where " + UsersColumns.EMAIL + " = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { password, username });
	}

}
