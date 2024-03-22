package in.sampath.service;

import in.sampath.entity.User;

public interface UserService {
	
	public boolean saveUser(User user);
	
	public User findUser(String email,String pwd);

}
