package cz.cuni.mff.d3s.cloudmigration;

import java.io.IOException;

import cz.cuni.mff.d3s.cloudmigration.component.Device;
import cz.cuni.mff.d3s.cloudmigration.component.Planner;
import cz.cuni.mff.d3s.cloudmigration.data.ApplicationConfiguration;
import cz.cuni.mff.d3s.cloudmigration.data.MonitorDefinition;
import cz.cuni.mff.d3s.cloudmigration.data.NodeConfiguration;
import cz.cuni.mff.d3s.cloudmigration.ensemble.Device2Monitor;
import cz.cuni.mff.d3s.cloudmigration.ensemble.Monitor2Planner;
import cz.cuni.mff.d3s.cloudmigration.ensemble.Planner2Device;
import cz.cuni.mff.d3s.deeco.annotations.processor.AnnotationProcessor;
import cz.cuni.mff.d3s.deeco.annotations.processor.AnnotationProcessorException;
import cz.cuni.mff.d3s.deeco.model.runtime.api.RuntimeMetadata;
import cz.cuni.mff.d3s.deeco.model.runtime.custom.RuntimeMetadataFactoryExt;
import cz.cuni.mff.d3s.deeco.runtime.RuntimeConfiguration;
import cz.cuni.mff.d3s.deeco.runtime.RuntimeFramework;
import cz.cuni.mff.d3s.deeco.runtime.RuntimeFrameworkBuilder;
import cz.cuni.mff.d3s.deeco.runtime.RuntimeConfiguration.Distribution;
import cz.cuni.mff.d3s.deeco.runtime.RuntimeConfiguration.Execution;
import cz.cuni.mff.d3s.deeco.runtime.RuntimeConfiguration.Scheduling;

public class Main {

	
	public static void main(String[] args) throws AnnotationProcessorException, IOException {
		ApplicationConfiguration ac = new ApplicationConfiguration(
				new MonitorDefinition("Af"),
				new MonitorDefinition("Ab"),
				new MonitorDefinition("Bf"),
				new MonitorDefinition("Bb"));
		
		Planner p = new Planner(ac);
		
		Device d1 = new Device(new NodeConfiguration("N1"));
		Device d2 = new Device(new NodeConfiguration("N2"));
		
		AnnotationProcessor processor = new AnnotationProcessor(RuntimeMetadataFactoryExt.eINSTANCE);
		RuntimeMetadata model = RuntimeMetadataFactoryExt.eINSTANCE.createRuntimeMetadata();		
		
		processor.process(model, Planner2Device.class, Device2Monitor.class, Monitor2Planner.class, p, d1, d2);
		
		RuntimeFrameworkBuilder builder = new RuntimeFrameworkBuilder(
				new RuntimeConfiguration(
						Scheduling.WALL_TIME, 
						Distribution.LOCAL, 
						Execution.SINGLE_THREADED));
		RuntimeFramework runtime = builder.build(model); 
		runtime.start();
		
		System.out.println("Press key to exit.");
		System.in.read();
		
		runtime.stop();
	}

}
