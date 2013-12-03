package cz.cuni.mff.d3s.cloudmigration.component;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import cz.cuni.mff.d3s.cloudmigration.data.MonitorDefinition;
import cz.cuni.mff.d3s.cloudmigration.data.NFPDeviceData;
import cz.cuni.mff.d3s.cloudmigration.data.NodeConfiguration;
import cz.cuni.mff.d3s.deeco.annotations.*;
import cz.cuni.mff.d3s.deeco.annotations.Process;
import cz.cuni.mff.d3s.deeco.model.runtime.api.ComponentInstance;
import cz.cuni.mff.d3s.deeco.task.ParamHolder;

@Component
public class Device {

	NodeConfiguration nodeCfg;
	
	// represent roles somehow explicitly
	Collection<MonitorDefinition> deviceMonitorDefs;
	
	Map<MonitorDefinition, ComponentInstance> monitors;
	Map<MonitorDefinition, NFPDeviceData> nfpDeviceData;
	
	
	public Device(NodeConfiguration nodeCfg) {
		this.nodeCfg= nodeCfg;
		deviceMonitorDefs = new HashSet<>();
		monitors = new HashMap<>();
		nfpDeviceData = new HashMap<>();
	}
	
	@Process
	public static void produceNFPDeviceData(
			@TriggerOnChange @In("monitors") Map<MonitorDefinition, ComponentInstance> monitors,
			@InOut("nfpDeviceData") ParamHolder<Map<MonitorDefinition, NFPDeviceData>> nfpDeviceData
			) {
		//TODO: produce NFPDeviceData
	}
	
	@Process
	public static void createMonitors(
			@TriggerOnChange @In("deviceMonitorDefs") Collection<MonitorDefinition> deviceMonitorDefs,
			@InOut("monitors") ParamHolder<Map<MonitorDefinition, ComponentInstance>> monitors
			) {
		//TODO: instantiate monitors
	}
	
	
}
