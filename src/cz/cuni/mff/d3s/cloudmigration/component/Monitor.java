package cz.cuni.mff.d3s.cloudmigration.component;


import cz.cuni.mff.d3s.cloudmigration.data.MonitorDefinition;
import cz.cuni.mff.d3s.cloudmigration.data.NFPData;
import cz.cuni.mff.d3s.cloudmigration.data.NFPDeviceData;
import cz.cuni.mff.d3s.deeco.annotations.Component;
import cz.cuni.mff.d3s.deeco.annotations.In;
import cz.cuni.mff.d3s.deeco.annotations.Out;
import cz.cuni.mff.d3s.deeco.annotations.PeriodicScheduling;
import cz.cuni.mff.d3s.deeco.annotations.Process;
import cz.cuni.mff.d3s.deeco.task.ParamHolder;

@Component
public class Monitor {
	MonitorDefinition monitorDef;
	
	NFPDeviceData monitorDeviceData;
	NFPData monitorNfpData;
	
	public Monitor(MonitorDefinition monitorDef) {
		this.monitorDef = monitorDef;
	}
	
	
	// TODO: mark as inactive by default
	@Process
	@PeriodicScheduling(1000)
	public static void monitorPerformance(
			@Out("monitorNfpData") ParamHolder<NFPData> monitorNfpData
			) {
		// TODO: measure performance
	}
	
	@Process
	@PeriodicScheduling(1000)
	public static void estimatePerformance(
			@In("monitorDeviceData") NFPDeviceData monitorDeviceData,
			@Out("monitorNfpData") ParamHolder<NFPData> monitorNfpData
			) {
		// TODO: compute performance estimate
	}
}
