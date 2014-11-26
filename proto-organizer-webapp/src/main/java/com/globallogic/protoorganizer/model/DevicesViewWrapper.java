package com.globallogic.protoorganizer.model;

import java.util.ArrayList;
import java.util.List;

public class DevicesViewWrapper {
	
	private List<DeviceView> devicesList = new ArrayList<DeviceView>();
	private List<Device> removedDevicesList = new ArrayList<Device>();
	private List<Project> projects = new ArrayList<Project>();
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<DeviceView> getDevicesList() {
		return devicesList;
	}

	public void setDevicesList(List<DeviceView> devicesList) {
		this.devicesList = devicesList;
	}

	public List<Device> getRemovedDevicesList() {
		return removedDevicesList;
	}

	public void setRemovedDevicesList(List<Device> removedDevicesList) {
		this.removedDevicesList = removedDevicesList;
	}
	
	public DevicesViewWrapper()
	{}

	public DevicesViewWrapper(List<DeviceView> devicesList,
			List<Device> removedDevicesList, List<Project> projects) {

		this.devicesList = devicesList;
		this.removedDevicesList = removedDevicesList;
		this.projects = projects;
	}

}
