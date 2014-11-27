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
import com.globallogic.protoorganizer.jdbc.DeviceRowMapper;
import com.globallogic.protoorganizer.jdbc.DeviceViewRowMapper;
import com.globallogic.protoorganizer.model.Device;
import com.globallogic.protoorganizer.model.DeviceView;
import com.globallogic.protoorganizer.model.User;
import com.mysql.jdbc.PreparedStatement;

public class DevicesDAOImpl implements DevicesDAO {

	@Autowired
	DataSource dataSource;

	public List<Device> getDevicesList(String filter) {
		List<Device> devicesList = new ArrayList<Device>();
		String sql;

		if (filter == null) {
			sql = "select * from " + TableNames.DEVICES_VIEW;
		} else {
			sql = "select * from " + TableNames.DEVICES_VIEW + " where "
					+ DevicesColumns.DEVICE + " like '%" + filter + "%'	or " 
					+ DevicesColumns.PLATFORM_NAME + " like '%" + filter + "%'";
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		devicesList = jdbcTemplate.query(sql, new DeviceRowMapper());
		return devicesList;
	}
	
	public List<DeviceView> getDevicesViewList(String filter) {
		List<DeviceView> devicesList = new ArrayList<DeviceView>();
		String sql;

		if (filter == null) {
			sql = "select * from " + TableNames.DEVICES_VIEW;
		} else {
			sql = "select * from " + TableNames.DEVICES_VIEW + " where "
					+ DevicesColumns.DEVICE + " like '%" + filter + "%'	or " 
					+ DevicesColumns.PLATFORM_NAME + " like '%" + filter + "%'";
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		devicesList = jdbcTemplate.query(sql, new DeviceViewRowMapper());
		return devicesList;
	}
	
	public List<Device> getRemovedDevicesList() {
		List<Device> devicesList = new ArrayList<Device>();
		String sql = "select * from " + TableNames.REMOVED_DEVICES;
		

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		devicesList = jdbcTemplate.query(sql, new DeviceRowMapper());
		return devicesList;
	}
	
	public List<DeviceView> getFullRemovedDevicesList() {
		List<DeviceView> devicesList = new ArrayList<DeviceView>();
		String sql = "select * from " + TableNames.REMOVED_DEVICES_VIEW;
		

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		devicesList = jdbcTemplate.query(sql, new DeviceViewRowMapper());
		return devicesList;
	}

	public void insertDevice(Device device) {

		String sql = "INSERT INTO " + TableNames.DEVICES + " ("
				+ DevicesColumns.DEVICE + ", " + DevicesColumns.IMEI + ", "
				+ DevicesColumns.PLATFORM_ID + ", " + DevicesColumns.DATE + ", "
				+ DevicesColumns.STATUS + ", " + DevicesColumns.PROJECT_ID + ", "
				+ DevicesColumns.OWNER_ID + ", " + DevicesColumns.REASON + ", "
				+ DevicesColumns.LAST_MODIFIED_BY + ", " + DevicesColumns.EMAIL
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { device.getDevice(), device.getImei(),
						device.getPlatformId(), getCurrentTime(),
						device.getStatus(), device.getProjectId(),
						device.getOwnerId(), device.getReason(),
						device.getLastModifiedBy(), device.getEmail() });

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
				+ DevicesColumns.PROJECT_ID + " = ?," + DevicesColumns.OWNER_ID
				+ " = ?," + DevicesColumns.REASON + " = ?,"
				+ DevicesColumns.LAST_MODIFIED_BY + " = ?," + DevicesColumns.EMAIL
				+ " = ?," + " where " + DevicesColumns.ID + " = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { device.getDevice(), device.getImei(),
						device.getStatus(), device.getProjectId(),
						device.getOwnerId(), device.getReason(),
						device.getLastModifiedBy(), device.getEmail(),
						device.getId() });
	}
	
	public void updateDevicePartially(List<DeviceView> devices) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Connection connection;
		
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);

			String sql = "UPDATE " + TableNames.DEVICES + " set "
					+ DevicesColumns.DEVICE + " = ?," 
					+ DevicesColumns.IMEI + " = ?," 
					+ DevicesColumns.PROJECT_ID + " = ?,"
					+ DevicesColumns.PLATFORM_ID + " = ?,"
					+ " = ?," + " where " + DevicesColumns.ID + " = ?";
			
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
			final int batchSize = 1000;
			int count = 0;
			
			for (Device device: devices) {

			    ps.setString(1, device.getDevice());
			    ps.setString(2, device.getImei());
			    ps.setInt(3, device.getProjectId());
			    ps.setInt(4, device.getPlatformId());
			    ps.setLong(5, device.getId());
			    ps.addBatch();

			    if(++count % batchSize == 0) {
			        ps.executeBatch();
			        ps.clearBatch(); 
			    }
			}
			
			ps.executeBatch();
			ps.clearBatch(); 
			connection.commit();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

