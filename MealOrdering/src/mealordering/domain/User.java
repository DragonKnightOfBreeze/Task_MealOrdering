package mealordering.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户的实体类
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 用户编号 */
	private int id;
	/** 用户名称 */
	private String userName;
	/** 用户密码 */
	private String password;
	/** 用户性别 */
	private String gender;
	/** 用户邮箱地址 */
	private String email;
	/** 用户手机号码 */
	private String phoneNum;
	/** 用户介绍 */
	private String introduce;

	/** 用户类型 */
	private String type;
	/** 注册激活码 */
	private String activeCode;
	/** 用户激活状态 */
	private int activeState;
	/** 注册时间 */
	private Date registerTime;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getActiveState() {
		return activeState;
	}

	public void setActiveState(int activeState) {
		this.activeState = activeState;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}


	@Override
	public String toString() {
		return String.format("User [id=%d, userName=%s, gender=%s, email=%s, telephone=%s, introduce=%s, type=%s]", getId(), getUserName(), getGender(), getEmail(), getPhoneNum(), getIntroduce(), getType());
	}
}
