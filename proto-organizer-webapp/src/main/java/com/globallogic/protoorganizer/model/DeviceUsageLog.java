package com.globallogic.protoorganizer.model;
import java.sql.Date;
import java.sql.Timestamp;

public class DeviceUsageLog {

	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public DeviceUsageActionEnum getAction() {
		return action;
	}

	public void setAction(DeviceUsageActionEnum action) {
		this.action = action;
	}

	private int userId;
	private Date date;
	private int deviceId;
	private DeviceUsageActionEnum action;
	
	public DeviceUsageLog()	{
		
	}
	
	public DeviceUsageLog(long id, int userId, Date date, int deviceId, short action) {
		this.id = id;
		this.userId = userId;
		this.date = date;
		this.deviceId = deviceId;
		this.action = Helper.getActionEnum(action);
	}
}
