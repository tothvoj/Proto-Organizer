package com.globallogic.protoorganizer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.globallogic.protoorganizer.database.PlatformsColumns;
import com.globallogic.protoorganizer.database.ProjectsColumns;
import com.globallogic.protoorganizer.database.TableNames;
import com.globallogic.protoorganizer.jdbc.PlatformRowMapper;
import com.globallogic.protoorganizer.model.Platform;

public class PlatformsDAOImpl implements PlatformsDAO {

	@Autowired
	DataSource dataSource;
	
	public List<Platform> getPlatformsList() {
		List<Platform> platforms = new ArrayList<Platform>();

		String sql = "select * from " + TableNames.PLATFORMS;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		platforms = jdbcTemplate.query(sql, new PlatformRowMapper());

		return platforms;
	}
	
	public List<Platform> getActiveChildPlatforms(boolean isActive) {
		List<Platform> platforms = new ArrayList<Platform>();

		String sql = "select * from " + TableNames.PLATFORMS + " where " + PlatformsColumns.IS_ACTIVE + " = " +
				(isActive ? "1" : "0");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		platforms = jdbcTemplate.query(sql, new PlatformRowMapper());

		return platforms;
	}
	
	public List<Platform> getMasterPlatforms() {
		List<Platform> platforms = new ArrayList<Platform>();

		String sql = "select * from " + TableNames.PLATFORMS + " where master_platform IS NULL";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		platforms = jdbcTemplate.query(sql, new PlatformRowMapper());

		return platforms;
	}
	
	public List<Platform> getChildPlatforms() {
		List<Platform> platforms = new ArrayList<Platform>();

		String sql = "select * from " + TableNames.PLATFORMS + " where master_platform IS NOT NULL";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		platforms = jdbcTemplate.query(sql, new PlatformRowMapper());

		return platforms;
	}

	public void insertPlatform(Platform platform) {
		String sql = "INSERT INTO " + TableNames.PLATFORMS + " ("
				+ PlatformsColumns.NAME + ", "
				+ PlatformsColumns.MASTER_PLATFORM + ", "
				+ PlatformsColumns.VERSION + ", "
				+ PlatformsColumns.COMMENT + ") VALUES (?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { 
				platform.getName(),
				platform.getMasterPlatform(), 
				platform.getVersion(), 
				platform.getComment()});
	}

	public void updatePlatform(Platform platform) {
		String sql = "UPDATE " + TableNames.PLATFORMS + " set "
				+ PlatformsColumns.NAME + " = ?, "
				+ PlatformsColumns.MASTER_PLATFORM + " = ?, " 
				+ PlatformsColumns.VERSION + " = ?, "
				+ PlatformsColumns.COMMENT + " = ? where " + 
				PlatformsColumns.ID + " = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { 
						platform.getName(), 
						platform.getMasterPlatform(),
						platform.getVersion(),
						platform.getComment(),
						platform.getId()});
	}

	public void deletePlatform(long id) {
		String sql = "delete from " + TableNames.PLATFORMS + " where "
				+ PlatformsColumns.ID + "=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
	}

	public void deleteBatch(final List<Long> ids) {
		String sql = "delete from " + TableNames.PLATFORMS + " where "
				+ PlatformsColumns.ID + "=?";
	 
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
	
	public void deactivateBatch(final List<Long> ids, boolean isActive) {
		String sql = "update " + TableNames.PLATFORMS + " set " + PlatformsColumns.IS_ACTIVE + " = " + 
				(isActive ? 1 : 0 ) + " where " + ProjectsColumns.ID + " =? ";
	 
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
