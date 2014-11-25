package com.globallogic.protoorganizer;

import java.util.List;

import com.globallogic.protoorganizer.model.DeviceUsageLog;

public interface DevicesUsageLogDAO {
	
	public List<DeviceUsageLog> getDeviceUsageLogList();	
	
	public void insertDeviceUsageLog(DeviceUsageLog platform);
	public void updateDeviceUsageLog(DeviceUsageLog platform);
	public void deleteDeviceUsageLog(long id);
	public void deleteBatch(final List<Long> ids);

}
