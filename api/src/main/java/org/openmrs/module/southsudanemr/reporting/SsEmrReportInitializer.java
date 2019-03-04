package org.openmrs.module.southsudanemr.reporting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.ReportingConstants;
import org.openmrs.module.reporting.report.manager.ReportManager;
import org.openmrs.module.reporting.report.util.ReportUtil;
import org.openmrs.module.southsudanemr.activator.SsEmrInitializer;
import org.openmrs.module.southsudanemr.reporting.manager.SsEmrReportManager;
import org.openmrs.module.southsudanemr.utils.SsEmrReportUtils;

public class SsEmrReportInitializer implements SsEmrInitializer {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public void started() {
		for (ReportManager reportManager : Context.getRegisteredComponents(SsEmrReportManager.class)) {
			if (reportManager.getClass().getAnnotation(Deprecated.class) != null) {
				// remove depricated reports
				SsEmrReportUtils.purgeReportDefinition(reportManager);
				log.info("Report " + reportManager.getName() + " is deprecated.  Removing it from database.");
			} else {
				// setup EPTS active reports
				SsEmrReportUtils.setupReportDefinition(reportManager);
				log.info("Setting up report " + reportManager.getName() + "...");
			}
		}
		ReportUtil.updateGlobalProperty(ReportingConstants.GLOBAL_PROPERTY_DATA_EVALUATION_BATCH_SIZE, "-1");
	}
	
	@Override
	public void stopped() {
		for (ReportManager reportManager : Context.getRegisteredComponents(SsEmrReportManager.class)) {
			SsEmrReportUtils.purgeReportDefinition(reportManager);
			log.info("Report " + reportManager.getName() + " removed from database.");
		}
	}
}
