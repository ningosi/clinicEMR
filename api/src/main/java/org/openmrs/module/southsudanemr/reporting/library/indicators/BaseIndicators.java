package org.openmrs.module.southsudanemr.reporting.library.indicators;

import org.openmrs.Location;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.CohortIndicator;

import java.util.Date;

public abstract class BaseIndicators {
	
	protected CohortIndicator newCohortIndicator(String name, Mapped<CohortDefinition> cohort) {
		CohortIndicator i = new CohortIndicator(name);
		i.setCohortDefinition(cohort);
		i.addParameter(new Parameter("startDate", "Start date", Date.class));
		i.addParameter(new Parameter("endDate", "End date", Date.class));
		i.addParameter(new Parameter("location", "Facility", Location.class));
		
		return i;
	}
}
