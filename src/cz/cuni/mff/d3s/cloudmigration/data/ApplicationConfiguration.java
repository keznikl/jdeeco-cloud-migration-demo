package cz.cuni.mff.d3s.cloudmigration.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class ApplicationConfiguration {

	Collection<MonitorDefinition> monitorDefinitions;

	public ApplicationConfiguration(
			MonitorDefinition  ... monitorDefinitions ) {
		this.monitorDefinitions = new HashSet<>(Arrays.asList(monitorDefinitions));
	}

	public Collection<MonitorDefinition> getMonitorDefinitions() {
		return monitorDefinitions;
	}
}
