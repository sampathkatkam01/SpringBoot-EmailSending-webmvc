package in.sampath.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender emailsender;
	
	
	 
	 public void sendemail(SimpleMailMessage email) {
			SimpleMailMessage message = new SimpleMailMessage();
//			message.setTo(to);
//			message.setSubject(subject);
//			message.setText(text);
			emailsender.send(message);
		}

}
