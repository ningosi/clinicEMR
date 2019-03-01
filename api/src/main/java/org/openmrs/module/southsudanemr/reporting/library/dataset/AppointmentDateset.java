package org.openmrs.module.southsudanemr.reporting.library.dataset;

import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDateset extends BaseDataSet {
	
	public DataSetDefinition constructAppointmentDatset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		dsd.setName("Daily Appointments");
		return dsd;
	}
}
