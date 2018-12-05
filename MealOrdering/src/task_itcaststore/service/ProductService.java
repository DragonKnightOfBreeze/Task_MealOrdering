package task_itcaststore.service;

import task_itcaststore.dao.ProductDao;
import task_itcaststore.domain.PageBean;
import task_itcaststore.domain.Product;
import task_itcaststore.exception.AddProductException;
import task_itcaststore.exception.FindProductByIdException;
import task_itcaststore.exception.ListProductException;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品的服务类
 */
public class ProductService {
	private ProductDao dao = new ProductDao();

	/**
	 * 添加商品。
	 */
	public void addProduct(Product product) throws AddProductException {
		try {
			dao.addProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddProductException("警告：添加商品信息！");
		}
	}

	/**
	 * 查找所有商品信息。
	 */
	public List<Product> findAllProducts() throws ListProductException {
		try {
			return dao.findAllProducts();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListProductException("警告：查询商品信息！");
		}
	}

	/**
	 * 分页操作。
	 */
	public PageBean findProductsByPage(int currentPage, int currentCount, String category) {
		PageBean bean = new PageBean();
		bean.setCurrentCount(currentCount);
		bean.setCurrentPage(currentPage);
		bean.setCategory(category);
		try {
			//获取总条数
			int totalCount = dao.getTotalCount(category);
			bean.setTotalCount(totalCount);
			//获取总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
			bean.setTotalPage(totalPage);
			//获取当前页数据
			List<Product> productList = dao.findProductsByPage(currentPage, currentCount, category);
			bean.setProductList(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 根据id查找商品。
	 */
	public Product findProductById(String id) throws FindProductByIdException {
		try {
			return dao.findProductById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindProductByIdException("警告：查询商品信息！");
		}
	}

	/**
	 * 下载销售榜单。
	 */
	public List<Object[]> download(String year, String month) {
		List<Object[]> salesList = null;
		try {
			salesList = dao.getSalesList(year, month);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salesList;
	}

	/**
	 * 多条件查询。
	 */
	public List<Product> findProductsByConditions(String id, String name, String category, String minPrice, String maxPrice) {
		List<Product> ps = null;
		try {
			ps = dao.findProductsByConditions(id, name, category, minPrice, maxPrice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	/**
	 * 修改商品信息。
	 */
	public void updateProduct(Product p) {
		try {
			dao.updateProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 前台，获取本周热销商品。
	 */
	public List<Object[]> getWeekHotProducts() {
		try {
			return dao.getWeekHotProducts();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("警告：前台获取本周热销商品信息！");
		}
	}

	/**
	 * 前台，用于搜索框根据书名来模糊查询相应的图书。
	 */
	public PageBean findBooksByName(int currentPage, int currentCount, String searchField) {
		PageBean bean = new PageBean();
		bean.setCurrentCount(currentCount);
		bean.setCurrentPage(currentPage);
		bean.setSearchField(searchField);
		try {
			//获取总条数
			int totalCount = dao.findBooksByNameGetTotalCount(searchField);
			bean.setTotalCount(totalCount);
			//获取总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
			bean.setTotalPage(totalPage);
			//满足条件的图书
			List<Product> ps = dao.findBooksByName(currentPage, currentCount, searchField);
			bean.setProductList(ps);
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("警告：前台搜索框根据书名查询图书失败！");
		}
	}

	/**
	 * 后台系统，根据id删除商品信息。
	 */
	public void deleteProduct(String id) {
		try {
			dao.deleteProductById(id);
		} catch (SQLException e) {
			throw new RuntimeException("警告：删除商品信息！");
		}
	}
}
