package com.globallogic.protoorganizer;

import java.util.List;

import com.globallogic.protoorganizer.model.Device;

public interface DevicesDAO {

	public List<Device> getDevicesList(String filter);

	public void insertDevice(Device device);

	public void deleteDevice(long id);

	public void updateDevice(Device device);

	public void deleteBatch(final List<Long> ids);
	
	public void changeOwner(long deviceID, long userID);
	
	public void sort(List<Device> deviceList, int sortingParam);
	
	public List<Device> getRemovedDevicesList();

}
