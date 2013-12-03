package cz.cuni.mff.d3s.cloudmigration.ensemble;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.d3s.cloudmigration.data.MonitorDefinition;
import cz.cuni.mff.d3s.cloudmigration.data.NFPData;
import cz.cuni.mff.d3s.deeco.annotations.*;
import cz.cuni.mff.d3s.deeco.task.ParamHolder;

@Ensemble
@PeriodicScheduling(1000)
public class Monitor2Planner {
	
	@Membership
	public static boolean membership(
			@In("coord.plannerMonitorDefs") Collection<MonitorDefinition> plannerMonitorDefs,
			@In("member.monitorDef") MonitorDefinition monitorDef) {
		return plannerMonitorDefs.contains(monitorDef);
	}

	@KnowledgeExchange
	public static void knowledgeExchange(
			@InOut("coord.altNfpData") ParamHolder<Map<MonitorDefinition, Map<String, NFPData>>> altNfpData,
			@In("member.id") String monitorID,
			@In("member.monitorDef") MonitorDefinition monitorDef,
			@In("member.monitorNfpData") NFPData monitorNfpData) {
		Map<String, NFPData> altData = altNfpData.value.get(monitorDef);
		if (altData == null) {
			altData = new HashMap<>();
			altNfpData.value.put(monitorDef, altData);
		}
		altData.put(monitorID, monitorNfpData);
	}

}
