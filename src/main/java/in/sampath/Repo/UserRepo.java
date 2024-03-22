package in.sampath.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import in.sampath.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByEmailAndPwd(String email,String pwd);

}
