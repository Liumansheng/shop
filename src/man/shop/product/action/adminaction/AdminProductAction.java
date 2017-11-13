package man.shop.product.action.adminaction;


import java.util.Date;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import man.shop.categorysecond.service.CategorySecondService;
import man.shop.categorysecond.vo.CategorySecond;
import man.shop.product.service.ProductService;
import man.shop.product.vo.Product;
import man.shop.utils.PageBean;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	private CategorySecond categorySecond;
	private Product product =new Product(categorySecond); 
	private ProductService productService;
	private CategorySecondService categorySecondService;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	@Override
	public Product getModel() {
		
		return product;
	}
	
	public String findAllByPage(){
		
		PageBean<Product> pageBean=productService.findAll(page); 
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	public String addPage(){
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	
	public String save() throws IOException{
	
		product.setPdate(new java.util.Date());
				// product.setImage(image);
			if(upload != null){
					// 将商品图片上传到服务器上.
					// 获得上传图片的服务器端路径.
				String path = ServletActionContext.getServletContext().getRealPath(
							"/products");
					// 创建文件类型对象:
				File diskFile = new File(path + "//" + uploadFileName);
					// 文件上传:
				FileUtils.copyFile(upload, diskFile);
			
				product.setImage("products/" + uploadFileName);
				}
		productService.save(product);
		return "saveSuccess";
	}
	
	public String delete() {
		// 根据id查询商品信息
		product = productService.findByPid(product.getPid());
		// 删除商品的图片:
		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + product.getImage());
		File file = new File(path);
		file.delete();
		// 删除数据库中商品记录:
		productService.delete(product);
		// 页面跳转
		return "deleteSuccess";
	}
	
	public String edit(){
		product=productService.findByPid(product.getPid());
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList",csList);
		return "editSuccess";
	}
	
	public String update() throws IOException{
		product.setPdate(new Date());
		
		// 上传:
		if(upload != null ){
			String delPath = ServletActionContext.getServletContext().getRealPath(
					"/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		// 页面跳转
		return "updateSuccess";
	}
}
