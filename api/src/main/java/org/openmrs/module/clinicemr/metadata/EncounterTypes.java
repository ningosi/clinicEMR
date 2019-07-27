package org.openmrs.module.clinicemr.metadata;

import org.openmrs.module.metadatadeploy.descriptor.EncounterTypeDescriptor;

public class EncounterTypes {
	
	public static EncounterTypeDescriptor CONSULTATIONS = new EncounterTypeDescriptor() {
		
		@Override
		public String name() {
			return "Consultations Encounter Type";
		}
		
		@Override
		public String description() {
			return "Used for collecting information related to the consultations of the patient";
		}
		
		@Override
		public String uuid() {
			return "3345059a-b083-11e9-9d55-8f91276ffdd1";
		}
	};
	
}
