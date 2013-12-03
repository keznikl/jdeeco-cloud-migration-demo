package cz.cuni.mff.d3s.cloudmigration.component;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cz.cuni.mff.d3s.cloudmigration.data.ApplicationConfiguration;
import cz.cuni.mff.d3s.cloudmigration.data.MonitorDefinition;
import cz.cuni.mff.d3s.cloudmigration.data.NFPData;
import cz.cuni.mff.d3s.cloudmigration.data.Plan;
import cz.cuni.mff.d3s.deeco.annotations.*;
import cz.cuni.mff.d3s.deeco.annotations.Process;
import cz.cuni.mff.d3s.deeco.task.ParamHolder;

@Component
public class Planner {
	public ApplicationConfiguration app;
	
	public Collection<MonitorDefinition> plannerMonitorDefs;
	public Map<MonitorDefinition, Map<String, NFPData>> altNfpData;
	public Plan plan;
	
	
	public Planner(ApplicationConfiguration app) {
		this.app = app;
		plannerMonitorDefs = app.getMonitorDefinitions();
		altNfpData = new HashMap<>();		
	}
	
	
	// TODO write inputs and outputs by using specific wrappers instead of
	// duplicating the variable names... or maybe use some shortcut
	
	@Process
	@PeriodicScheduling(1000)
	public static void computePlan(
			@In("altNfpData") Map<MonitorDefinition, Map<String, NFPData>> altNfpData,
			@Out("plan") ParamHolder<Plan> plan
			) {
		// TODO: compute plan;
		Set<String> alternatives = new HashSet<>();
		for (Map<String, NFPData> monitorData: altNfpData.values()) {
			alternatives.addAll(monitorData.keySet());
		}
		System.out.println("Computing a plan for alternatives: " + alternatives.toString());
	}
	
}
