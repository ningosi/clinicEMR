package org.openmrs.module.southsudanemr.metadata;

import org.openmrs.module.metadatadeploy.descriptor.EncounterTypeDescriptor;

public class EncounterTypes {
	
	public static EncounterTypeDescriptor ART_INITIAL_ENCOUNTER_TYPE = new EncounterTypeDescriptor() {
		
		@Override
		public String name() {
			return "ART enrollment";
		}
		
		@Override
		public String description() {
			return "Used for collecting information related to ART enrollment";
		}
		
		@Override
		public String uuid() {
			return "22cc4ade-39dc-11e9-bd8d-23b7f6ce3c7f";
		}
	};
	
	public static EncounterTypeDescriptor ART_FOLLOW_UP_ENCOUNTER_TYPE = new EncounterTypeDescriptor() {
		
		@Override
		public String name() {
			return "ART Follow up";
		}
		
		@Override
		public String description() {
			return "Used for collecting follow up information related to ART care";
		}
		
		@Override
		public String uuid() {
			return "a0f58114-39dc-11e9-aa78-57fcaf14551d";
		}
	};
}
