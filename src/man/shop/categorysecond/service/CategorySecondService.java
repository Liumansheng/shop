package man.shop.categorysecond.service;

import java.util.List;

import man.shop.categorysecond.dao.CategorySecondDao;
import man.shop.categorysecond.vo.CategorySecond;
import man.shop.product.vo.Product;
import man.shop.utils.PageBean;

public class CategorySecondService {
	private CategorySecondDao categorySecondDao;
	
	public PageBean<CategorySecond> findAllByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPageCid(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
		
	}

	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
		
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
		
	}

	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
		
	}

	public List<CategorySecond> findAll() {
		
		return categorySecondDao.findAll();
	}

}
