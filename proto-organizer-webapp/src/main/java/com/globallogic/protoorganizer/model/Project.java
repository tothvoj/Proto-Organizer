package com.globallogic.protoorganizer.model;

public class Project {
	
	private long id;
    private String project_name;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}   
    
	
	@Override
	public String toString() {
		return project_name;
	}
    

}
