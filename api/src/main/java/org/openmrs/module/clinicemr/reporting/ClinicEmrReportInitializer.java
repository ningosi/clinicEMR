package org.openmrs.module.clinicemr.reporting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.ReportingConstants;
import org.openmrs.module.reporting.report.manager.ReportManager;
import org.openmrs.module.reporting.report.util.ReportUtil;
import org.openmrs.module.clinicemr.activator.ClinicEmrInitializer;
import org.openmrs.module.clinicemr.reporting.manager.ClinicEmrReportManager;
import org.openmrs.module.clinicemr.utils.ClinicEmrReportUtils;

public class ClinicEmrReportInitializer implements ClinicEmrInitializer {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public void started() {
		for (ReportManager reportManager : Context.getRegisteredComponents(ClinicEmrReportManager.class)) {
			if (reportManager.getClass().getAnnotation(Deprecated.class) != null) {
				// remove depricated reports
				ClinicEmrReportUtils.purgeReportDefinition(reportManager);
				log.info("Report " + reportManager.getName() + " is deprecated.  Removing it from database.");
			} else {
				// setup clinic active reports
				ClinicEmrReportUtils.setupReportDefinition(reportManager);
				log.info("Setting up report " + reportManager.getName() + "...");
			}
		}
		ReportUtil.updateGlobalProperty(ReportingConstants.GLOBAL_PROPERTY_DATA_EVALUATION_BATCH_SIZE, "-1");
	}
	
	@Override
	public void stopped() {
		for (ReportManager reportManager : Context.getRegisteredComponents(ClinicEmrReportManager.class)) {
			ClinicEmrReportUtils.purgeReportDefinition(reportManager);
			log.info("Report " + reportManager.getName() + " removed from database.");
		}
	}
}
