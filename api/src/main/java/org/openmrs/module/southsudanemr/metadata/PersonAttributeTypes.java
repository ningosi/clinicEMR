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
        public boolean searchable() {return true;}
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
        public boolean searchable() {return true;}
    };
}
