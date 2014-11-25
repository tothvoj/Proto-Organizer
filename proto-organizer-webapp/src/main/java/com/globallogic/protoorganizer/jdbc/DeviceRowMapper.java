package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.globallogic.protoorganizer.model.Device;
import com.globallogic.protoorganizer.model.DeviceView;

public class DeviceRowMapper implements RowMapper<Device> {

	public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceExtractor deviceExtractor = new DeviceExtractor();
		return deviceExtractor.extractData(rs);
	}
}
