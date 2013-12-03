package cz.cuni.mff.d3s.cloudmigration.ensemble;

import java.util.Collection;

import cz.cuni.mff.d3s.cloudmigration.data.MonitorDefinition;
import cz.cuni.mff.d3s.deeco.annotations.*;
import cz.cuni.mff.d3s.deeco.task.ParamHolder;

@Ensemble
@PeriodicScheduling(2000)
public class Planner2Device {
	// TODO: represent roles explicitly
	
	@Membership
	public static boolean membership(
			@In("member.acceptsMonitors") Boolean deviceAcceptsMonitors) {
		return deviceAcceptsMonitors;
	}

	@KnowledgeExchange
	public static void knowledgeExchange(
			@In("coord.plannerMonitorDefs") Collection<MonitorDefinition> plannerDefs,
			@InOut("member.deviceMonitorDefs") ParamHolder<Collection<MonitorDefinition>> deviceDefs) {
		deviceDefs.value.addAll(plannerDefs);
	}

}
