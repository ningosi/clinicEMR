package org.openmrs.module.clinicemr.messaging.task;

import org.openmrs.module.clinicemr.messaging.SendReminderMessage;
import org.openmrs.scheduler.tasks.AbstractTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendReminderMessageTask extends AbstractTask {
	
	private static final Logger log = LoggerFactory.getLogger(SendReminderMessageTask.class);
	
	@Override
	public void execute() {
		if (!isExecuting) {
			if (log.isDebugEnabled()) {
				log.error("Starting Send reminders messsages task...");
			}
			
			startExecuting();
			try {
				SendReminderMessage.sendReminderMssage();
			}
			catch (Exception e) {
				e.printStackTrace();
				log.error("Error sending reminders :", e);
			}
			finally {
				stopExecuting();
			}
		}
	}
}
