package com.globallogic.protoorganizer.model;

public class Project {
	
	private long id;
    private String projectName;
    private Boolean isActive;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}   
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
    	
	@Override
	public String toString() {
		return projectName;
	}
}
