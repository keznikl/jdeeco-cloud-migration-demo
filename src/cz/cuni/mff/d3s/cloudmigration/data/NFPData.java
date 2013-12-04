package cz.cuni.mff.d3s.cloudmigration.data;

import java.io.Serializable;

public class NFPData implements Serializable{

	private static final long serialVersionUID = -5183717837594970458L;

		String deviceName = "";

	public NFPData(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	
	
}
