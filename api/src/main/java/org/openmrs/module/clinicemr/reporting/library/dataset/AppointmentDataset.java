package org.openmrs.module.clinicemr.reporting.library.dataset;

import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.clinicemr.reporting.library.cohorts.AppointmentCohortQueries;
import org.openmrs.module.clinicemr.reporting.library.dimension.ClinicEmrCommonDimension;
import org.openmrs.module.clinicemr.reporting.library.indicators.ClinicEmrGeneralIndicator;
import org.openmrs.module.clinicemr.utils.ClinicEmrReportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AppointmentDataset extends BaseDataSet {
	
	@Autowired
	private AppointmentCohortQueries appointmentCohortQueries;
	
	@Autowired
	private ClinicEmrCommonDimension clinicEmrCommonDimension;
	
	@Autowired
	private ClinicEmrGeneralIndicator clinicEmrGeneralIndicator;
	
	public DataSetDefinition constructAppointmentDataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		dsd.setName("Daily Appointments");
		dsd.addDimension("gender", ClinicEmrReportUtils.map(clinicEmrCommonDimension.gender(), ""));
		//Start building the data set here
		addRow(
		    dsd,
		    "I1",
		    "Number of clients with appointments today",
		    ClinicEmrReportUtils.map(
		        clinicEmrGeneralIndicator.getIndicator("Clients with appointments today",
		            ClinicEmrReportUtils.map(appointmentCohortQueries.getAppointmentsToday(), "")), ""), getGenderList());
		addRow(
		    dsd,
		    "I2",
		    "Number of patients who visited facility today",
		    ClinicEmrReportUtils.map(
		        clinicEmrGeneralIndicator.getIndicator("Patients who visited facility today",
		            ClinicEmrReportUtils.map(appointmentCohortQueries.getClientsWhoVistedFacilityToday(), "")), ""),
		    getGenderList());
		
		addRow(
		    dsd,
		    "I3",
		    "Number of patients who missed appointment",
		    ClinicEmrReportUtils.map(
		        clinicEmrGeneralIndicator.getIndicator("Patients who missed",
		            ClinicEmrReportUtils.map(appointmentCohortQueries.getClientsWhoMissedAppointment(), "")), ""),
		    getGenderList());
		
		addRow(dsd, "I4", "Number of patients who visited facility today without appointments", ClinicEmrReportUtils.map(
		    clinicEmrGeneralIndicator.getIndicator(
		        "Patients who visited facility today without appointments",
		        ClinicEmrReportUtils.map(
		            appointmentCohortQueries.getNumberOfClientsWhoVisitedFacilityTodayWithoutAppointment(), "")), ""),
		    getGenderList());
		return dsd;
	}
	
	private List<ColumnParameters> getGenderList() {
		ColumnParameters male = new ColumnParameters("Male", "Male", "gender=M", "01");
		ColumnParameters female = new ColumnParameters("Female", "Female", "gender=F", "02");
		ColumnParameters totals = new ColumnParameters("Totals", "Totals", "", "03");
		return Arrays.asList(male, female, totals);
	}
}
