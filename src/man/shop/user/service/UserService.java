package man.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import man.shop.user.dao.UserDao;
import man.shop.user.vo.User;
import man.shop.utils.UUIDUtils;

@Transactional
public class UserService {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
	
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	public void save(User user) {
		user.setState(0);
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		
	}
	
	public User login(User user){
		return userDao.login(user);
	}

}
