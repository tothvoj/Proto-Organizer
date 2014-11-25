package com.globallogic.protoorganizer.model;

public class Platform {

	private long id;
	private String name;
	private int masterPlatform;
	private String version;
	private String comment;
	
	
	public Platform(long id, String name, int masterPlatform, String version,
			String comment) {
		this.id = id;
		this.name = name;
		this.masterPlatform = masterPlatform;
		this.version = version;
		this.comment = comment;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMasterPlatform() {
		return masterPlatform;
	}
	public void setMasterPlatform(int masterPlatform) {
		this.masterPlatform = masterPlatform;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getFullName()
	{
		return name + " " + version;
	}
	
}
