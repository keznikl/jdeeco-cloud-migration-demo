package cz.cuni.mff.d3s.cloudmigration.component;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import cz.cuni.mff.d3s.cloudmigration.data.MonitorDefinition;
import cz.cuni.mff.d3s.cloudmigration.data.NFPDeviceData;
import cz.cuni.mff.d3s.cloudmigration.data.NodeConfiguration;
import cz.cuni.mff.d3s.deeco.annotations.*;
import cz.cuni.mff.d3s.deeco.annotations.Process;
import cz.cuni.mff.d3s.deeco.model.runtime.api.ComponentInstance;
import cz.cuni.mff.d3s.deeco.task.ParamHolder;

@Component
public class Device {

	public NodeConfiguration nodeCfg;
	
	public Boolean acceptsMonitors;
	
	// represent roles somehow explicitly
	public Collection<MonitorDefinition> deviceMonitorDefs;
	public Collection<MonitorDefinition> oldDeviceMonitorDefs;

	
	public Map<MonitorDefinition, ComponentInstance> monitors;
	public Map<MonitorDefinition, NFPDeviceData> nfpDeviceData;
	
	
	public Device(NodeConfiguration nodeCfg) {
		this.nodeCfg= nodeCfg;
		deviceMonitorDefs = new HashSet<>();
		oldDeviceMonitorDefs = new HashSet<>();
		monitors = new HashMap<>();
		nfpDeviceData = new HashMap<>();
		acceptsMonitors = false;
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
			@InOut("oldDeviceMonitorDefs") ParamHolder<Collection<MonitorDefinition>> oldDeviceMonitorDefs,
			@InOut("monitors") ParamHolder<Map<MonitorDefinition, ComponentInstance>> monitors
			) {
		// check whether something actually changed
		if (oldDeviceMonitorDefs.value.equals(deviceMonitorDefs))
			return;
		
		//TODO: instantiate monitors
		// TODO: add support for model@runtime reflection
		System.out.println("Installing monitors for defs: " + deviceMonitorDefs + ", old: " + oldDeviceMonitorDefs.value);
		
		oldDeviceMonitorDefs.value = deviceMonitorDefs;

	}
	
	@Process
	@PeriodicScheduling(1000)
	public static void getAcceptMonitors(			
			@InOut("acceptsMonitors") ParamHolder<Boolean> acceptsMonitors
			) {
		Boolean nextVal = new Random().nextBoolean();
		if (!acceptsMonitors.value.equals(nextVal)) 
			System.out.println("Changing acceptsMonitors==" + nextVal);
		acceptsMonitors.value = nextVal;
	}
		
}
