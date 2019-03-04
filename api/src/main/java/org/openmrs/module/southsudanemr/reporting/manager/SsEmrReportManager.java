package org.openmrs.module.southsudanemr.reporting.manager;

import org.openmrs.module.reporting.common.ObjectUtil;
import org.openmrs.module.reporting.data.encounter.definition.EncounterDataDefinition;
import org.openmrs.module.reporting.data.encounter.definition.PatientToEncounterDataDefinition;
import org.openmrs.module.reporting.data.obs.definition.EncounterToObsDataDefinition;
import org.openmrs.module.reporting.data.obs.definition.ObsDataDefinition;
import org.openmrs.module.reporting.data.obs.definition.PatientToObsDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientDataDefinition;
import org.openmrs.module.reporting.dataset.definition.EncounterDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.ObsDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.manager.BaseReportManager;

public abstract class SsEmrReportManager extends BaseReportManager {
	
	protected void addColumn(PatientDataSetDefinition dsd, String columnName, PatientDataDefinition pdd) {
		dsd.addColumn(columnName, pdd, Mapped.straightThroughMappings(pdd));
	}
	
	protected void addColumn(EncounterDataSetDefinition dsd, String columnName, PatientDataDefinition pdd) {
		addColumn(dsd, columnName, new PatientToEncounterDataDefinition(pdd));
	}
	
	protected void addColumn(EncounterDataSetDefinition dsd, String columnName, EncounterDataDefinition edd) {
		dsd.addColumn(columnName, edd, ObjectUtil.toString(Mapped.straightThroughMappings(edd), "=", ","));
	}
	
	protected void addColumn(ObsDataSetDefinition dsd, String columnName, PatientDataDefinition pdd) {
		addColumn(dsd, columnName, new PatientToObsDataDefinition(pdd));
	}
	
	protected void addColumn(ObsDataSetDefinition dsd, String columnName, EncounterDataDefinition edd) {
		addColumn(dsd, columnName, new EncounterToObsDataDefinition(edd));
	}
	
	protected void addColumn(ObsDataSetDefinition dsd, String columnName, ObsDataDefinition odd) {
		dsd.addColumn(columnName, odd, ObjectUtil.toString(Mapped.straightThroughMappings(odd), "=", ","));
	}
}
