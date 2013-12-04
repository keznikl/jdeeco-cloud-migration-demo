package cz.cuni.mff.d3s.cloudmigration.component;

import java.util.ArrayList;
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
import cz.cuni.mff.d3s.deeco.annotations.processor.AnnotationProcessor;
import cz.cuni.mff.d3s.deeco.model.runtime.api.ComponentInstance;
import cz.cuni.mff.d3s.deeco.task.ParamHolder;
import cz.cuni.mff.d3s.deeco.task.ProcessContext;

@Component
public class Device {

	public NodeConfiguration nodeCfg;
	
	public Boolean acceptsMonitors;
	
	// represent roles somehow explicitly
	public Collection<MonitorDefinition> deviceMonitorDefs;
	public Collection<MonitorDefinition> oldDeviceMonitorDefs;

	
	public Map<MonitorDefinition, String> monitors;
	public Map<String, NFPDeviceData> nfpDeviceData;
	
	
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
			@In("nodeCfg") NodeConfiguration nodeCfg,
			@TriggerOnChange @In("monitors") Map<MonitorDefinition, String> monitors,
			@InOut("nfpDeviceData") ParamHolder<Map<String, NFPDeviceData>> nfpDeviceData
			) {
		for (String m: monitors.values()) {
			nfpDeviceData.value.put(m, 
					new NFPDeviceData(nodeCfg.getName()));
		}		
	}
	
	@Process
	public static void createMonitors(
			@In("nodeCfg") NodeConfiguration nodeCfg,
			@TriggerOnChange @In("deviceMonitorDefs") Collection<MonitorDefinition> deviceMonitorDefs,
			@InOut("oldDeviceMonitorDefs") ParamHolder<Collection<MonitorDefinition>> oldDeviceMonitorDefs,
			@InOut("monitors") ParamHolder<Map<MonitorDefinition, String>> monitors
			) {
		// check whether something actually changed
		if (oldDeviceMonitorDefs.value.equals(deviceMonitorDefs))
			return;
		
		// TODO: instantiate monitors
		// TODO: add support for model@runtime reflection		
		
				
		for (MonitorDefinition def: deviceMonitorDefs) {
			if (monitors.value.containsKey(def))
				continue;
			
			System.out.println(String.format("(%s) Installing monitor for %s", nodeCfg.getName(), def));
			
			try {
				Monitor m = new Monitor(def);
				// FIXME: this should be done via invokeAndWait()
				String instanceID = ProcessContext.startComponent(m);				
				monitors.value.put(def, instanceID);
			} catch (Exception e) {
				System.out.println("Installing monitor failed.");
			}			
		}
		
		oldDeviceMonitorDefs.value = deviceMonitorDefs;

	}
	
	@Process
	@PeriodicScheduling(1000)
	public static void getAcceptMonitors(
			@In("nodeCfg") NodeConfiguration nodeCfg,
			@InOut("acceptsMonitors") ParamHolder<Boolean> acceptsMonitors
			) {
		Boolean nextVal = new Random().nextBoolean();
		if (!acceptsMonitors.value.equals(nextVal)) { 
			System.out.println(String.format(
					"(%s) Changing acceptsMonitors==%s", nodeCfg.getName(), nextVal));
		}
		acceptsMonitors.value = nextVal;
	}
		
}
