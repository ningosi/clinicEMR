package org.openmrs.module.southsudanemr.reporting.library.cohorts;

import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.springframework.stereotype.Component;

@Component
public class AppointmentCohortQueries {
	
	public CohortDefinition appointmentsToday() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Patients who have appointments today");
		sql.setQuery("SELECT patient_id FROM patient");
		return sql;
	}
}
