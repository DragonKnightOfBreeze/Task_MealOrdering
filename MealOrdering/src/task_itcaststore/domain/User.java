package task_itcaststore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户的实体类
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户编号 */
	private int id;
	/** 用户姓名 */
	private String userName;
	/** 用户密码 */
	private String password;
	/** 用户性别 */
	private String gender;
	/** 用户邮箱 */
	private String email;
	/** 用户联系电话 */
	private String telephone;
	/** 用户介绍 */
	private String introduce;
	/** 激活码 */
	private String activeCode;
	/** 用户类型 */
	private String type;
	/** 用户激活状态 */
	private int state;
	/** 注册时间 */
	private Date registTime;


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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}


	@Override
	public String toString() {
		return "User [id=" + getId() + ", userName=" + getUserName() + ", password=" + getPassword() + ", gender=" + getGender() + ", email=" + getEmail() + ", telephone=" + getTelephone() + ", introduce=" + getIntroduce() + ", type=" + getType() + "]";
	}
}
