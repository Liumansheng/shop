package man.shop.adminuser.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import man.shop.adminuser.service.AdminService;
import man.shop.adminuser.vo.AdminUser;

public class AdminAction extends ActionSupport implements ModelDriven<AdminUser>{

	private AdminUser adminUser=new AdminUser();
	private AdminService adminService=new AdminService();
	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public String login(){
		
		 AdminUser existAdminUser=adminService.login(adminUser);
		 if(existAdminUser==null){
			 this.addActionError("您的用户名或密码错误");
			 return "loginFail";
		 }else{ServletActionContext.getRequest().getSession().setAttribute("existAdminUser",existAdminUser);
		 return "loginSuccess";
			 
		 }
		
		
	}
	
	


}
