package man.shop.order.action.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import man.shop.order.vo.OrderItem;
import man.shop.order.service.OrderService;
import man.shop.order.vo.Order;

import man.shop.utils.PageBean;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	private Order order=new Order();
	private OrderService orderService;
	private Integer page;
	
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public Order getModel() {
		return order;
	}
	
  public String findAllByPage(){
		
		PageBean<Order> pageBean=orderService.findAll(page); 
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
  public String findOrderItem(){
		// 根据订单id查询订单项:
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		// 显示到页面:
		ActionContext.getContext().getValueStack().set("list", list);
		// 页面跳转
		return "findOrderItem";
	}
	
  public String updateState(){
	  Order currOrder=orderService.findByOid(order.getOid());
	  currOrder.setState(3);
	  orderService.update(currOrder);
	  return "updateStateSuccess";
	  
  }

}
