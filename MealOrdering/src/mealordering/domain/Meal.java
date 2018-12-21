package mealordering.domain;

import java.io.Serializable;

/**
 * 餐品的实体类
 */
public class Meal implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 餐品编号 */
	private String id;
	/** 餐品名称 */
	private String name;
	/** 餐品价格 */
	private double price;
	/** 餐品分类 */
	private String category;
	/** 餐品图片路径 */
	private String imgUrl;
	/** 餐品描述 */
	private String description;
	/** 餐品数量 */
	private int count = 0;
	/** 餐品售出数量 */
	private int soldCount = 0;

	public Meal() {}

	public Meal(String name, double price, String category, String imgUrl, String description, int count) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.imgUrl = imgUrl;
		this.description = description;
		this.count = count;
	}

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getImgUrl() {
		return imgUrl;
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
		Meal other = (Meal) obj;
		if (id == null)
			return other.id == null;
		else
			return id.equals(other.id);
	}

	@Override
	public String toString() {
		return String.format("Meal [id=%s, name=%s, price=%s, category=%s, imgUrl=%s, description=%s, count=%d, soldCount=%d]", id, name, price, category, imgUrl, description, count, soldCount);
	}
}
