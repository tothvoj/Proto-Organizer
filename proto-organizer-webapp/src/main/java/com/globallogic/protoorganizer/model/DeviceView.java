package com.globallogic.protoorganizer.model;

import java.sql.Timestamp;
import java.util.Comparator;

public class DeviceView extends Device {

	private String projectName;
	private String platformName;
	private String platformVersion;
	private String ownerLastName;
	private String ownerFirstName;
	private String modifierFirstName;
	private String modifierLastName;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getPlatformVersion() {
		return platformVersion;
	}

	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getModifierFirstName() {
		return modifierFirstName;
	}

	public void setModifierFirstName(String modifierFirstName) {
		this.modifierFirstName = modifierFirstName;
	}

	public String getModifierLastName() {
		return modifierLastName;
	}

	public void setModifierLastName(String modifierLastName) {
		this.modifierLastName = modifierLastName;
	}

	public DeviceView() {
		
	}
	
	public DeviceView(long id, String device, int platformId, String platformName,
			String platformVersion, String imei, String status, int projectId,
			String projectName, int ownerId, String ownerFirstName, String ownerLastName,
			String reason, int lastModifiedBy, String modifiersFirstName, String modifiersLastName,
			Timestamp date, String origin) {
		
		this.id = id;
		this.device = device;
		this.platformId = platformId;
		this.platformName = platformName;
		this.platformVersion = platformVersion;
		this.imei = imei;
		this.status = status;
		this.projectId = projectId;
		this.projectName = projectName;
		this.ownerId = ownerId;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.reason = reason;
		this.lastModifiedBy = lastModifiedBy;
		this.modifierFirstName = modifiersFirstName;
		this.modifierLastName = modifiersLastName;
		this.date = date;
		this.origin = origin;
	}

	public String getOwnerFullName()
	{
		return ownerLastName + " " + ownerFirstName;
	}
	
	public String getModifierFullName()
	{
		return modifierLastName + " " + modifierFirstName;
	}
	
	public static Comparator<DeviceView> PlatformASC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o1.platformName, o2.platformName);
		}

	};
	
	public static Comparator<DeviceView> PlatformDESC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o2.platformName, o1.platformName);
		}

	};
	
	public static Comparator<DeviceView> ProjectASC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o1.projectName, o2.projectName);
		}

	};
	
	public static Comparator<DeviceView> ProjectDESC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o2.projectName, o1.projectName);
		}

	};
	
	public static Comparator<DeviceView> OwnerASC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o1.getOwnerFullName(), o2.getOwnerFullName());
		}

	};
	
	public static Comparator<DeviceView> OwnerDESC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o2.getOwnerFullName(), o1.getOwnerFullName());
		}

	};
	
	public static Comparator<DeviceView> LastModifiedASC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o1.getModifierFullName(), o2.getModifierFullName());
		}

	};
	
	public static Comparator<DeviceView> LastModifiedDESC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o2.getModifierFullName(), o1.getModifierFullName());
		}

	};
	
	// from Device object
	
	static <T extends Comparable<T>> int cp(T a, T b) {
	     return
	         a==null ?
	         (b==null ? 0 : Integer.MIN_VALUE) :
	         (b==null ? Integer.MAX_VALUE : a.compareTo(b));
	}

	public static Comparator<DeviceView> DeviceASC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o1.device, o2.device);
		}

	};
	
	public static Comparator<DeviceView> DeviceDESC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o2.device, o1.device);
		}

	};
	
	public static Comparator<DeviceView> ImeiASC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o1.imei, o2.imei);
		}

	};
	
	public static Comparator<DeviceView> ImeiDESC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o2.imei, o1.imei);
		}

	};
	
	public static Comparator<DeviceView> StatusASC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o1.status, o2.status);
		}

	};
	
	public static Comparator<DeviceView> StatusDESC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o2.status, o1.status);
		}

	};
	
	public static Comparator<DeviceView> DateASC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return
			         o1.date==null ?
			         (o2.date==null ? 0 : Integer.MIN_VALUE) :
			         (o2.date==null ? Integer.MAX_VALUE : o1.date.compareTo(o2.date));
		}

	};
	
	public static Comparator<DeviceView> DateDESC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return
			         o1.date==null ?
			         (o2.date==null ? 0 : Integer.MIN_VALUE) :
			         (o2.date==null ? Integer.MAX_VALUE : o2.date.compareTo(o1.date));
		}

	};
	
	public static Comparator<DeviceView> OriginASC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o1.origin, o2.origin);
		}

	};
	
	public static Comparator<DeviceView> OriginDESC = new Comparator<DeviceView>() {

		public int compare(DeviceView o1, DeviceView o2) {
			return cp(o2.origin, o1.origin);
		}

	};
	
	
}
