package cz.cuni.mff.d3s.cloudmigration.data;

import java.io.Serializable;

public class NFPDeviceData implements Serializable {

	private static final long serialVersionUID = 2585319525270001179L;

	String deviceName = "";

	public String getDeviceName() {
		return deviceName;
	}

	public NFPDeviceData(String deviceName) {
		this.deviceName = deviceName;
	}	
	
}
