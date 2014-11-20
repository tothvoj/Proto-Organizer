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

	private String device;
	private String imei;
	private String platform;
	private String status;
	private String project;
	private String owner;
	private String reason;
	private String last_modified;
	private String email;
	private long id;
	private Timestamp date;
	private String origin;

	/**
	 * Construct.
	 * 
	 * @param device
	 *            device
	 * @param imei
	 *            imei
	 * @param status
	 *            status
	 * @param project
	 *            project
	 * @param owner
	 *            owner
	 * @param reason
	 *            reason
	 * @param origin
	 *            origin
	 */
	public Device(long id, String device, String platform, String imei,
			String status, String project, String owner, String reason,
			String last_modified, Timestamp date, String origin) {
		this.id = id;
		this.platform = platform;
		this.device = device;
		this.imei = imei;
		this.status = status;
		this.project = project;
		this.owner = owner;
		this.reason = reason;
		this.last_modified = last_modified;
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
	public String getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 * 
	 * @param project
	 *            project
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * Gets the owner.
	 * 
	 * @return owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets the owner.
	 * 
	 * @param owner
	 *            owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
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

	public String getLast_modified() {
		return last_modified;
	}

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPlatform() {
		return platform;
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
	
	public static Comparator<Device> PlatformASC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o1.platform, o2.platform);
		}

	};
	
	public static Comparator<Device> PlatformDESC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o2.platform, o1.platform);
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
	
	public static Comparator<Device> ProjectASC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o1.project, o2.project);
		}

	};
	
	public static Comparator<Device> ProjectDESC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o2.project, o1.project);
		}

	};
	
	public static Comparator<Device> OwnerASC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o1.owner, o2.owner);
		}

	};
	
	public static Comparator<Device> OwnerDESC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o2.owner, o1.owner);
		}

	};
	
	public static Comparator<Device> LastModifiedASC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o1.last_modified, o2.last_modified);
		}

	};
	
	public static Comparator<Device> LastModifiedDESC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o2.last_modified, o1.last_modified);
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
			return cp(o1.owner, o2.owner);
		}

	};
	
	public static Comparator<Device> OriginDESC = new Comparator<Device>() {

		public int compare(Device o1, Device o2) {
			return cp(o2.owner, o1.owner);
		}

	};
}
