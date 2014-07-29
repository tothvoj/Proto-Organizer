package com.globallogic.protoorganizer.model;

import java.util.List;

public class Helper {

	private List<Long> ids;
	private long userID;
	private long selectedDeviceID;

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
	
}
