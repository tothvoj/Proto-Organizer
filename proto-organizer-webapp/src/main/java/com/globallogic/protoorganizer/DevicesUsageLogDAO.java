package com.globallogic.protoorganizer;

import java.sql.Date;
import java.util.List;

import com.globallogic.protoorganizer.model.DeviceUsageActionEnum;
import com.globallogic.protoorganizer.model.DeviceUsageLog;
import com.globallogic.protoorganizer.model.DeviceUsageView;

public interface DevicesUsageLogDAO {
	
	public List<DeviceUsageLog> getDeviceUsageLogList();	
	public List<DeviceUsageView> getDeviceUsageViewList(int deviceId);
	
	public void insertDeviceUsageLog(DeviceUsageLog platform);
	public void insertDeviceUsageLog(int deviceId, DeviceUsageActionEnum action);
	public void insertDeviceUsageLog(int userId, int deviceId, DeviceUsageActionEnum action);
	public void insertDeviceUsageLog(int userId, Date date, int deviceId, DeviceUsageActionEnum action);
	public void updateDeviceUsageLog(DeviceUsageLog platform);
	public void deleteDeviceUsageLog(long id);
	public void deleteBatch(final List<Long> ids);

}
