package org.openmrs.module.clinicemr.fragment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.patient.PatientCalculationService;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.ListResult;
import org.openmrs.module.clinicemr.calculation.ConfigCalculationUtils;
import org.openmrs.module.clinicemr.calculation.ConfigCalculations;
import org.openmrs.module.clinicemr.metadata.ClinicDictionary;
import org.openmrs.module.emrapi.patient.PatientDomainWrapper;
import org.openmrs.module.reporting.common.ObjectUtil;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LatestObsWidgetFragmentController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	public void controller(FragmentModel model, @FragmentParam("patient") PatientDomainWrapper patient) {
		PatientCalculationService patientCalculationService = Context.getService(PatientCalculationService.class);
		PatientCalculationContext context = patientCalculationService.createCalculationContext();
		context.setNow(new Date());
		
		model.addAttribute("details", getAllEncounters(patient.getId(), context));
		
	}
	
	private Map<String, String> getAllEncounters(Integer patientId, PatientCalculationContext context) {
		EncounterType encounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "3345059a-b083-11e9-9d55-8f91276ffdd1");
		CalculationResultMap map = ConfigCalculations.allEncounters(encounterType, Arrays.asList(patientId), context);
		ListResult listResult = (ListResult) map.get(patientId);
		List<Encounter> encounterList = ConfigCalculationUtils.extractResultValues(listResult);
		Map<String, String> details = new TreeMap<String, String>();
		for (Encounter encounter : encounterList) {
			Set<Obs> encounterObs = encounter.getAllObs();
			for (Obs obs : encounterObs) {
				if (obs.getConcept().equals(ClinicDictionary.getConcept(ClinicDictionary.NOTES))) {
					details.put(ObjectUtil.formatDate(encounter.getEncounterDatetime(), "dd-MMM-yyyy", Locale.getDefault()),
					    obs.getValueText());
				}
			}
		}
		return details;
		
	}
}
