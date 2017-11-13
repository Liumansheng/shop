package man.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import man.shop.cart.vo.Cart;
import man.shop.cart.vo.CartItem;
import man.shop.product.service.ProductService;
import man.shop.product.vo.Product;

public class CartAction extends ActionSupport{
	private Integer count;
	private Integer pid;
	private ProductService productService;
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
 public String addCart(){
	 CartItem item=new CartItem();
	 item.setCount(count);
	 Product product=productService.findByPid(pid);
	 item.setProduct(product);
	 Cart cart=getCart();
	 cart.addCart(item);
	 return "addCart";
 }
 public String clearCart(){
	 Cart cart=getCart();
	 cart.clearCart();
	 return "clearCart";
 }
 

 
 public String removeCart(){
	 Cart cart=getCart();
	 cart.removeCart(pid);
	 return "removeCart";
 }
 public String myCart(){
	 return "myCart";
 }
 private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession()
					.setAttribute("cart", cart);
		}
		return cart;
	}

}
