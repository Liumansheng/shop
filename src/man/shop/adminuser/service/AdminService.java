package man.shop.adminuser.service;

import java.util.List;

import man.shop.adminuser.dao.AdminDao;
import man.shop.adminuser.vo.AdminUser;

public class AdminService {
	private AdminDao adminDao=new AdminDao();

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}



	public AdminUser login(AdminUser existAdminUser) {
		// TODO Auto-generated method stub
		return adminDao.login(existAdminUser);
	}
	

}
