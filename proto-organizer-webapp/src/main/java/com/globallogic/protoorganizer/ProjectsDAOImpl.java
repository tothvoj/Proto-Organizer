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
import com.globallogic.protoorganizer.jdbc.ProjectRowMapper;
import com.globallogic.protoorganizer.model.Project;

public class ProjectsDAOImpl implements ProjectsDAO {

	@Autowired
	DataSource dataSource;

	public List<Project> getProjectsList() {
		List<Project> projects = new ArrayList<Project>();

		String sql = "select * from " + TableNames.PROJECTS;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		projects = jdbcTemplate.query(sql, new ProjectRowMapper());

		return projects;
	}

	public void insertProject(String project) {
		String sql = "INSERT INTO " + TableNames.PROJECTS + " ("
				+ ProjectsColumns.PROJECT_NAME + ") VALUES (?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { project });
	}
	
	public void updateProject(Project project) {
		String sql = "UPDATE " + TableNames.PROJECTS + " set "
				+ ProjectsColumns.PROJECT_NAME + " = ? where " + 
				ProjectsColumns.ID + " = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { project.getProjectName(), project.getId() });
	}

	public void deleteProject(long id) {
		String sql = "delete from " + TableNames.PROJECTS + " where "
				+ ProjectsColumns.ID + "=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);

	}
	
	public void deleteBatch(final List<Long> ids){
		 
		String sql = "delete from " + TableNames.PROJECTS + " where "
				+ ProjectsColumns.ID + "=?";
	 
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
