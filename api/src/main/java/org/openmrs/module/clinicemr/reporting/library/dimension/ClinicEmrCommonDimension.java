package org.openmrs.module.clinicemr.reporting.library.dimension;

import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.openmrs.module.clinicemr.reporting.library.cohorts.ClinicEmrGenderCohortQueries;
import org.openmrs.module.clinicemr.utils.ClinicEmrReportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClinicEmrCommonDimension {
	
	@Autowired
	private ClinicEmrGenderCohortQueries ssEmrGenderCohortQueries;
	
	/**
	 * Gender dimension
	 * 
	 * @return the {@link org.openmrs.module.reporting.indicator.dimension.CohortDimension}
	 */
	public CohortDefinitionDimension gender() {
		CohortDefinitionDimension dim = new CohortDefinitionDimension();
		dim.setName("gender");
		dim.addCohortDefinition("M", ClinicEmrReportUtils.map(ssEmrGenderCohortQueries.male(), ""));
		dim.addCohortDefinition("F", ClinicEmrReportUtils.map(ssEmrGenderCohortQueries.female(), ""));
		return dim;
	}
}
