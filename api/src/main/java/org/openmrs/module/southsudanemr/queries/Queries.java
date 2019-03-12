package org.openmrs.module.southsudanemr.queries;

import org.openmrs.module.southsudanemr.utils.SsEmrReportUtils;

public class Queries {
	
	public static final String appointmentToday(int conceptId) {
		return "SELECT pa.patient_id FROM patient pa INNER JOIN encounter e ON pa.patient_id=e.patient_id"
		        + " INNER JOIN obs o ON pa.patient_id=o.person_id WHERE o.voided=0 AND o.concept_id=" + conceptId
		        + " AND e.voided=0 AND o.value_datetime BETWEEN "
		        + SsEmrReportUtils.formatDate(SsEmrReportUtils.getStartOfDay()) + " AND "
		        + SsEmrReportUtils.formatDate(SsEmrReportUtils.getEndOfDay());
	}
}
