package cz.cuni.mff.d3s.cloudmigration.ensemble;

import java.util.Map;

import cz.cuni.mff.d3s.cloudmigration.data.MonitorDefinition;
import cz.cuni.mff.d3s.cloudmigration.data.NFPDeviceData;
import cz.cuni.mff.d3s.deeco.annotations.*;
import cz.cuni.mff.d3s.deeco.model.runtime.api.ComponentInstance;
import cz.cuni.mff.d3s.deeco.task.ParamHolder;

@Ensemble
@PeriodicScheduling(1000)
public class Device2Monitor {
	
	@Membership
	public static boolean membership(
			@In("coord.monitors") Map<MonitorDefinition, ComponentInstance> monitors,
			@In("member.monitorDef") MonitorDefinition monitorDef) {
		return monitors.containsKey(monitorDef);
	}

	@KnowledgeExchange
	public static void knowledgeExchange(
			@In("coord.nfpDeviceData") NFPDeviceData nfpDeviceData,
			@Out("member.monitorDeviceData") ParamHolder<NFPDeviceData> monitorDeviceData) {
		monitorDeviceData.value = nfpDeviceData;
	}

}
