package com.globallogic.protoorganizer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.globallogic.protoorganizer.model.Platform;

public class PlatformRowMapper implements RowMapper<Platform> {

	public Platform mapRow(ResultSet rs, int rowNum) throws SQLException {
		PlatformExtractor platformExtractor = new PlatformExtractor();
		Platform platform = platformExtractor.extractData(rs);
		return platform;
	}
}