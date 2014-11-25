package com.globallogic.protoorganizer.model;

import java.util.List;

public class Helper {

	private List<Long> ids;
	private long userID;
	private long selectedDeviceID;
	private String currentPassword;
	private String newPassword;
	private String newPasswordRepeat;
	
	// caching EnumDeviceUsage
	private static DeviceUsageActionEnum[] DeviceUsage = DeviceUsageActionEnum.values();

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getSelectedDeviceID() {
		return selectedDeviceID;
	}

	public void setSelectedDeviceID(long selectedDeviceID) {
		this.selectedDeviceID = selectedDeviceID;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}

	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}	
	
	public static DeviceUsageActionEnum getActionEnum(int action)
	{
		return DeviceUsage[action];
	}
	
		
}
