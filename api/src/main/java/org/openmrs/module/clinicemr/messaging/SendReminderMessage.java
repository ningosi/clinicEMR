package org.openmrs.module.clinicemr.messaging;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import org.apache.commons.lang.StringUtils;
import org.openmrs.Patient;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.patient.PatientCalculationService;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.clinicemr.calculation.ConfigCalculations;
import org.openmrs.module.clinicemr.calculation.ConfigEmrCalculationUtils;
import org.openmrs.module.clinicemr.metadata.PersonAttributeTypes;
import org.openmrs.module.clinicemr.utils.ConfigCoreUtils;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.util.OpenmrsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SendReminderMessage {
	
	private static final int LANG_ENG = 1;
	
	private static final int LANG_SWA = 2;
	
	private static final Logger log = LoggerFactory.getLogger(SendReminderMessage.class);
	
	private static String getTokenFromFile() throws IOException {
		File file = new File(OpenmrsUtil.getApplicationDataDirectory() + "/token.txt");
		if (file.exists() && file.canRead()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String key = reader.readLine();
			reader.close();
			return key;
		}
		return "";
		
	}
	
	public static void buildMessage(String phone, String message) throws IOException {
		String username = "test";
		String apiKey = getTokenFromFile();
		String from = "Test";
		if (StringUtils.isNotEmpty(apiKey)) {
			AfricasTalking.initialize(username, apiKey);
			SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);
			
			try {
				List<Recipient> response = sms.send(message, from, new String[] { phone }, true);
				for (Recipient recipient : response) {
					if (!recipient.status.equals("Success")) {
						log.error("SMS sending Failure : Number %s status %s", recipient.number, recipient.status);
					}
					
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static String missedAppointMentMesssage(String lang, String name) {
		if (lang.equals("english")) {
			return String
			        .format(
			            "Dear %s , our records show that you have missed your last appointment(s). This could affect your progress and health goals. You are advised to please urgently visit your doctor within the next 24 hours. Please visit your health care provider urgently.",
			            name);
		} else {
			return String
			        .format(
			            "Hujambo %s, records zetu zinatuonyesha kwamba ulikosa kutembelea kituo cha afya kama ilivyopangwa. Hio inaweza kuduru afya na malengo yako ya kiafya. Tafadhali tembelea mhudumu wako wa afya mara moja.",
			            name);
		}
	}
	
	public static void sendReminderMssage() throws IOException {
		PatientCalculationService patientCalculationService = Context.getService(PatientCalculationService.class);
		PatientCalculationContext context = patientCalculationService.createCalculationContext();
		context.setNow(new Date());
		
		String message = "";
		Set<Patient> results = missedAppointments(context);
		//provide person attribute here for the phone umber and language
		PersonAttributeType attributeType = MetadataUtils.existing(PersonAttributeType.class, "");
		PersonAttributeType langAttributeType = MetadataUtils.existing(PersonAttributeType.class, "");
		
		for (Patient patient : results) {
			String name = String.format("%s %s", patient.getGivenName(), patient.getFamilyName());
			PersonAttribute personAttribute = patient.getPerson().getAttribute(attributeType);
			if (langAttributeType != null) {
				PersonAttribute language = patient.getPerson().getAttribute(langAttributeType);
				message = missedAppointMentMesssage(
				    (language != null && StringUtils.isNotEmpty(language.getValue()) ? language.getValue() : "kiswahili"),
				    name);
			} else {
				message = missedAppointMentMesssage("kiswahili", name);
			}
			if (StringUtils.isNotEmpty(convertPhoneNumber(personAttribute.getValue()))) {
				buildMessage(convertPhoneNumber(personAttribute.getValue()), message);
			}
		}
		
	}
	
	private static Set<Patient> missedAppointments(PatientCalculationContext context) {
		Set<Patient> results = new HashSet<Patient>();
		CalculationResultMap lastReturnDateObss = ConfigCalculations.lastObs(Context.getConceptService()
		        .getConceptByUuid(""), ConfigCoreUtils.cohort(), context);
		for (Patient patient : Context.getPatientService().getAllPatients()) {
			Date lastScheduledReturnDate = ConfigEmrCalculationUtils.datetimeObsResultForPatient(lastReturnDateObss,
			    patient.getId());
			if (lastScheduledReturnDate != null
			        && ConfigEmrCalculationUtils.daysSince(lastScheduledReturnDate, context) == 1) {
				results.add(patient);
			}
		}
		return results;
	}
	
	private static String convertPhoneNumber(String number) {
		String phoneNUmber = number.trim().replaceAll("\\s", "");
		if (phoneNUmber.length() == 13 && phoneNUmber.substring(0, Math.min(phoneNUmber.length(), 4)).equals("+254")) {
			return phoneNUmber;
		} else if (phoneNUmber.length() == 10 && phoneNUmber.charAt(0) == '0') {
			phoneNUmber = phoneNUmber.replaceFirst("0", "+254");
			return phoneNUmber;
		} else if (phoneNUmber.length() == 12 && phoneNUmber.substring(0, Math.min(phoneNUmber.length(), 3)).equals("254")) {
			phoneNUmber = String.format("%s%S", "+", phoneNUmber);
			return phoneNUmber;
		} else if (phoneNUmber.length() == 9) {
			phoneNUmber = String.format("%s%s", "+254", phoneNUmber);
			return phoneNUmber;
		} else {
			return "";
		}
	}
}
