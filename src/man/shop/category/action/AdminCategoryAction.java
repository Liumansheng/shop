package man.shop.category.action;

import java.util.List;

import javax.management.loading.PrivateClassLoader;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import man.shop.category.service.CategoryService;
import man.shop.category.vo.Category;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	private Category category=new Category();
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

	public String findAll(){
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList",cList);
		
		return "findAll";
	}
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	public String delete(){
		Category category1=categoryService.findByCid(category.getCid());
		categoryService.delete(category1);
		return "deleteSuccess";
	}
	public String edit(){
		category=categoryService.findByCid(category.getCid());
		return "editSuccess";
		
	}
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}

}
