package org.openmrs.module.southsudanemr.activator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.southsudanemr.SsEmrConstants;
import org.openmrs.scheduler.SchedulerService;
import org.openmrs.scheduler.TaskDefinition;

public class SsEmrAppConfigurationInitializer implements SsEmrInitializer {
	
	protected Log log = LogFactory.getLog(getClass());
	
	@Override
	public void started() {
		log.info("Setting administrative configurations for " + SsEmrConstants.MODULE_ID);
		
		SchedulerService schedulerService = Context.getSchedulerService();
		
		try {
			// set the AutoClose visits tasks to start automatically
			TaskDefinition autoCloseVisitsTask = (TaskDefinition) schedulerService.getTaskByName("Auto Close Visits Task");
			autoCloseVisitsTask.setStartOnStartup(true);
			schedulerService.saveTaskDefinition(autoCloseVisitsTask);
		}
		catch (Exception e) {
			log.error("Failed to setup scheduled tasks ", e);
		}
		
	}
	
	@Override
	public void stopped() {
		
	}
}
