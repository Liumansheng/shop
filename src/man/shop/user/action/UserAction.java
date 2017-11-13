package man.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import man.shop.user.service.UserService;
import man.shop.user.vo.User;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	private User user =new User();
	
	@Override
	public User getModel() {
		
		return user;
	}
	
	public UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * 跳转到注册页面
	 */
	public String execute(){
		
		return "registPage";
	}
	
	public String registPage(){
		return "registPage";
	}
	public String findByName() throws IOException{
		//调用service
		User existUser=userService.findByUsername(user.getUsername());
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		
		if(existUser!=null){
			response.getWriter().println("<font color='red'>user name has exist</font>");
		}else{
			response.getWriter().println("<font color='green'>user name can be used</font>");	
			}
		return NONE;
	}
	
	public String regist(){
		userService.save(user);
		return NONE;
	}
	
	public String loginPage(){
		return "loginPage";
	}
	
	public String login(){
		User existUser=userService.login(user);
		if(existUser==null){
			this.addActionError("登陆失败：用户名或密码错误或账户未激活");
			return LOGIN;
		}else{
			ServletActionContext.getRequest().getSession().
			setAttribute("existUser",existUser);
		}
		return "loginSuccess";
	}
	
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}

	
}
