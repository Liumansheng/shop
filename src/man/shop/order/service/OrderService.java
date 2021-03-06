package man.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import man.shop.order.dao.OrderDao;
import man.shop.order.vo.Order;
import man.shop.order.vo.OrderItem;
import man.shop.utils.PageBean;


@Transactional
public class OrderService {
	private OrderDao orderDao;
	

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}


	public void save(Order order) {
		orderDao.save(order);
		
	}


	public PageBean<Order> findByUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin = (page - 1)*limit;
		List<Order> list = orderDao.findPageByUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}


	public void update(Order currOrder) {
		orderDao.update(currOrder);
		
	}


	public PageBean<Order> findAll(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin = (page - 1)*limit;
		List<Order> list = orderDao.findAll(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


	public List<OrderItem> findOrderItem(Integer oid) {
		// TODO Auto-generated method stu
		return orderDao.findOrderItem(oid);
	}

}
