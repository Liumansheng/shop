package man.shop.product.action;



import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import man.shop.category.service.CategoryService;
import man.shop.category.vo.Category;
import man.shop.categorysecond.vo.CategorySecond;
import man.shop.product.service.ProductService;
import man.shop.product.vo.Product;
import man.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	private CategorySecond categorySecond;
	private Product product =new Product(categorySecond); 
	private ProductService productService;
	private Integer cid;
	private Integer csid;
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public Integer getCid() {
		return cid;
	}
	private CategoryService categoryService;
	private int page;
	
	public void setPage(int page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		
		return product;
	}
	
	public String findByPid(){
		
		product=productService.findByPid(product.getPid());
		return "findByPid";
		}
	public String findByCid(){
		
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findByCid"; 
		
	}
	public String findByCsid(){
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findByCsid"; 
		
	}
	

}
