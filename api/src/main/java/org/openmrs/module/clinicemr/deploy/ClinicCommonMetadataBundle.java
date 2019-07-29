package org.openmrs.module.clinicemr.deploy;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.clinicemr.metadata.EncounterTypes;
import org.openmrs.module.clinicemr.metadata.PatientIdentifierTypes;
import org.openmrs.module.clinicemr.metadata.PersonAttributeTypes;
import org.openmrs.module.clinicemr.metadata.Privileges;
import org.openmrs.module.clinicemr.metadata.ProgramTypes;
import org.openmrs.module.clinicemr.metadata.Roles;
import org.springframework.stereotype.Component;

@Component
public class ClinicCommonMetadataBundle extends AbstractMetadataBundle {
	
	public void install() throws Exception {
		// install the patient identifier type
		
		log.info("Installing PatientIdentifierTypes");
		install(PatientIdentifierTypes.PATIENT_CLINIC_NUMBER);
		install(PatientIdentifierTypes.PATIENT_MOBILE_NUMBER);
		install(PatientIdentifierTypes.NATIONAL_ID_NUMBER);
		log.info("Patient IdentifierTypes installed");
		
		// install person attribute types
		log.info("Installing PatientAttributeTypes");
		install(PersonAttributeTypes.NEXT_OF_KIN_NAME);
		install(PersonAttributeTypes.NEXT_OF_KIN_TELEPHONE);
		install(PersonAttributeTypes.MODE_OF_PAYMENT);
		install(PersonAttributeTypes.MEMBERSHIP_NUMBER);
		install(PersonAttributeTypes.SCHEME_EMPLOYER);
		install(PersonAttributeTypes.NEXT_OF_KIN_EMAIL);
		install(PersonAttributeTypes.ADDRESS);
		install(PersonAttributeTypes.REG_DATE);
		
		//install encounter types
		install(EncounterTypes.CONSULTATIONS);
		
		//install programs
		install(ProgramTypes.CLINIC_PROGRAM);
		
		//install privileges
		install(Privileges.APP_CLINICEMRREPORTS_PRV);
		//install roles here
		install(Roles.CLINIC_USERS);
		
	}
	
}
