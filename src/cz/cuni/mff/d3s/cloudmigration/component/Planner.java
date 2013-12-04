package cz.cuni.mff.d3s.cloudmigration.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		List<String> alternatives = new ArrayList<>();
		for (Entry<MonitorDefinition, Map<String, NFPData>> entry: altNfpData.entrySet()){			
			for (NFPData nfpdata: entry.getValue().values()) {
				if (nfpdata != null) {
					alternatives.add(String.format("%s@%s", 
							entry.getKey().getMonitorComponentID(), 
							nfpdata.getDeviceName()));
				}
			}			
		}		
		
		System.out.println("Computing a plan for alternatives: " + alternatives.toString());
	}
	
}
