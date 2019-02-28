package org.openmrs.module.southsudanemr.activator;

public interface SsEmrInitializer {
	
	/**
	 * Run during the activator started method
	 */
	void started();
	
	/**
	 * Run during the activator stopped method
	 */
	void stopped();
}
