package org.openmrs.module.clinicemr.reports;

import org.openmrs.module.reporting.ReportingException;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.clinicemr.reporting.library.dataset.AppointmentListTodayDataset;
import org.openmrs.module.clinicemr.reporting.manager.ClinicEmrDataExportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class AppointmentListToday extends ClinicEmrDataExportManager {
	
	@Autowired
	private AppointmentListTodayDataset appointmentListTodayDataset;
	
	@Override
	public String getExcelDesignUuid() {
		return "1a1c9034-b082-11e9-8c8e-17db97e2201f";
	}
	
	@Override
	public String getUuid() {
		return "26cdf32c-b082-11e9-b3dd-4b1a4edc5b78";
	}
	
	@Override
	public String getName() {
		return "Appointments Today";
	}
	
	@Override
	public String getDescription() {
		return "Patients who have appointments today";
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.setParameters(getParameters());
		
		rd.addDataSetDefinition("L1",
		    Mapped.mapStraightThrough(appointmentListTodayDataset.constructAppointmentListTodayDataset()));
		
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
			reportDesign = createXlsReportDesign(reportDefinition, "today.xls", "Today Appointments", getExcelDesignUuid(),
			    null);
			Properties props = new Properties();
			props.put("repeatingSections", "sheet:1,row:5,dataset:L1");
			props.put("sortWeight", "5000");
			reportDesign.setProperties(props);
		}
		catch (IOException e) {
			throw new ReportingException(e.toString());
		}
		
		return Arrays.asList(reportDesign);
	}
}
