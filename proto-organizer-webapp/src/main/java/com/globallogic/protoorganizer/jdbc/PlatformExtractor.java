package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.globallogic.protoorganizer.database.DevicesUsageLogColumns;
import com.globallogic.protoorganizer.database.PlatformsColumns;
import com.globallogic.protoorganizer.model.DeviceUsageLog;
import com.globallogic.protoorganizer.model.Platform;

public class PlatformExtractor implements ResultSetExtractor<Platform> {

	public Platform extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		Platform platform = new Platform(
				rs.getLong(rs.findColumn(PlatformsColumns.ID)), 
				rs.getString(rs.findColumn(PlatformsColumns.NAME)), 
				rs.getInt(rs.findColumn(PlatformsColumns.MASTER_PLATFORM)), 
				rs.getString(rs.findColumn(PlatformsColumns.VERSION)), 
				rs.getString(rs.findColumn(PlatformsColumns.COMMENT)),
				rs.getBoolean(rs.findColumn(PlatformsColumns.IS_ACTIVE))); 
				
		return platform;
	}

}
