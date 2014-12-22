package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.globallogic.protoorganizer.database.DevicesColumns;
import com.globallogic.protoorganizer.model.Device;

public class DeviceExtractor implements ResultSetExtractor<Device> {

	public Device extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		Device device = new Device(
				rs.getLong(rs.findColumn(DevicesColumns.ID)), 
				rs.getString(rs.findColumn(DevicesColumns.DEVICE)), 
				rs.getInt(rs.findColumn(DevicesColumns.PLATFORM_ID)), 
				rs.getString(rs.findColumn(DevicesColumns.IMEI)), 
				rs.getString(rs.findColumn(DevicesColumns.STATUS)), 
				rs.getInt(rs.findColumn(DevicesColumns.PROJECT_ID)), 
				rs.getInt(rs.findColumn(DevicesColumns.OWNER_ID)), 
				rs.getString(rs.findColumn(DevicesColumns.REASON)), 
				rs.getInt(rs.findColumn(DevicesColumns.LAST_MODIFIED_BY)),
				rs.getTimestamp(rs.findColumn(DevicesColumns.DATE)),
				rs.getString(rs.findColumn(DevicesColumns.ORIGIN)));

		return device;
	}
}
