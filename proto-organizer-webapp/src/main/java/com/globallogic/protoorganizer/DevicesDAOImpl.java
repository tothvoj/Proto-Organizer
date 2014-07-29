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

import com.globallogic.protoorganizer.database.DevicesColumns;
import com.globallogic.protoorganizer.database.TableNames;
import com.globallogic.protoorganizer.database.UsersColumns;
import com.globallogic.protoorganizer.jdbc.DeviceRowMapper;
import com.globallogic.protoorganizer.jdbc.UserRowMapper;
import com.globallogic.protoorganizer.model.Device;
import com.globallogic.protoorganizer.model.User;

public class DevicesDAOImpl implements DevicesDAO {

	@Autowired
	DataSource dataSource;

	public List<Device> getDevicesList(String filter) {
		List<Device> devicesList = new ArrayList<Device>();
		String sql;

		if (filter == null) {
			sql = "select * from " + TableNames.DEVICES;
		} else {
			sql = "select * from " + TableNames.DEVICES + " where "
					+ DevicesColumns.DEVICE + " like '%" + filter + "%' or "
					+ DevicesColumns.PLATFORM + " like '%" + filter + "%'";
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		devicesList = jdbcTemplate.query(sql, new DeviceRowMapper());
		return devicesList;
	}
	
	public List<Device> getRemovedDevicesList() {
		List<Device> devicesList = new ArrayList<Device>();
		String sql = "select * from " + TableNames.REMOVED_DEVICES;
		

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
				+ DevicesColumns.DEVICE + " = ?," + DevicesColumns.IMEI
				+ " = ?," + DevicesColumns.STATUS + " = ?,"
				+ DevicesColumns.PROJECT + " = ?," + DevicesColumns.OWNER
				+ " = ?," + DevicesColumns.REASON + " = ?,"
				+ DevicesColumns.LAST_MODIFIED + " = ?," + DevicesColumns.EMAIL
				+ " = ?," + " where " + DevicesColumns.ID + " = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { device.getDevice(), device.getImei(),
						device.getStatus(), device.getProject(),
						device.getOwner(), device.getReason(),
						device.getLast_modified(), device.getEmail(),
						device.getId() });
	}

	private Timestamp getCurrentTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		return timestamp;
	}

	public void deleteBatch(final List<Long> ids) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		insertBatch(jdbcTemplate, ids);
		
		String sql = "delete from " + TableNames.DEVICES + " where "
				+ DevicesColumns.ID + "=?";
		
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

	public void changeOwner(long deviceID, long userID) {

		List<User> users = new ArrayList<User>();

		String sql = "select * from " + TableNames.USERS + " where "
				+ UsersColumns.ID + "=" + userID;
		;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		users = jdbcTemplate.query(sql, new UserRowMapper());

		if (users.size() > 0) {

			String sql2 = "UPDATE " + TableNames.DEVICES + " set "
					+ DevicesColumns.DATE + " = ?," + DevicesColumns.OWNER
					+ " = ? " + " where " + DevicesColumns.ID + " = ?";

			jdbcTemplate.update(sql2, new Object[] { getCurrentTime(),
					users.get(0).getName(), deviceID });
		}
	}

	private List<Device> selectDevices(JdbcTemplate jdbcTemplate, final List<Long> ids) {

		PreparedStatementCreator creator = new PreparedStatementCreator() {

			public java.sql.PreparedStatement createPreparedStatement(
					Connection con) throws SQLException {

				String selectSQL = "select * from " + TableNames.DEVICES
						+ " where " + DevicesColumns.ID + "=?";
				java.sql.PreparedStatement preparedStatement = con
						.prepareStatement(selectSQL);
				preparedStatement.setArray(
						1,
						con.createArrayOf("ids",
								ids.toArray(new Long[ids.size()])));

				return preparedStatement;
			}
		};

		List<Device> devicesList = new ArrayList<Device>();
		devicesList = jdbcTemplate.query(creator,
				new DeviceRowMapper());
		
		return devicesList;
	}
	
	private void insertBatch(JdbcTemplate jdbcTemplate, final List<Long> ids) {

		String sql = "INSERT INTO " + TableNames.REMOVED_DEVICES + " ("
				+ DevicesColumns.DEVICE + ", " + DevicesColumns.IMEI + ", "
				+ DevicesColumns.PLATFORM + ", " + DevicesColumns.DATE + ", "
				+ DevicesColumns.STATUS + ", " + DevicesColumns.PROJECT + ", "
				+ DevicesColumns.OWNER + ", " + DevicesColumns.REASON + ", "
				+ DevicesColumns.LAST_MODIFIED + ", " + DevicesColumns.EMAIL
				+ ") SELECT " + DevicesColumns.DEVICE + ", " + DevicesColumns.IMEI + ", "
				+ DevicesColumns.PLATFORM + ", " + DevicesColumns.DATE + ", "
				+ DevicesColumns.STATUS + ", " + DevicesColumns.PROJECT + ", "
				+ DevicesColumns.OWNER + ", " + DevicesColumns.REASON + ", "
				+ DevicesColumns.LAST_MODIFIED + ", " + DevicesColumns.EMAIL
				+ " FROM " + TableNames.DEVICES + " WHERE " + TableNames.DEVICES + ".id=?";

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

	public void sort(List<Device> deviceList, int sortingParam) {

		Comparator<Device> comparator = null;
		switch (sortingParam) {
		case 1:
			comparator = Device.DeviceASC;
			break;

		case 2:
			comparator = Device.DeviceDESC;
			break;

		case 3:
			comparator = Device.PlatformASC;
			break;

		case 4:
			comparator = Device.PlatformDESC;
			break;

		case 5:
			comparator = Device.ImeiASC;
			break;

		case 6:
			comparator = Device.ImeiDESC;
			break;

		case 7:
			comparator = Device.StatusASC;
			break;

		case 8:
			comparator = Device.StatusDESC;
			break;

		case 9:
			comparator = Device.ProjectASC;
			break;

		case 10:
			comparator = Device.ProjectDESC;
			break;

		case 11:
			comparator = Device.OwnerASC;
			break;

		case 12:
			comparator = Device.OwnerDESC;
			break;

		case 13:
			comparator = Device.LastModifiedASC;
			break;

		case 14:
			comparator = Device.LastModifiedDESC;
			break;

		case 15:
			comparator = Device.DateASC;
			break;

		case 16:
			comparator = Device.DateDESC;
			break;

		default:
			comparator = Device.DeviceASC;
			break;
		}

		Collections.sort(deviceList, comparator);
	}

}
