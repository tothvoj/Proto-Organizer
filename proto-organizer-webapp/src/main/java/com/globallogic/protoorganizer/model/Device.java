/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.protoorganizer.model;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 *
 * @author magyapa
 */
public class Device {

	protected String device;
	protected String imei;
	protected int platformId;
	protected String status;
	protected int projectId;
	protected int ownerId;
	protected String reason;
	protected int lastModifiedBy;
	protected String email;
	protected long id;
	protected Timestamp date;
	protected String origin;

	/**
	 * Construct.
	 * 
	 * @param device
	 *            device
	 * @param imei
	 *            imei
	 * @param status
	 *            status
	 * @param projectId
	 *            projectId
	 * @param owner
	 *            owner
	 * @param reason
	 *            reason
	 * @param origin
	 *            origin
	 */
	public Device(long id, String device, int platformId, String imei,
			String status, int projectId, int ownerId, String reason,
			int lastModifiedBy, Timestamp date, String origin) {
		this.id = id;
		this.platformId = platformId;
		this.device = device;
		this.imei = imei;
		this.status = status;
		this.projectId = projectId;
		this.ownerId = ownerId;
		this.reason = reason;
		this.lastModifiedBy = lastModifiedBy;
		this.date = date;
		this.origin = origin;
	}

	public Device() {

	}

	/**
	 * Gets the device.
	 * 
	 * @return device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * Sets the device.
	 * 
	 * @param device
	 *            device
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * Gets the imei.
	 * 
	 * @return imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * Sets the imei.
	 * 
	 * @param imei
	 *            imei
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * Gets the status.
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the project.
	 * 
	 * @return project
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * Sets the project.
	 * 
	 * @param project
	 *            project
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * Gets the owner.
	 * 
	 * @return owner
	 */
	public int getOwnerId() {
		return ownerId;
	}

	/**
	 * Sets the owner.
	 * 
	 * @param owner
	 *            owner
	 */
	public void setOwner(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getReason() {
		return reason;
	}

	/**
	 * Sets the reason.
	 * 
	 * @param reason
	 *            reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getLastModifiedBy() {
		return lastModifiedBy;
	}

	public String getEmail() {
		return email;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}

	public int getPlatformId() {
		return platformId;
	}

	public Timestamp getDate() {
		return date;
	}
	
	public String getOrigin() {
		return origin;
	}

	@Override
	public String toString() {
		return device;
	}

	public void printAll() {
		// System.out.println(this.device + this.imei + this.owner +
		// this.project + this.status);
	}
	
	static <T extends Comparable<T>> int cp(T a, T b) {
	     return
	         a==null ?
	         (b==null ? 0 : Integer.MIN_VALUE) :
	         (b==null ? Integer.MAX_VALUE : a.compareTo(b));
	}

	public static Comparator<Device> DeviceASC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o1.device, o2.device);
		}

	};
	
	public static Comparator<Device> DeviceDESC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o2.device, o1.device);
		}

	};
	
	public static Comparator<Device> ImeiASC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o1.imei, o2.imei);
		}

	};
	
	public static Comparator<Device> ImeiDESC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o2.imei, o1.imei);
		}

	};
	
	public static Comparator<Device> StatusASC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o1.status, o2.status);
		}

	};
	
	public static Comparator<Device> StatusDESC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o2.status, o1.status);
		}

	};
	
	public static Comparator<Device> DateASC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return
			         o1.date==null ?
			         (o2.date==null ? 0 : Integer.MIN_VALUE) :
			         (o2.date==null ? Integer.MAX_VALUE : o1.date.compareTo(o2.date));
		}

	};
	
	public static Comparator<Device> DateDESC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return
			         o1.date==null ?
			         (o2.date==null ? 0 : Integer.MIN_VALUE) :
			         (o2.date==null ? Integer.MAX_VALUE : o2.date.compareTo(o1.date));
		}

	};
	
	public static Comparator<Device> OriginASC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o1.ownerId, o2.ownerId);
		}

	};
	
	public static Comparator<Device> OriginDESC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o2.ownerId, o1.ownerId);
		}

	};
}
