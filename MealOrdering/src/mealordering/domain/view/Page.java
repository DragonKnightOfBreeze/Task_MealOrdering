package mealordering.domain.view;

import task_itcaststore.domain.Product;

import java.io.Serializable;
import java.util.List;

/**
 * 显示页面的实体类
 */
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 当前页码 */
	private int pageIndex;
	/** 总页数 */
	private int pageCount;
	/** 每页条数 */
	private int count;
	/** 总条数 */
	private int totalCount;
	/** 每页显示的数据 */
	private List<Product> productList;
	/** 类别 */
	private String category;
	/** 模糊搜索的图书名 */
	private String searchField;


	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
}
