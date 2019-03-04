package org.openmrs.module.southsudanemr.reporting.library.dimension;

import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.openmrs.module.southsudanemr.reporting.library.cohorts.SsEmrGenderCohortQueries;
import org.openmrs.module.southsudanemr.utils.SsEmrReportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SsEmrCommonDimension {
	
	@Autowired
	private SsEmrGenderCohortQueries ssEmrGenderCohortQueries;
	
	/**
	 * Gender dimension
	 * 
	 * @return the {@link org.openmrs.module.reporting.indicator.dimension.CohortDimension}
	 */
	public CohortDefinitionDimension gender() {
		CohortDefinitionDimension dim = new CohortDefinitionDimension();
		dim.setName("gender");
		dim.addCohortDefinition("M", SsEmrReportUtils.map(ssEmrGenderCohortQueries.male(), ""));
		dim.addCohortDefinition("F", SsEmrReportUtils.map(ssEmrGenderCohortQueries.female(), ""));
		return dim;
	}
}
