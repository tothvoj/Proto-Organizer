package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.globallogic.protoorganizer.database.DevicesColumns;
import com.globallogic.protoorganizer.model.DeviceView;

public class DeviceViewExtractor implements ResultSetExtractor<DeviceView> {

	public DeviceView extractData(ResultSet rs) throws SQLException,
	DataAccessException {

	DeviceView device = new DeviceView(
			rs.getLong(rs.findColumn(DevicesColumns.ID)), 
			rs.getString(rs.findColumn(DevicesColumns.DEVICE)), 
			rs.getInt(rs.findColumn(DevicesColumns.PLATFORM_ID)),
			rs.getString(rs.findColumn(DevicesColumns.PLATFORM_NAME)),
			rs.getString(rs.findColumn(DevicesColumns.PLATFORM_VERSION)),
			rs.getString(rs.findColumn(DevicesColumns.IMEI)), 
			rs.getString(rs.findColumn(DevicesColumns.STATUS)), 
			rs.getInt(rs.findColumn(DevicesColumns.PROJECT_ID)),
			rs.getString(rs.findColumn(DevicesColumns.PROJECT_NAME)),
			rs.getInt(rs.findColumn(DevicesColumns.OWNER_ID)),
			rs.getString(rs.findColumn(DevicesColumns.OWNER_FIRST_NAME)),
			rs.getString(rs.findColumn(DevicesColumns.OWNER_LAST_NAME)),
			rs.getString(rs.findColumn(DevicesColumns.REASON)), 
			rs.getInt(rs.findColumn(DevicesColumns.LAST_MODIFIED_BY)),
			rs.getString(rs.findColumn(DevicesColumns.MODIFIERS_FIRST_NAME)),
			rs.getString(rs.findColumn(DevicesColumns.MODIFIERS_LAST_NAME)),			
			rs.getTimestamp(rs.findColumn(DevicesColumns.DATE)),
			rs.getString(rs.findColumn(DevicesColumns.ORIGIN)),
			rs.getBoolean(rs.findColumn(DevicesColumns.ISOWNERAPERSON)));
	
	return device;
	}
}
