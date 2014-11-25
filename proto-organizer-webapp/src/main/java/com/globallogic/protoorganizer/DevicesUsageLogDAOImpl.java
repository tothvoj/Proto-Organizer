package com.globallogic.protoorganizer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.globallogic.protoorganizer.database.DevicesUsageLogColumns;
import com.globallogic.protoorganizer.database.TableNames;
import com.globallogic.protoorganizer.jdbc.DeviceUsageLogRowMapper;
import com.globallogic.protoorganizer.model.DeviceUsageLog;

public class DevicesUsageLogDAOImpl implements DevicesUsageLogDAO {

	@Autowired
	DataSource dataSource;

	public List<DeviceUsageLog> getDeviceUsageLogList() {
		List<DeviceUsageLog> devicesUsageLogList = new ArrayList<DeviceUsageLog>();
		String sql;

		sql = "select * from " + TableNames.DEVICES_USAGE_LOG;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		devicesUsageLogList = jdbcTemplate.query(sql, new DeviceUsageLogRowMapper());
		return devicesUsageLogList;
	}

	public void insertDeviceUsageLog(DeviceUsageLog deviceUsageLog) {
		String sql = "INSERT INTO " + TableNames.DEVICES_USAGE_LOG + " ("
				+ DevicesUsageLogColumns.USER_ID + ", " + DevicesUsageLogColumns.DATE + ", "
				+ DevicesUsageLogColumns.DEVICE_ID + ", " + DevicesUsageLogColumns.ACTION + 
				") VALUES (?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { 
						deviceUsageLog.getUserId(), 
						deviceUsageLog.getDate(), 
						deviceUsageLog.getDeviceId(), 
						deviceUsageLog.getAction()});
	}

	public void updateDeviceUsageLog(DeviceUsageLog deviceUsageLog) {
		String sql = "UPDATE " + TableNames.DEVICES_USAGE_LOG + " set "
				+ DevicesUsageLogColumns.USER_ID + " = ?," 
				+ DevicesUsageLogColumns.DATE + " = ?," 
				+ DevicesUsageLogColumns.DEVICE_ID + " = ?,"
				+ DevicesUsageLogColumns.ACTION + " = ?"
				+ " where " + DevicesUsageLogColumns.ID + " = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { 
						deviceUsageLog.getUserId(), 
						deviceUsageLog.getDate(), 
						deviceUsageLog.getDeviceId(), 
						deviceUsageLog.getAction(),
						deviceUsageLog.getId()});
		
	}

	public void deleteDeviceUsageLog(long id) {
		String sql = "delete from " + TableNames.DEVICES_USAGE_LOG + " where "
				+ DevicesUsageLogColumns.ID + "=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
		
	}

	public void deleteBatch(final List<Long> ids) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "delete from " + TableNames.DEVICES_USAGE_LOG + " where "
				+ DevicesUsageLogColumns.ID + "=?";
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			public void setValues(java.sql.PreparedStatement ps, int i)
					throws SQLException {
				ps.setLong(1, ids.get(i));

			}

			public int getBatchSize() {
				return ids.size();
			}
		});	
	}
}