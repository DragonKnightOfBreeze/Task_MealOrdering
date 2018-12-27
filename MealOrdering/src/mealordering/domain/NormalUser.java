package mealordering.domain;

import mealordering.enums.Identity;

import java.beans.JavaBean;
import java.util.Date;

import static dkbreeze.utils.ext.StringExt.f;

/**
 * 普通用户的实体类
 */
@JavaBean
public class NormalUser extends User {
	private static final long serialVersionUID = 1L;

	/** 用户头像 */
	private String imgUrl;
	/** 用户性别 */
	private String gender;
	/** 用户邮箱地址 */
	private String email;
	/** 用户手机号码 */
	private String phoneNum;
	/** 用户介绍 */
	private String introduce;

	/** 注册激活码 */
	private String activeCode;
	/** 用户激活状态 */
	private int activeState;
	/** 注册时间 */
	private Date registerTime;

	public NormalUser() {}

	public NormalUser(String userName, String password, String imgUrl, String gender, String email, String phoneNum, String introduce) {
		this.userName = userName;
		this.password = password;
		this.type = Identity.normalUser.toString();
		this.gender = gender;
		this.email = email;
		this.phoneNum = phoneNum;
		this.introduce = introduce;
		this.registerTime = new Date();
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
		return f("normalUser [id={0}, userName={1}, type={2}, gender={3}, email={4}, phoneNum={5}, introduce={6}]",
				getId(), getUserName(), getType(), gender, email, phoneNum, introduce);
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
