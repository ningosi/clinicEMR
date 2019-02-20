package org.openmrs.module.southsudanemr.metadata;

public class CommonMetadataBundle extends AbstractMetadataBundle{

    public void install() throws Exception {
        // install the patient identifier type
        install(PatientIdentifierTypes.ART_NUMBER);
        install(PatientIdentifierTypes.HEI_NUMBER);
    }

}
