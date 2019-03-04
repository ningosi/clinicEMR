package org.openmrs.module.southsudanemr.metadata;

import org.openmrs.module.metadatadeploy.descriptor.PrivilegeDescriptor;
import org.openmrs.module.metadatadeploy.descriptor.RoleDescriptor;

import java.util.Arrays;
import java.util.List;

public class Roles {
	
	public static RoleDescriptor SS_USERS = new RoleDescriptor() {
		
		@Override
		public String role() {
			return "southsudanemr Role: SS_Users";
		}
		
		@Override
		public String description() {
			return "Use to manage clinician/user roles";
		}
		
		@Override
		public List<PrivilegeDescriptor> privileges() {
			return Arrays.asList(Privileges.APP_SSEMRREPORTS_PRV, Privileges.PRV_GET_PATIENTS, Privileges.PRV_GET_CONCEPTS,
			    Privileges.PRV_GET_USERS, Privileges.PRV_GET_LOCATIONS, Privileges.APP_COREAPPS_FIND_PATIENT,
			    Privileges.APP_COREAPPS_PATIENT_DASHBOARD, Privileges.APP_COREAPPS_PATIENT_VISITS,
			    Privileges.APP_REGISTRATION_REGISTER_PATIENT, Privileges.PRV_GET_VISITS, Privileges.PRV_ADD_PATIENT,
			    Privileges.PRV_EDIT_PATIENT, Privileges.PRV_GET_VISITS_TYPES, Privileges.PRV_GET_VISITS_ATTRIBUTE_TYPES,
			    Privileges.PRV_GET_PEOPLE, Privileges.PRV_GET_ENCOUNTERS, Privileges.PRV_GET_PROVIDERS,
			    Privileges.PRV_GET_CONCEPT_SOURCES, Privileges.PRV_GET_VISITS_PATIENT_DASHBOARD, Privileges.PRV_ADD_VISITS,
			    Privileges.PRV_ACTIVE_VISITS, Privileges.PRV_EDIT_VISITS, Privileges.PRV_LOCATION_ATTRIBUTE_TYPES,
			    Privileges.PRV_VISITS_CONFIGURE, Privileges.PRV_VISITS_DELETE, Privileges.PRV_VISITS_RETROSPECTIVE,
			    Privileges.PRV_VISITS_CORE_APPS_CREATE, Privileges.PRV_VISITS_CORE_APPS_END,
			    Privileges.PRV_ADD_PATIENT_IDENTIFIERS, Privileges.PRV_EDIT_PATIENT_IDENTIFIERS,
			    Privileges.PRV_GET_PATIENT_IDENTIFIER_TYPES, Privileges.PRV_GET_PATIENT_IDENTIFIERS
			
			);
		}
		
		public String uuid() {
			return "a35d71ae-3e60-11e9-ae37-0fb5e45020b5";
		}
	};
}
