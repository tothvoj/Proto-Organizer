package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.globallogic.protoorganizer.model.Project;

public class ProjectRowMapper implements RowMapper<Project> {

	public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProjectExtractor projectExtractor = new ProjectExtractor();
		Project project = projectExtractor.extractData(rs);
		return project;
	}

}
