package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.globallogic.protoorganizer.database.ProjectsColumns;
import com.globallogic.protoorganizer.model.Project;

public class ProjectExtractor implements ResultSetExtractor<Project> {

	public Project extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Project project = new Project();
		
		project.setId(rs.getLong(rs.findColumn(ProjectsColumns.ID)));
		project.setProjectName(rs.getString(rs.findColumn(ProjectsColumns.PROJECT_NAME)));
		return project;
	}

}
