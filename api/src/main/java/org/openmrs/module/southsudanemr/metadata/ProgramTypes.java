package org.openmrs.module.southsudanemr.metadata;

import org.openmrs.module.metadatadeploy.descriptor.ProgramDescriptor;

public class ProgramTypes {
	
	public static ProgramDescriptor MH_PROGRAM = new ProgramDescriptor() {
		
		@Override
		public String conceptUuid() {
			return "160631AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		}
		
		@Override
		public String name() {
			return "ART PROGRAM";
		}
		
		@Override
		public String description() {
			return "ART program management";
		}
		
		@Override
		public String uuid() {
			return "5278a71c-3a6a-11e9-9aee-a7663f537b70";
		}
	};
}
