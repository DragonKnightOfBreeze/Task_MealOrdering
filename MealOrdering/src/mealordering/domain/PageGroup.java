package mealordering.domain;

import dk_breeze.annotation.NotTested;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 封装有分页信息的数据页组的实体类
 */
@NotTested
public class PageGroup<T extends Serializable> implements Serializable {
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
	private List<T> list;


	public PageGroup() {
	}

	public PageGroup(List<T> list, int pageIndex) {
		this(list, pageIndex, 15);
	}

	public PageGroup(List<T> list, int pageIndex, int count) {
		this.pageIndex = pageIndex;
		this.count = count;
		this.totalCount = list.size();
		this.pageCount = (int) Math.ceil(totalCount * 1.0 / count);
		this.list = list;
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

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}


	/**
	 * 得到指定的分页，并更新当前页面索引。默认每页15项。
	 * @param pageIndex 页面索引
	 */
	public List<T> getPage(int pageIndex) {
		return getPage(pageIndex, 15);
	}

	/**
	 * 得到指定的分页，并更新当前页面索引和页面条数。
	 * @param pageIndex 页面索引
	 * @param count 页面条目数
	 */
	public List<T> getPage(int pageIndex, int count) {
		this.pageIndex = pageIndex;
		this.count = count;
		int from = (pageIndex - 1) * count;
		int to = Math.min(pageIndex * count, list.size());
		return list.subList(from, to);
	}

	/**
	 * 得到分页条所依据的字符串数组。默认从5开始省略，最多显示8(+1)个按钮。
	 * <br>在js中通过模版字符串，附加判断，生成正确的分页条。
	 * <br> 示例格式：上一页,1,2,3,4,...,i=6,7,8,...,下一页
	 * <br> 示例参数：maxBtnNum = 8,startIgnore = 5,pageIndex=6,pageCount=12
	 */
	public String[] getPageBtnText() {
		return getPageBtnText(5, 8);
	}

	/**
	 * 得到分页条所依据的字符串数组。
	 * <br>在js中通过模版字符串，附加判断，生成正确的分页条。
	 * <br> 示例格式：上一页,1,2,3,4,...,i=6,7,8,...,下一页
	 * <br> 示例参数：maxBtnNum = 8,startIgnore = 5,pageIndex=6,pageCount=12
	 */
	public String[] getPageBtnText(int startIgnore, int maxBtnNum) {
		if(startIgnore >= maxBtnNum)
			throw new IllegalArgumentException();

		int to = Math.min(pageCount, maxBtnNum);
		int delta = pageIndex - startIgnore;
		List<String> result = new ArrayList<>();
		IntStream.rangeClosed(1, to).boxed().forEachOrdered(e -> {
			if(e == 1) {
				if(pageIndex == 1) {
					result.add("D 上一页");
				} else {
					result.add("上一页");
				}
				result.add("1");
			} else if(pageIndex > startIgnore) {
				if(e == startIgnore) {
					result.add("D ...");
				} else if(e > startIgnore) {
					result.add(Integer.toString(e + delta));
				}
			} else if(e == to) {
				if(pageIndex == pageCount) {
					result.add("D 下一页");
				} else {
					result.add("下一页");
					result.add("D ...");
				}
			}
		});
		result.set(startIgnore + 1, "A " + pageIndex);
		return result.toArray(new String[0]);
	}
}

