package com.globallogic.protoorganizer.model;

import java.sql.Date;

public class DeviceUsageView extends DeviceUsageLog {

	private String firstName;
	private String lastName;
	private String device;
	private String actionName;
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	public DeviceUsageView()
	{}
	
	public DeviceUsageView(long id, int userId, Date date, int deviceId,
			short action, String lastName, String firstName, String device) {

		this.setId(id);
		this.setUserId(userId);
		this.setDate(date);
		this.setDeviceId(deviceId);
		this.setAction(DeviceUsageActionEnum.values()[(int)action]);
		this.setActionName(DeviceUsageActionEnum.values()[(int)action].toString());
		this.lastName = lastName;
		this.firstName = firstName;
		this.device = device;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
}
