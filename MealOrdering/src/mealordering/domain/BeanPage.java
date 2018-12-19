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
	/** 总页数 */
	private int pageCount;
	/** 总条数 */
	private int totalCount;

	/** 每页的Bean条目列表 */
	private List<T> beanList;


	public BeanPage() {
	}

	public BeanPage(int pageIndex, int count, List<T> beanList) {
		this.pageIndex = pageIndex;
		this.count = count;
		this.totalCount = beanList.size();
		this.pageCount = (int) Math.ceil(totalCount * 1.0 / count);
		this.beanList = beanList.subList((pageIndex - 1) * count, count);
	}


	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
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
}
