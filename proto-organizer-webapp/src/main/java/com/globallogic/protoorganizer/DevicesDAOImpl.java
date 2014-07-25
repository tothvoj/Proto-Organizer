package com.globallogic.protoorganizer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.globallogic.protoorganizer.database.DevicesColumns;
import com.globallogic.protoorganizer.database.TableNames;
import com.globallogic.protoorganizer.jdbc.DeviceRowMapper;
import com.globallogic.protoorganizer.model.Device;
import com.mysql.jdbc.PreparedStatement;

public class DevicesDAOImpl implements DevicesDAO{

	@Autowired
	DataSource dataSource;

	public List<Device> getDevicesList(String filter) {
		List<Device> devicesList = new ArrayList<Device>();
		String sql;
		
		if (filter == null){
			sql = "select * from " + TableNames.DEVICES;
		}else{
			sql = "select * from " + TableNames.DEVICES + " where " 
						+ DevicesColumns.DEVICE + " like '%"  + filter + "%' or "
						+ DevicesColumns.PLATFORM + " like '%"  + filter + "%'";
		}		 

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		devicesList = jdbcTemplate.query(sql, new DeviceRowMapper());
		return devicesList;
	}

	public void insertDevice(Device device) {

		String sql = "INSERT INTO " + TableNames.DEVICES + " ("
				+ DevicesColumns.DEVICE + ", " + DevicesColumns.IMEI + ", "
				+ DevicesColumns.PLATFORM + ", " + DevicesColumns.DATE + ", "
				+ DevicesColumns.STATUS + ", " + DevicesColumns.PROJECT + ", "
				+ DevicesColumns.OWNER + ", " + DevicesColumns.REASON + ", "
				+ DevicesColumns.LAST_MODIFIED + ", " + DevicesColumns.EMAIL
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { device.getDevice(), device.getImei(),
						device.getPlatform(), getCurrentTime(),
						device.getStatus(), device.getProject(),
						device.getOwner(), device.getReason(),
						device.getLast_modified(), device.getEmail() });

	}

	public void deleteDevice(long id) {
		String sql = "delete from " + TableNames.DEVICES + " where "
				+ DevicesColumns.ID + "=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);

	}

	public void updateDevice(Device device) {

		String sql = "UPDATE " + TableNames.DEVICES + " set "
				+ DevicesColumns.DEVICE + " = ?," 
				+ DevicesColumns.IMEI	+ " = ?," 
				+ DevicesColumns.STATUS + " = ?,"
				+ DevicesColumns.PROJECT + " = ?," 
				+ DevicesColumns.OWNER	+ " = ?," 
				+ DevicesColumns.REASON + " = ?,"
				+ DevicesColumns.LAST_MODIFIED + " = ?," 
				+ DevicesColumns.EMAIL	+ " = ?," 
				+ " where " + DevicesColumns.ID + " = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { device.getDevice(), device.getImei(),
						device.getStatus(), device.getProject(),
						device.getOwner(), device.getReason(),
						device.getLast_modified(), device.getEmail(), device.getId() });
	}
	
	private Timestamp getCurrentTime(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		return timestamp;
	}
		
	public void deleteBatch(final List<Long> ids){
	 
		String sql = "delete from " + TableNames.DEVICES + " where "
				+ DevicesColumns.ID + "=?";
	 
	  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
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
