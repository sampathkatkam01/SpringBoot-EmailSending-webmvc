package in.sampath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.sampath.entity.User;
import in.sampath.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userservice;
	
	
	//get login
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "index";	
	}
	
	//post login handle
	@PostMapping("/login")
	public String loginhandle(User user,Model model) {
		User userobj
		= userservice.findUser(user.getEmail(), user.getPwd());
		if(userobj == null) {
			model.addAttribute("emsg", "Invalid Credentials");
			return "index";
		}else {
			model.addAttribute("smsg", userobj.getName()+",Welcome To The DashBoard");
			return "dashboard";
		}
	}
	
	//get register
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	
	//post register
	@PostMapping("/register")
	public String registerhandle(User user ,Model model) {
		boolean status= userservice.saveUser(user);
		if(status) {
			model.addAttribute("smsg", user.getName()+",you got Registerd sucessfully");
		}else {
			model.addAttribute("emsg", user.getName()+",you are not Registerd");
		}
		return "register";
	}
	
	//logout
	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("user",new User() );
		return "index";
	}

}
