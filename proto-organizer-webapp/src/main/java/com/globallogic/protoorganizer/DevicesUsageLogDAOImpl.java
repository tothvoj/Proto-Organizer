package com.globallogic.protoorganizer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import com.globallogic.protoorganizer.database.DevicesUsageLogColumns;
import com.globallogic.protoorganizer.database.TableNames;
import com.globallogic.protoorganizer.jdbc.DeviceUsageLogRowMapper;
import com.globallogic.protoorganizer.jdbc.DeviceUsageViewRowMapper;
import com.globallogic.protoorganizer.model.DeviceUsageActionEnum;
import com.globallogic.protoorganizer.model.DeviceUsageLog;
import com.globallogic.protoorganizer.model.DeviceUsageView;
import com.globallogic.protoorganizer.model.User;

public class DevicesUsageLogDAOImpl implements DevicesUsageLogDAO {

	@Autowired
	DataSource dataSource;
	@Autowired
	UsersDAO usersDAO;

	public List<DeviceUsageLog> getDeviceUsageLogList() {
		List<DeviceUsageLog> devicesUsageLogList = new ArrayList<DeviceUsageLog>();
		String sql;

		sql = "select * from " + TableNames.DEVICES_USAGE_LOG;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		devicesUsageLogList = jdbcTemplate.query(sql, new DeviceUsageLogRowMapper());
		return devicesUsageLogList;
	}
	
	public List<DeviceUsageView> getDeviceUsageViewList(int deviceId) {
		List<DeviceUsageView> devicesUsageViewList = new ArrayList<DeviceUsageView>();
		String sql;

		sql = "select * from " + TableNames.DEVICE_USAGE_VIEW 
				+ " where " + DevicesUsageLogColumns.DEVICE_ID + " = " + deviceId;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		devicesUsageViewList = jdbcTemplate.query(sql, new DeviceUsageViewRowMapper());
		return devicesUsageViewList;
	}

	public void insertDeviceUsageLog(DeviceUsageLog deviceUsageLog) {
		insertDeviceUsageLog(deviceUsageLog.getUserId(), deviceUsageLog.getDate(), 
				deviceUsageLog.getDeviceId(), deviceUsageLog.getAction());
	}
	
	public void insertDeviceUsageLog(int deviceId, DeviceUsageActionEnum action) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User dbUser = usersDAO.getUserByEmail(user.getUsername());
		
		insertDeviceUsageLog((int)dbUser.getId(), new Timestamp(System.currentTimeMillis()), 
				deviceId, action);
	}
	
	public void insertDeviceUsageLog(int userId, int deviceId, DeviceUsageActionEnum action) {
		insertDeviceUsageLog(userId, new Timestamp(System.currentTimeMillis()), 
				deviceId, action);
	}
	
	public void insertDeviceUsageLog(int userId, Timestamp date, int deviceId, DeviceUsageActionEnum action) {
		String sql = "INSERT INTO " + TableNames.DEVICES_USAGE_LOG + " ("
				+ DevicesUsageLogColumns.USER_ID + ", " 
				+ DevicesUsageLogColumns.DATE + ", "
				+ DevicesUsageLogColumns.DEVICE_ID + ", " 
				+ DevicesUsageLogColumns.ACTION + 
				") VALUES (?, ?, ?, ?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { 
						userId, 
						date, 
						deviceId, 
						action.getNumVal()});
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