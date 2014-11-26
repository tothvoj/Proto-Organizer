package com.globallogic.protoorganizer.model;

public class Project {
	
	private long id;
    private String projectName;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String project_name) {
		this.projectName = project_name;
	}   
    
	
	@Override
	public String toString() {
		return projectName;
	}
    

}
