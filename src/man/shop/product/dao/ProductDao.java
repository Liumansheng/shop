package man.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import man.shop.utils.PageHibernateCallback;
import man.shop.categorysecond.vo.CategorySecond;
import man.shop.product.vo.Product;

public class ProductDao extends HibernateDaoSupport{
	
	private Product product;
	public void setProduct(Product product) {
		this.product = product;
	}


	public List<Product> findHot() {
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot",1));
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}


	public List<Product> findNew() {
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}


	public Product findByPid(Integer pid) {
	return	this.getHibernateTemplate().get(Product.class,pid);
		
	}

	

	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// select p.* from category c,categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2
		// select p from Category c,CategorySecond cs,Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// 分页另一种写法:
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
		
	}


	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}


	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs  where cs.csid = ?";
		// 分页另一种写法:
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}


	public int findCount() {
		String hql="select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;

	}


	public List<Product> findAll(int begin, int limit) {
		String hql="from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, null, begin,
						limit));
		return list;
	}


	public void save(Product product2) {
		this.getHibernateTemplate().save(product2);
	}


	public void delete(Product product2) {
		this.getHibernateTemplate().delete(product2);
		
	}


	public void update(Product product2) {
		this.getHibernateTemplate().update(product2);
		
	}

}