//		String sql = "select * from " + TableNames.USERS + " where "
//				+ UsersColumns.ID + "=" + userID;
//		;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		users = jdbcTemplate.query(sql, new UserRowMapper());

		
		String sql2 = "UPDATE " + TableNames.DEVICES + " set "
				+ DevicesColumns.DATE + " = ?," + DevicesColumns.OWNER_ID
				+ " = ? " + " where " + DevicesColumns.ID + " = ?";

		jdbcTemplate.update(sql2, new Object[] { getCurrentTime(),
				userID, deviceID });
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
				+ DevicesColumns.PLATFORM_ID + ", " + DevicesColumns.DATE + ", "
				+ DevicesColumns.STATUS + ", " + DevicesColumns.PROJECT_ID + ", "
				+ DevicesColumns.OWNER_ID + ", " + DevicesColumns.REASON + ", "
				+ DevicesColumns.LAST_MODIFIED_BY + ", " + DevicesColumns.EMAIL
				+ ") SELECT " + DevicesColumns.DEVICE + ", " + DevicesColumns.IMEI + ", "
				+ DevicesColumns.PLATFORM_ID + ", " + DevicesColumns.DATE + ", "
				+ DevicesColumns.STATUS + ", " + DevicesColumns.PROJECT_ID + ", "
				+ DevicesColumns.OWNER_ID + ", " + DevicesColumns.REASON + ", "
				+ DevicesColumns.LAST_MODIFIED_BY + ", " + DevicesColumns.EMAIL
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

	public void sortInView(List<DeviceView> deviceList, int sortingParam) {

		Comparator<DeviceView> comparator = null;
		switch (sortingParam) {
		case 1:
			comparator = DeviceView.DeviceASC;
			break;

		case 2:
			comparator = DeviceView.DeviceDESC;
			break;

		case 3:
			comparator = DeviceView.PlatformASC;
			break;

		case 4:
			comparator = DeviceView.PlatformDESC;
			break;

		case 5:
			comparator = DeviceView.ImeiASC;
			break;

		case 6:
			comparator = DeviceView.ImeiDESC;
			break;

		case 7:
			comparator = DeviceView.StatusASC;
			break;

		case 8:
			comparator = DeviceView.StatusDESC;
			break;

		case 9:
			comparator = DeviceView.ProjectASC;
			break;

		case 10:
			comparator = DeviceView.ProjectDESC;
			break;

		case 11:
			comparator = DeviceView.OwnerASC;
			break;

		case 12:
			comparator = DeviceView.OwnerDESC;
			break;

		case 13:
			comparator = DeviceView.LastModifiedASC;
			break;

		case 14:
			comparator = DeviceView.LastModifiedDESC;
			break;

		case 15:
			comparator = DeviceView.DateASC;
			break;

		case 16:
			comparator = DeviceView.DateDESC;
			break;

		default:
			comparator = DeviceView.DeviceASC;
			break;
		}

		Collections.sort(deviceList, comparator);
	}
}
