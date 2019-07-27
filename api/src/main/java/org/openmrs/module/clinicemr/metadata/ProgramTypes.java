package org.openmrs.module.clinicemr.metadata;

import org.openmrs.module.metadatadeploy.descriptor.ProgramDescriptor;

public class ProgramTypes {
	
	public static ProgramDescriptor CLINIC_PROGRAM = new ProgramDescriptor() {
		
		@Override
		public String conceptUuid() {
			return "160631AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		}
		
		@Override
		public String name() {
			return "CLINIC PROGRAM";
		}
		
		@Override
		public String description() {
			return "Clinic program management";
		}
		
		@Override
		public String uuid() {
			return "72423708-b084-11e9-b246-83f267ec5a36";
		}
	};
}
