package in.sampath.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import in.sampath.Repo.UserRepo;

import in.sampath.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

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
			
			try {
				sendRegistrationEmail(saveduser.getEmail(), user);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	
	private void sendRegistrationEmail(String email, User user) throws MessagingException {
        String subject = "your Account created Sucessfully  -"+user.getName();
        String text1="<h1>Congratulation your Account Created Succefully..... </h1>";
       MimeMessage mimeMessage = emailsender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
       helper.setTo(email);
       helper.setText(text1, true);
       helper.setSubject(subject);
        //SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject(subject);
//        message.setText(text1);
        emailsender.send(mimeMessage); // Use emailSender service to send email
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
