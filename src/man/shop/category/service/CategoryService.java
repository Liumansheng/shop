package man.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import man.shop.category.dao.CategoryDao;
import man.shop.category.vo.Category;
@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public List<Category> findAll(){
		
		return categoryDao.findAll();
	}

	public void save(Category category) {
		categoryDao.save(category);
		
	}

	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
		
	}

	public void delete(Category category1) {
		categoryDao.delete(category1);
		
	}

	public void update(Category category) {
		categoryDao.update(category);
		
	}

}
