package man.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import man.shop.user.vo.User;

public class UserDao extends HibernateDaoSupport{
	//按名称查询是否有
	public User findByUsername(String username){ 
		String hql="from User where Username=?";
		List<User> list=this.getHibernateTemplate().find(hql,username);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public void save(User user){
		this.getHibernateTemplate().save(user);
	}
	
	public User login(User user){
		String hql="from User where username=? and password=? and state=?";
		List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
