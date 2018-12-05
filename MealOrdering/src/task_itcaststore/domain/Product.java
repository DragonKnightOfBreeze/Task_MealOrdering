package task_itcaststore.domain;

import java.io.Serializable;

/**
 * 商品的实体类
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 商品编号 */
	private String id;
	/** 名称 */
	private String name;
//	private String author;
	/** 价格 */
	private double price;
	/** 分类 */
	private String category;
	/** 数量 */
	private int pnum;
	/** 图片路径 */
	private String imgUrl;
	/** 描述 */
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//	public String getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(String author) {
//		this.author = author;
//	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	//a.jpg a_s.jpg
	public String getImgUrl_s() {
		int index = imgUrl.lastIndexOf("."); //得到最的.的索引
		String first = imgUrl.substring(0, index);
		String last = imgUrl.substring(index);
		return first + "_s" + last;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (obj == null)
			return false;
		else if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null)
			return other.id == null;
		else
			return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "Meal [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", pnum=" + pnum + ", imgUrl=" + imgUrl + ", description=" + description + ", totalSaleNum=" + "]";
	}
}
