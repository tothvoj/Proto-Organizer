package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.globallogic.protoorganizer.model.DeviceView;

public class DeviceViewRowMapper implements RowMapper<DeviceView> {

	public DeviceView mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceViewExtractor deviceExtractor = new DeviceViewExtractor();
		return deviceExtractor.extractData(rs);
	}

}