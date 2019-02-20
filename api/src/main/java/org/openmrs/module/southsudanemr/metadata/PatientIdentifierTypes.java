package org.openmrs.module.southsudanemr.metadata;

public class PatientIdentifierTypes {
	
	public static PatientIdentifierTypeDescriptor ART_NUMBER = new PatientIdentifierTypeDescriptor() {
		
		@Override
		public String name() {
			return "Unique ART No";
		}
		
		@Override
		public String description() {
			return "This a unique number for ART";
		}
		
		public String uuid() {
			return "5a580b6a-3508-11e9-aba5-dbb924006cb7";
		}
		
		public String formatDescription() {
			return " The first three letters of the facility, followed by / then 5 numbers with no spaces";
		}
	};

	public static PatientIdentifierTypeDescriptor HEI_NUMBER = new PatientIdentifierTypeDescriptor() {

		@Override
		public String name() {
			return "HEI No";
		}

		@Override
		public String description() {
			return "HIV Exposed infant number";
		}

		public String uuid() {
			return "4fcb62cc-3509-11e9-8c1e-73e323400228";
		}

		public String formatDescription() {
			return "";
		}
	};
}
