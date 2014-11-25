package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.globallogic.protoorganizer.model.DeviceUsageLog;

public class DeviceUsageLogRowMapper implements RowMapper<DeviceUsageLog> {

	public DeviceUsageLog mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceUsageLogExtractor deviceUsageLogExtractor = new DeviceUsageLogExtractor();
		return deviceUsageLogExtractor.extractData(rs);
	}

}