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
				rs.getString(rs.findColumn(DevicesColumns.PLATFORM)), 
				rs.getString(rs.findColumn(DevicesColumns.IMEI)), 
				rs.getString(rs.findColumn(DevicesColumns.STATUS)), 
				rs.getString(rs.findColumn(DevicesColumns.PROJECT)), 
				rs.getString(rs.findColumn(DevicesColumns.OWNER)), 
				rs.getString(rs.findColumn(DevicesColumns.REASON)), 
				rs.getString(rs.findColumn(DevicesColumns.LAST_MODIFIED)),
				rs.getTimestamp(rs.findColumn(DevicesColumns.DATE)),
				rs.getString(rs.findColumn(DevicesColumns.ORIGIN)));

		return device;
	}

}
