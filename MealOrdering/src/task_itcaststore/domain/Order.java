package task_itcaststore.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单的实体类
 */
public class Order {
	/** 订单编号 */
	private String id;
	/** 订单总价 */
	private double money;
	/** 送货地址 */
	private String receiverAddress;
	/** 收货人姓名 */
	private String receiverName;
	/** 收货人电话 */
	private String receiverPhone;
	/** 订单状态 */
	private int payState;
	/** 下单时间 */
	private Date orderTime;
	/** 订单所属用户 */
	private User user;

	private List<OrderItem> orderItems = new ArrayList<>();


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
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

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}


	public int getPayState() {
		return payState;
	}

	public void setPayState(int payState) {
		this.payState = payState;
	}

	public Date getOrderIime() {
		return orderTime;
	}

	public void setOrderIime(Date orderTime) {
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
		return "Order [id=" + id + ", money=" + money + ", receiverAddress=" + receiverAddress + ", receiverName=" + receiverName + ", receiverPhone=" + receiverPhone + ", payState=" + payState + ", orderTime=" + orderTime + ", user=" + user + ", orderItems=" + orderItems + "]";
	}

}
