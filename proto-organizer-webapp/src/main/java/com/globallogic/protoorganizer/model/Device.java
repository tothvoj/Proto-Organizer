/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.protoorganizer.model;

import java.sql.Timestamp;

/**
 *
 * @author magyapa
 */
public class Device {
    
    private String device;
    private String imei; 
    private String platform;     
    private String status;
    private String project;
    private String owner;
    private String reason;
    private String last_modified;
    private String email;
    private long id;
    private Timestamp date;
    

    /**
     * Construct.
     * 
     * @param device
     *            device
     * @param imei
     *            imei
     * @param status
     *            status
     * @param project
     *            project
     * @param owner
     *            owner
     * @param reason
     *            reason
     */
    public Device(long id, String device, String platform, String imei, String status, String project, String owner, String reason, String last_modified, Timestamp date)
    {
    	this.id = id;
    	this.platform = platform;
        this.device = device;
        this.imei = imei;
        this.status = status;
        this.project = project;
        this.owner = owner;
        this.reason = reason;
        this.last_modified = last_modified;
        this.date = date;
    }
    
    public Device(){
    	
    }

    /**
     * Gets the device.
     * 
     * @return device
     */
    public String getDevice()
    {
        return device;
    }

    /**
     * Sets the device.
     * 
     * @param device
     *            device
     */
    public void setDevice(String device)
    {
        this.device = device;
    }

    /**
     * Gets the imei.
     * 
     * @return imei
     */
    public String getImei()
    {
        return imei;
    }

    /**
     * Sets the imei.
     * 
     * @param imei
     *            imei
     */
    public void setImei(String imei)
    {
        this.imei = imei;
    }
    
    /**
     * Gets the status.
     * 
     * @return status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Sets the status.
     * 
     * @param status
     *            status
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    /**
     * Gets the project.
     * 
     * @return project
     */
    public String getProject()
    {
        return project;
    }

    /**
     * Sets the project.
     * 
     * @param project
     *            project
     */
    public void setProject(String project)
    {
        this.project = project;
    }
    
    /**
     * Gets the owner.
     * 
     * @return owner
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * Sets the owner.
     * 
     * @param owner
     *            owner
     */
    public void setOwner(String owner)
    {
        this.owner = owner;
    }
    
    public String getReason()
    {
        return reason;
    }

    /**
     * Sets the reason.
     * 
     * @param reason
     *            reason
     */
    public void setReason(String reason)
    {
        this.reason = reason;
    }
    
    public String getLast_modified() {
		return last_modified;
	}

    
	public String getEmail() {
		return email;
	}
	
	
	public long getId() {
		return id;
	}
	
	
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
	public String getPlatform() {
		return platform;
	}

	public Timestamp getDate() {
		return date;
	}

	@Override
	public String toString() {
		return device;
	}
	public void printAll(){
        //System.out.println(this.device + this.imei + this.owner + this.project + this.status);
    }
}
