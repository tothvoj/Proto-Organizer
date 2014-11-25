package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.globallogic.protoorganizer.database.DevicesUsageLogColumns;
import com.globallogic.protoorganizer.model.DeviceUsageLog;

public class DeviceUsageLogExtractor implements ResultSetExtractor<DeviceUsageLog> {

	public DeviceUsageLog extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		DeviceUsageLog deviceUsageLog = new DeviceUsageLog(
				rs.getLong(rs.findColumn(DevicesUsageLogColumns.ID)), 
				rs.getInt(rs.findColumn(DevicesUsageLogColumns.USER_ID)), 
				rs.getDate(rs.findColumn(DevicesUsageLogColumns.DATE)), 
				rs.getInt(rs.findColumn(DevicesUsageLogColumns.DEVICE_ID)), 
				rs.getShort(rs.findColumn(DevicesUsageLogColumns.ACTION))); 
				
		return deviceUsageLog;
	}

}
