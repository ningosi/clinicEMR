package org.openmrs.module.clinicemr.reporting.library.indicators;

import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.stereotype.Component;

@Component
public class ClinicEmrGeneralIndicator extends BaseIndicators {
	
	/**
	 * Methods that takes a cohort defintion and return and indicator definition for reuse
	 * 
	 * @return CohortIndicator
	 */
	public CohortIndicator getIndicator(String name, Mapped<CohortDefinition> cd) {
		return newCohortIndicator(name, cd);
	}
}
