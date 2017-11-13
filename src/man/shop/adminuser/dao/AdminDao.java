package man.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import man.shop.adminuser.vo.AdminUser;

public class AdminDao  extends HibernateDaoSupport{

	public  AdminUser login(AdminUser existAdminUser) {
		String hql="from AdminUser where username=? and password=?";
		List<AdminUser> list=this.getHibernateTemplate().find(hql,existAdminUser.getUsername(),existAdminUser.getPassword());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	

}
