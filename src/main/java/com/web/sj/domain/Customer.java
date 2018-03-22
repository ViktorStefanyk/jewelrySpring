package com.web.sj.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Customer {
	
	public Integer userId;
	public String roleName;
	public Integer roleId;
	public Integer active;
		
	@NotEmpty(message="{NotEmpty.User.userFirstName.validation}")
	@Size(min=2, max=32, message="{Size.User.userFirstName.validation}")
	public String userFirstName;
	
	@NotEmpty(message="{NotEmpty.User.userLastName.validation}")
	@Size(min=2, max=32, message="{Size.User.userLastName.validation}")
	public String userLastName;
	
	public String userPatronymic;
	
	@Email(message="{Email.User.userEmail.validation}")
	public String userEmail;
	
	@NotNull(message=("{NotNull.User.userPassword.validation}"))
	@Size(min=2, max=32, message="{Size.User.userPassword.validation}")
	public String userPassword;
	
//	@NotNull(message=("{NotNull.User.userConfirmPassword.validation}"))
//	@Size(min=2, max=32, message="{Size.User.userConfirmPassword.validation}")
	public String userConfirmPassword;
	
	public String userPhoneNumber;
	
	public Integer quantityUsers;
	
	public boolean newCustomerFlag;
	
	public Customer() {
		super();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserPatronymic() {
		return userPatronymic;
	}
	public void setUserPatronymic(String userPatronymic) {
		this.userPatronymic = userPatronymic;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserConfirmPassword() {
		return userConfirmPassword;
	}
	public void setUserConfirmPassword(String userConfirmPassword) {
		this.userConfirmPassword = userConfirmPassword;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public Integer getQuantityUsers() {
		return quantityUsers;
	}
	public void setQuantityUsers(Integer quantityUsers) {
		this.quantityUsers = quantityUsers;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public boolean isNewCustomerFlag() {
		return newCustomerFlag;
	}
	public void setNewCustomerFlag(boolean newCustomerFlag) {
		this.newCustomerFlag = newCustomerFlag;
	}
}
