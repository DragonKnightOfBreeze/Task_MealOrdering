package mealordering.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单的实体类
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 订单编号 */
	private String id;
	/** 订单总价 */
	private double totalPrice;
	/** 送货地址 */
	private String receiverAddress;
	/** 收货人姓名 */
	private String receiverName;
	/** 收货人电话 */
	private String receiverPhone;
	/** 订单支付状态 1：支付，0：未支付 */
	private int payState;
	/** 订单生成时间 */
	private Date orderTime;
	/** 订单所属用户 */
	private User user;
	/** 订单物品列表 */
	private List<OrderItem> orderItemList = new ArrayList<>();


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}


	public int getPayState() {
		return payState;
	}

	public void setPayState(int payState) {
		this.payState = payState;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("Order [id=%s, totalPrice=%s, receiverAddress=%s, receiverName=%s, receiverPhone=%s, payState=%d, orderTime=%s, user=%s, orderItems=%s]", id, totalPrice, receiverAddress, receiverName, receiverPhone, payState, orderTime, user, orderItemList);
	}

}
