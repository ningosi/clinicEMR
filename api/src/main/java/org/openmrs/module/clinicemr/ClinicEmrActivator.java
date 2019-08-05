/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.clinicemr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.module.Module;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.appframework.service.AppFrameworkService;
import org.openmrs.module.emrapi.EmrApiConstants;
import org.openmrs.module.metadatadeploy.api.MetadataDeployService;
import org.openmrs.module.metadatamapping.MetadataTermMapping;
import org.openmrs.module.metadatamapping.api.MetadataMappingService;
import org.openmrs.module.clinicemr.activator.ClinicEmrAppConfigurationInitializer;
import org.openmrs.module.clinicemr.activator.ClinicEmrHtmlFormsInitializer;
import org.openmrs.module.clinicemr.activator.ClinicEmrInitializer;
import org.openmrs.module.clinicemr.deploy.ClinicCommonMetadataBundle;
import org.openmrs.module.clinicemr.metadata.PatientIdentifierTypes;
import org.openmrs.module.clinicemr.reporting.ClinicEmrReportInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class ClinicEmrActivator extends BaseModuleActivator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * @see #started()
	 */
	public void started() {
		
		log.info("Starting ClinicEMR");
		AppFrameworkService appFrameworkService = Context.getService(AppFrameworkService.class);
		AdministrationService administrationService = Context.getAdministrationService();
		
		appFrameworkService.disableApp("referenceapplication.registrationapp.registerPatient");
		appFrameworkService.disableApp("referenceapplication.vitals");
		appFrameworkService.disableExtension("referenceapplication.realTime.simpleAdmission");
		appFrameworkService.disableExtension("referenceapplication.realTime.simpleVisitNote");
		appFrameworkService.disableExtension("referenceapplication.realTime.vitals");
		appFrameworkService.disableApp("coreapps.mostRecentVitals");
		appFrameworkService.disableApp("coreapps.findPatient");
		
		// the extension to the edit person details
		appFrameworkService.disableExtension("org.openmrs.module.registrationapp.editPatientDemographics");
		
		// disable apps on the Clinican facing dashboard added through coreapps 1.12.0
		//appFrameworkService.disableApp("coreapps.diagnoses");
		appFrameworkService.disableApp("coreapps.latestObsForConceptList");
		appFrameworkService.disableApp("coreapps.obsAcrossEncounters");
		appFrameworkService.disableApp("coreapps.obsGraph");
		appFrameworkService.enableApp("coreapps.visitByEncounterType");
		appFrameworkService.disableApp("coreapps.dataIntegrityViolations");
		appFrameworkService.disableApp("coreapps.conditionlist");
		appFrameworkService.disableApp("reportingui.reports");
		appFrameworkService.disableApp("coreapps.relationships");
		appFrameworkService.disableApp("appointmentschedulingui.schedulingAppointmentApp");
		appFrameworkService.disableApp("appointmentschedulingui.requestAppointmentApp");
		appFrameworkService.disableExtension("appointmentschedulingui.tab");
		appFrameworkService
		        .disableExtension("org.openmrs.module.appointmentschedulingui.firstColumnFragments.patientDashboard.patientAppointments");
		appFrameworkService.disableExtension("org.openmrs.module.registrationapp.editPatientContactInfo");
		//appFrameworkService.disableExtension("org.openmrs.module.allergyui.patientDashboard.secondColumnFragments");
		
		MetadataDeployService deployService = Context.getService(MetadataDeployService.class);
		
		try {
			installCommonMetadata(deployService);
		}
		catch (Exception ex) {
			throw new RuntimeException("Failed to setup initial data", ex);
		}
		//load the initializers here to install components
		for (ClinicEmrInitializer initializer : getInitializers()) {
			initializer.started();
		}
		
		// save defined global properties
		administrationService.saveGlobalProperties(configureGlobalProperties());
		log.info("Clinic EMR Module started");
	}
	
	/**
	 * @see #shutdown()
	 */
	public void shutdown() {
		log.info("Shutdown ClinicEMR");
	}
	
	/**
	 * @see
	 */
	public void willStop() {
		// run the initializers
		for (ClinicEmrInitializer initializer : getInitializers()) {
			initializer.stopped();
		}
		log.info("Stopping South sudan Configurations Module");
	}
	
	private void installCommonMetadata(MetadataDeployService deployService) {
		try {
			deployService.installBundle(Context.getRegisteredComponents(ClinicCommonMetadataBundle.class).get(0));
		}
		
		catch (Exception e) {
			Module mod = ModuleFactory.getModuleById("clinicemr");
			ModuleFactory.stopModule(mod);
			throw new RuntimeException("Failed to install the common metadata ", e);
		}
	}
	
	private List<ClinicEmrInitializer> getInitializers() {
		List<ClinicEmrInitializer> l = new ArrayList<ClinicEmrInitializer>();
		l.add(new ClinicEmrAppConfigurationInitializer());
		l.add(new ClinicEmrHtmlFormsInitializer());
		l.add(new ClinicEmrReportInitializer());
		return l;
	}
	
	private List<GlobalProperty> configureGlobalProperties() {
		List<GlobalProperty> properties = new ArrayList<GlobalProperty>();
		// The primary identifier type now uses metadata mapping instead of a global property
		MetadataMappingService metadataMappingService = Context.getService(MetadataMappingService.class);
		MetadataTermMapping primaryIdentifierTypeMapping = metadataMappingService.getMetadataTermMapping(
		    EmrApiConstants.EMR_METADATA_SOURCE_NAME, EmrApiConstants.PRIMARY_IDENTIFIER_TYPE);
		PatientIdentifierType patientId = Context.getPatientService().getPatientIdentifierTypeByUuid(
		    PatientIdentifierTypes.PATIENT_CLINIC_NUMBER.uuid());
		
		if (!patientId.getUuid().equals(primaryIdentifierTypeMapping.getMetadataUuid())) {
			primaryIdentifierTypeMapping.setMappedObject(patientId);
			metadataMappingService.saveMetadataTermMapping(primaryIdentifierTypeMapping);
		}
		// set the name of the application
		properties.add(new GlobalProperty("application.name", "ClinicEMR - Clinic eHealth Solution"));
		// Remove the regular expression to validate names
		properties.add(new GlobalProperty("patient.nameValidationRegex", ""));
		// the search mode for patients to enable searching any part of names rather than the beginning
		properties.add(new GlobalProperty("patientSearch.matchMode", "ANYWHERE"));
		// enable searching on parts of the patient identifier
		// the prefix and suffix provide a % round the entered search term with a like
		properties.add(new GlobalProperty("patient.identifierPrefix", "%"));
		properties.add(new GlobalProperty("patient.identifierSuffix", "%"));
		
		// the RegeX and Search patterns should be empty so that the prefix and suffix matching above can work
		properties.add(new GlobalProperty("patient.identifierRegex", ""));
		properties.add(new GlobalProperty("patient.identifierSearchPattern", ""));
		// disable the appointmentshedulingui which currently has issues
		properties.add(new GlobalProperty("appointmentschedulingui.started", "false"));
		//disable the atlas module since it is NOT used
		properties.add(new GlobalProperty("atlas.started", "false"));
		// the name of the custom registration app
		properties.add(new GlobalProperty("registrationapp.customRegistrationAppId", "clinicemr.registerPatient"));
		// enable the register patient button to appear on the search widget
		properties.add(new GlobalProperty("coreapps.showRegisterPatientOnSearchWidget", "true"));
		return properties;
	}
	
}
