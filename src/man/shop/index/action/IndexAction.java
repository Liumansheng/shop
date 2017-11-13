package man.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import man.shop.category.service.CategoryService;
import man.shop.category.vo.Category;
import man.shop.product.service.ProductService;
import man.shop.product.vo.Product;

public class IndexAction extends ActionSupport{
	/**
	 * 	访问首页
	 */
	private CategoryService categoryService;
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	public String execute(){
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getSession().put("cList",cList);
		
		List<Product> hList=productService.findHot();
		ActionContext.getContext().getValueStack().set("hList",hList);
		
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList",nList);
		return  "index";
	}


	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
