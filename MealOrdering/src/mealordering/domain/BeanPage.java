package mealordering.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Bean分页的实体类
 */
public class BeanPage<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 页面索引 */
	private int pageIndex;
	/** 每页条数 */
	private int count;
	/** 总条数 */
	private int totalCount;
	/** 总页数 */
	private int pageCount;

	/** 对应的Bean条目列表 */
	private List<T> beanList;

	/** 搜索域，用于模糊搜索 */
	private String searchField;
	/** 分类 */
	private String category;


	public BeanPage(int pageIndex, int count) {
		this.pageIndex = pageIndex;
		this.count = count;
	}

	public int getPageIndex() {
		return pageIndex;
	}


	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
