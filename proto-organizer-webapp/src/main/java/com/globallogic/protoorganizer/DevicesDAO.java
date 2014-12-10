package com.globallogic.protoorganizer;

import java.util.List;

import com.globallogic.protoorganizer.model.Device;
import com.globallogic.protoorganizer.model.DeviceView;

public interface DevicesDAO {

	public List<Device> getDevicesList(String filter);
	
	public Device getDevice(int deviceId);
	
	public List<DeviceView> getDevicesViewList(String filter);

	public void insertDevice(Device device);

	public void deleteDevice(long id);

	public void updateDevice(Device device);

	public void updateDevicePartially(List<DeviceView> devicesList);
	
	public void deleteBatch(final List<Long> ids);
	
	public void changeOwner(long deviceID, long userID);
	
	public void sort(List<Device> deviceList, int sortingParam);
	
	public void sortInView(List<DeviceView> deviceList, int sortingParam);
	
	public List<Device> getRemovedDevicesList();
	
	public List<DeviceView> getRemovedDevicesViewList();
}
