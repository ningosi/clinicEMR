package org.openmrs.module.southsudanemr.reporting.library.cohorts;

import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;
import org.springframework.stereotype.Component;

@Component
public class SsEmrGenderCohortQueries {
	
	/**
	 * Patients who are female
	 * 
	 * @return the cohort definition
	 */
	public CohortDefinition female() {
		GenderCohortDefinition cohort = new GenderCohortDefinition();
		cohort.setName("FemaleCohort");
		cohort.setFemaleIncluded(true);
		cohort.setMaleIncluded(false);
		return cohort;
	}
	
	/**
	 * Patients who are male
	 * 
	 * @return the cohort definition
	 */
	public CohortDefinition male() {
		GenderCohortDefinition cohort = new GenderCohortDefinition();
		cohort.setName("MaleCohort");
		cohort.setMaleIncluded(true);
		cohort.setFemaleIncluded(false);
		return cohort;
	}
}
