package in.sampath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import in.sampath.Repo.UserRepo;

import in.sampath.entity.User;

@Service
public class serviceimpl implements UserService {
	@Autowired
	private UserRepo repo;

	@Autowired
	private JavaMailSender emailsender;

	@Override
	public boolean saveUser(User user) {
		User saveduser = repo.save(user);
		if(saveduser.getId()!=null) {
			
			sendRegistrationEmail(saveduser.getEmail(), user);
		}
		return true;
	}
	
	
	private void sendRegistrationEmail(String email, User user) {
        String subject = "your Account created Sucessfully  -"+user.getName();
        String text1="Congratulation your Account Created Succefully..... ";
        String text = "We have not heard back from you regarding case 170958044401823 in the last 7 days. We hope this means you have resolved your issue or question. If you need continued support, please contact us using the following URL:\r\n"
        		+ "\r\n"
        		+ "https://console.aws.amazon.com/support/home?#/case/?caseId=170958044401823&displayId=170958044401823&language=en\r\n"
        		+ "\r\n"
        		+ "(If you will connect by federation, log in before following the link.)\r\n"
        		+ "\r\n"
        		+ "Best Regards,\r\n"
        		+ "\r\n"
        		+ "Amazon Web Services\r\n"
        		+ "\r\n"
        		+ "*Please note: this e-mail was sent from an address that cannot accept incoming e-mail. Please use the link above if you need to contact us about this same issue.\r\n"
        		+ "\r\n"
        		+ "Amazon Web Services, Inc. is a subsidiary of Amazon.com, Inc. Amazon.com is a registered trademark of Amazon.com, Inc.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text1);
        emailsender.send(message); // Use emailSender service to send email
    } 

	@Override
	public User findUser(String email, String pwd) {
		
//		Optional<User> optional = repo.findbyEmailAndpwdUser(email, pwd);
//		if(optional.isPresent()) {
//			User user = optional.get();
//			System.out.println(user.getEmail());
//			return Optional.empty();
//		}else {
//    		return Optional.empty();
//    	}
			return repo.findByEmailAndPwd(email, pwd);
	}

}
