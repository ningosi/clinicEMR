package org.openmrs.module.clinicemr.activator;

public interface ClinicEmrInitializer {
	
	/**
	 * Run during the activator started method
	 */
	void started();
	
	/**
	 * Run during the activator stopped method
	 */
	void stopped();
}
