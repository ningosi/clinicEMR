package org.openmrs.module.clinicemr.reports;

import org.openmrs.module.reporting.ReportingException;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.clinicemr.reporting.library.dataset.AppointmentDataset;
import org.openmrs.module.clinicemr.reporting.manager.ClinicEmrDataExportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class DailyAppointmentsReport extends ClinicEmrDataExportManager {
	
	@Autowired
	private AppointmentDataset appointmentDateset;
	
	@Override
	public String getExcelDesignUuid() {
		return "408b4bb6-b082-11e9-a30a-c398aeaf111e";
	}
	
	@Override
	public String getUuid() {
		return "4c7033c4-b082-11e9-9ab3-d3f41e90f109";
	}
	
	@Override
	public String getName() {
		return "Daily Appointments";
	}
	
	@Override
	public String getDescription() {
		return "Daily Appointments for South Sudan";
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.setParameters(getParameters());
		
		rd.addDataSetDefinition("A", Mapped.mapStraightThrough(appointmentDateset.constructAppointmentDataset()));
		
		return rd;
	}
	
	@Override
	public String getVersion() {
		return "1.0-SNAPSHOT";
	}
	
	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		ReportDesign reportDesign = null;
		try {
			reportDesign = createXlsReportDesign(reportDefinition, "daily.xls", "Daily Appointments", getExcelDesignUuid(),
			    null);
			Properties props = new Properties();
			props.put("sortWeight", "5000");
			reportDesign.setProperties(props);
		}
		catch (IOException e) {
			throw new ReportingException(e.toString());
		}
		
		return Arrays.asList(reportDesign);
	}
}
