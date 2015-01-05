package com.globallogic.protoorganizer;

import java.util.List;

import com.globallogic.protoorganizer.model.Project;

public interface ProjectsDAO {
	
	public List<Project> getProjectsList();	
	public List<Project> getActiveProjectsList(boolean isActive);
	public void insertProject(String project);
	public void updateProject(Project project);
	public void deleteProject(long id);
	public void deleteBatch(final List<Long> ids);
	public int deactivateBatch(final List<Long> ids, boolean isActive);	
}
