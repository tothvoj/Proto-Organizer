package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.globallogic.protoorganizer.model.DeviceUsageLog;
import com.globallogic.protoorganizer.model.DeviceUsageView;

public class DeviceUsageViewRowMapper implements RowMapper<DeviceUsageView> {

	public DeviceUsageView mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceUsageViewExtractor deviceUsageViewExtractor = new DeviceUsageViewExtractor();
		return deviceUsageViewExtractor.extractData(rs);
	}

}