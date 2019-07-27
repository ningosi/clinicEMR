package org.openmrs.module.clinicemr.reporting.library.cohorts;

import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.clinicemr.metadata.ClinicDictionary;
import org.openmrs.module.clinicemr.queries.Queries;
import org.openmrs.module.clinicemr.utils.ClinicEmrReportUtils;
import org.springframework.stereotype.Component;

@Component
public class AppointmentCohortQueries {
	
	public CohortDefinition getAppointmentsToday() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Patients who have appointments today");
		sql.setQuery(Queries.getAppointmentToday(ClinicDictionary.getConcept(ClinicDictionary.NEXT_VISIT_DATE)
		        .getConceptId()));
		return sql;
	}
	
	public CohortDefinition getClientsWhoVistedFacilityToday() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Patients who visited the facility today");
		sql.setQuery(Queries.getClientsWhoVisitedFacilityToday());
		return sql;
	}
	
	public CohortDefinition getClientsWhoMissedAppointment() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Patients who missed appointment");
		sql.setQuery(Queries.getPatientsWhoMissedAppointment(ClinicDictionary.getConcept(ClinicDictionary.NEXT_VISIT_DATE)
		        .getConceptId()));
		return sql;
	}
	
	public CohortDefinition getNumberOfClientsWhoVisitedFacilityTodayWithoutAppointment() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Number of clients who visited facility today without the appointments");
		cd.addSearch("hasVisitToday", ClinicEmrReportUtils.map(getAppointmentsToday(), ""));
		cd.addSearch("hasAppointmentToday", ClinicEmrReportUtils.map(getClientsWhoVistedFacilityToday(), ""));
		cd.setCompositionString("hasVisitToday AND NOT hasAppointmentToday");
		return cd;
	}
}
