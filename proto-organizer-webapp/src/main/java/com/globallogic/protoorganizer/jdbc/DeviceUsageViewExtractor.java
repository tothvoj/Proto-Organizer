package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.globallogic.protoorganizer.database.DevicesColumns;
import com.globallogic.protoorganizer.database.DevicesUsageLogColumns;
import com.globallogic.protoorganizer.database.UsersColumns;
import com.globallogic.protoorganizer.model.DeviceUsageView;

public class DeviceUsageViewExtractor implements ResultSetExtractor<DeviceUsageView> {

	public DeviceUsageView extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		DeviceUsageView deviceUsageView = new DeviceUsageView(
				rs.getLong(rs.findColumn(DevicesUsageLogColumns.ID)), 
				rs.getInt(rs.findColumn(DevicesUsageLogColumns.USER_ID)), 
				rs.getTimestamp(rs.findColumn(DevicesUsageLogColumns.DATE)), 
				rs.getInt(rs.findColumn(DevicesUsageLogColumns.DEVICE_ID)), 
				rs.getShort(rs.findColumn(DevicesUsageLogColumns.ACTION)),
				rs.getString(rs.findColumn(UsersColumns.LAST_NAME)),
				rs.getString(rs.findColumn(UsersColumns.FIRST_NAME)), 
				rs.getString(rs.findColumn(DevicesColumns.DEVICE)));
				
		return deviceUsageView;
	}

}
