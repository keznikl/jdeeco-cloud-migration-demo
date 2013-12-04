package cz.cuni.mff.d3s.cloudmigration.data;

import java.io.Serializable;


public class MonitorDefinition implements Serializable {
	private static final long serialVersionUID = 234123165415170007L;

	private String appComponentID = "";
	
	public MonitorDefinition(String appComponentID) {
		this.appComponentID = appComponentID;
	}

	public String getMonitorComponentID() {
		return appComponentID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((appComponentID == null) ? 0 : appComponentID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonitorDefinition other = (MonitorDefinition) obj;
		if (appComponentID == null) {
			if (other.appComponentID != null)
				return false;
		} else if (!appComponentID.equals(other.appComponentID))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return String.format("Monitor(%s)", appComponentID);
	}
	
	
	
}
