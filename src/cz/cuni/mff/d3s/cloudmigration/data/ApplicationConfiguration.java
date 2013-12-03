package cz.cuni.mff.d3s.cloudmigration.data;

import java.util.Collection;

public class ApplicationConfiguration {

	Collection<MonitorDefinition> monitorDefinitions;

	public ApplicationConfiguration(
			Collection<MonitorDefinition> monitorDefinitions) {
		this.monitorDefinitions = monitorDefinitions;
	}

	public Collection<MonitorDefinition> getMonitorDefinitions() {
		return monitorDefinitions;
	}
}
