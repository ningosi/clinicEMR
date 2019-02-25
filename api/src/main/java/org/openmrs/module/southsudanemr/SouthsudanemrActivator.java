/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.southsudanemr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.module.Module;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.appframework.service.AppFrameworkService;
import org.openmrs.module.metadatadeploy.api.MetadataDeployService;
import org.openmrs.module.southsudanemr.deploy.SsCommonMetadataBundle;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class SouthsudanemrActivator extends BaseModuleActivator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * @see #started()
	 */
	public void started() {
		
		log.info("Started Southsudanemr");
		AppFrameworkService appFrameworkService = Context.getService(AppFrameworkService.class);
		appFrameworkService.disableApp("referenceapplication.registrationapp.registerPatient");
		appFrameworkService.disableExtension("org.openmrs.module.registrationapp.editPatientDemographics");
		appFrameworkService.disableApp("referenceapplication.vitals");
		appFrameworkService.disableExtension("referenceapplication.realTime.simpleAdmission");
		appFrameworkService.disableExtension("referenceapplication.realTime.simpleVisitNote");
		appFrameworkService.disableApp("coreapps.mostRecentVitals");
		appFrameworkService.disableApp("coreapps.findPatient");
		
		// the extension to the edit person details
		appFrameworkService.disableExtension("org.openmrs.module.registrationapp.editPatientDemographics");
		
		// disable apps on the Clinican facing dashboard added through coreapps 1.12.0
		appFrameworkService.disableApp("coreapps.diagnoses");
		appFrameworkService.disableApp("coreapps.latestObsForConceptList");
		appFrameworkService.disableApp("coreapps.obsAcrossEncounters");
		appFrameworkService.disableApp("coreapps.obsGraph");
		appFrameworkService.enableApp("coreapps.visitByEncounterType");
		appFrameworkService.disableApp("coreapps.dataIntegrityViolations");
		appFrameworkService.disableApp("coreapps.conditionlist");
		appFrameworkService.disableApp("reportingui.reports");
		appFrameworkService.disableApp("coreapps.relationships");
		
		MetadataDeployService deployService = Context.getService(MetadataDeployService.class);
		try {
			installCommonMetadata(deployService);
		}
		catch (Exception ex) {
			throw new RuntimeException("Failed to setup initial data", ex);
		}
		log.info("South Sudan Module started");
	}
	
	/**
	 * @see #shutdown()
	 */
	public void shutdown() {
		log.info("Shutdown Southsudanemr");
	}
	
	private void installCommonMetadata(MetadataDeployService deployService) {
		try {
			deployService.installBundle(Context.getRegisteredComponents(SsCommonMetadataBundle.class).get(0));
		}
		
		catch (Exception e) {
			Module mod = ModuleFactory.getModuleById("southsudanemr");
			ModuleFactory.stopModule(mod);
			throw new RuntimeException("Failed to install the common metadata ", e);
		}
	}
	
}
