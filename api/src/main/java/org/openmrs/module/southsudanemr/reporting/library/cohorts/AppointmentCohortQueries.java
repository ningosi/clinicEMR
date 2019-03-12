package org.openmrs.module.southsudanemr.reporting.library.cohorts;

import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.southsudanemr.metadata.SsDictionary;
import org.openmrs.module.southsudanemr.queries.Queries;
import org.springframework.stereotype.Component;

@Component
public class AppointmentCohortQueries {
	
	public CohortDefinition appointmentsToday() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Patients who have appointments today");
		sql.setQuery(Queries.appointmentToday(SsDictionary.getConcept(SsDictionary.NEXT_VISIT_DATE).getConceptId()));
		return sql;
	}
}
