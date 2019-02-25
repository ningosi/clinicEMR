package org.openmrs.module.southsudanemr.metadata;

import org.openmrs.module.metadatadeploy.descriptor.PersonAttributeTypeDescriptor;

public class PersonAttributeTypes {
	
	public static PersonAttributeTypeDescriptor MOBILE_NUMBER = new PersonAttributeTypeDescriptor() {
		
		@Override
		public double sortWeight() {
			return 8;
		}
		
		@Override
		public String name() {
			return "Mobile Number";
		}
		
		@Override
		public String description() {
			return "Patients Telephone number to be used";
		}
		
		public String uuid() {
			return "f8578772-38dd-11e9-b4e2-8fa3c8be8eca";
		}
		
		@Override
		public boolean searchable() {
			return true;
		}
	};
	
	public static PersonAttributeTypeDescriptor ALTERNATIVE_MOBILE_NUMBER = new PersonAttributeTypeDescriptor() {
		
		@Override
		public double sortWeight() {
			return 8;
		}
		
		@Override
		public String name() {
			return "Alternative Number";
		}
		
		@Override
		public String description() {
			return "Alternative Patients Telephone number to be used";
		}
		
		public String uuid() {
			return "250edff4-38de-11e9-94e6-1fda4e6c49ee";
		}
		
		@Override
		public boolean searchable() {
			return true;
		}
	};
	
	public static PersonAttributeTypeDescriptor TREATMENT_SUPPOTER_FIRST_NAME = new PersonAttributeTypeDescriptor() {
		
		@Override
		public double sortWeight() {
			return 8;
		}
		
		@Override
		public String name() {
			return "Treatment supporter first name";
		}
		
		@Override
		public String description() {
			return "";
		}
		
		public String uuid() {
			return "2d17c3f0-38ed-11e9-ac35-0f1befa73a84";
		}
	};
	
	public static PersonAttributeTypeDescriptor TREATMENT_SUPPOTER_LAST_NAME = new PersonAttributeTypeDescriptor() {
		
		@Override
		public double sortWeight() {
			return 8;
		}
		
		@Override
		public String name() {
			return "Treatment supporter last name";
		}
		
		@Override
		public String description() {
			return "";
		}
		
		public String uuid() {
			return "5f0c5fce-38ed-11e9-9f45-ab353e2c8c47";
		}
	};
	
	public static PersonAttributeTypeDescriptor TREATMENT_SUPPOTER_MOBILE_NUMBER = new PersonAttributeTypeDescriptor() {
		
		@Override
		public double sortWeight() {
			return 8;
		}
		
		@Override
		public String name() {
			return "Treatment supporter mobile number";
		}
		
		@Override
		public String description() {
			return "";
		}
		
		public String uuid() {
			return "99a0f6a4-38ed-11e9-b80b-c3d8b563284d";
		}
	};
	
	public static PersonAttributeTypeDescriptor TREATMENT_SUPPOTER_ADDRESS = new PersonAttributeTypeDescriptor() {
		
		@Override
		public double sortWeight() {
			return 8;
		}
		
		@Override
		public String name() {
			return "Treatment supporter address";
		}
		
		@Override
		public String description() {
			return "";
		}
		
		public String uuid() {
			return "c4acc616-38ed-11e9-a13f-d3831b0ff624";
		}
	};
	
	public static PersonAttributeTypeDescriptor HOME_BASED_CARE_PROVIDER = new PersonAttributeTypeDescriptor() {
		
		@Override
		public double sortWeight() {
			return 8;
		}
		
		@Override
		public String name() {
			return "Homebased care provider";
		}
		
		@Override
		public String description() {
			return "";
		}
		
		public String uuid() {
			return "150c5ee6-38ee-11e9-85fa-2b4a1bb1310c";
		}
	};
}
