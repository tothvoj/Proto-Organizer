package com.globallogic.protoorganizer;

import java.util.List;

import com.globallogic.protoorganizer.model.Project;

public interface ProjectsDAO {
	
	public List<Project> getProjectsList();	
	public void insertProject(String project);
	public void deleteProject(long id);
	public void deleteBatch(final List<Long> ids);
	
}
